import React, { useState, useEffect } from "react"; 
import axios from "axios";
import { useNavigate } from "react-router-dom";
import '../components/Dashboard.css';

function CustomerDashboard() {
  const [vehicles, setVehicles] = useState([]);
  const [fuelLimits, setFuelLimits] = useState({});
  const [error, setError] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    const customerId = localStorage.getItem("customerId");

    const fetchVehicles = async () => {
      try {
        console.log("Fetching vehicles for customer ID:", customerId);
        const response = await axios.get(`http://localhost:8080/api/vehicle/customer/${customerId}`);
        console.log("data :", response.data);
        
        if (Array.isArray(response.data.data)) {
          setVehicles(response.data.data);
          setError("");
        } else {
          setVehicles([]);
          setError("No vehicles found for this customer.");
        }
      } catch (err) {
        setVehicles([]);
        setError("Failed to fetch vehicle details. Please try again.");
      }
    };

    if (customerId) {
      fetchVehicles();
    }
  }, []);

  useEffect(() => {
    
    const fetchFuelLimits = async () => {
      const limits = {};
      await Promise.all(
        vehicles.map(async (vehicle) => {
          try {
            const response = await axios.get(`http://localhost:8080/api/fuelLimit/limit/${vehicle.vehicleType}`);
            limits[vehicle.vehicleType] = response.data.fuelLimit; 
          } catch (err) {
            limits[vehicle.vehicleType] = "N/A"; 
          }
        })
      );
      setFuelLimits(limits);
    };

    if (vehicles.length > 0) {
      fetchFuelLimits();
    }
  }, [vehicles]);

  const handleNavigation = (path) => {
    navigate(path);
  };

  return (
    <div className="dashboard-btn">
      <div style={styles.container}>
        <h1 style={styles.header}>Customer Dashboard</h1>
        <button type="submit" className="btn" onClick={() => handleNavigation("/vehReg")}>Add Vehicle</button><br/><br/>
        {error && <p style={styles.error}>{error}</p>}
        {vehicles.length === 0 ? (
          <p style={styles.message}>No vehicles found for this customer.</p>
        ) : (
          <div style={styles.grid}>
            {vehicles.map((vehicle, index) => (
              <div key={index} style={styles.card}>
                <h3 style={styles.cardHeader}>{vehicle.vehicleType}</h3>
                <p><strong>Fuel Type:</strong> {vehicle.fuelType}</p>
                <p><strong>Fuel Limit:</strong> {fuelLimits[vehicle.vehicleType] || "Loading..."}</p>
                <p><strong>Vehicle Number:</strong> {vehicle.vehicleNumber}</p>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}

const styles = {
  container: {
    padding: "20px",
    fontFamily: "Arial, sans-serif",
  },
  header: {
    textAlign: "center",
    color: "#333",
  },
  error: {
    color: "red",
    textAlign: "center",
  },
  message: {
    textAlign: "center",
    color: "#666",
  },
  grid: {
    display: "grid",
    gridTemplateColumns: "repeat(auto-fit, minmax(300px, 1fr))",
    gap: "20px",
    marginTop: "20px",
  },
  card: {
    border: "1px solid #ccc",
    borderRadius: "8px",
    padding: "20px",
    boxShadow: "0 4px 6px rgba(0, 0, 0, 0.1)",
    backgroundColor: "#fff",
  },
  cardHeader: {
    color: "#007bff",
    marginBottom: "10px",
  },
};

export default CustomerDashboard;