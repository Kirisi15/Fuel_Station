import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function CustomerDashboard() {
  const [vehicles, setVehicles] = useState([]);
  const [error, setError] = useState("");
  const navigate = useNavigate();

  
   
  
  useEffect(() => {
    const customerId = localStorage.getItem("customerId");

    
    const fetchVehicles = async (customerId) => {
      try {
       console.log("Fetching vehicles for customer ID:", customerId);
        const response = await axios.get(
          `http://localhost:8080/api/vehicle/customer/${customerId}`
        );
        console.log(response.data);
        if (response.data) {
          setVehicles(response.data); 
          setError("");
        } else {
          setError("No vehicles found for this customer.");
        }
      } catch (err) {
        setError("Failed to fetch vehicle details. Please try again.");
      }
    };
    
    if(customerId)
    {
      fetchVehicles(customerId);
    }

    
  }, []);

  const handleNavigation = (path) => {
    navigate(path);
};

  return (
    <div>
    <button onClick={() => handleNavigation("/vehReg")}>Add Vehicle</button><br/><br/>

    <div style={styles.container}>
      <h1 style={styles.header}>Customer Dashboard</h1>
      {error && <p style={styles.error}>{error}</p>}
      {vehicles.length === 0 ? (
        <p style={styles.message}>No vehicles found for this customer.</p>
      ) : (
        <div style={styles.grid}>
          {vehicles.map((vehicle,index) => (
            <div key={index} style={styles.card}>
              <h3 style={styles.cardHeader}>{vehicle.vehicleType}</h3>
       
              <p><strong>Fuel Type:</strong> {vehicle.fuelType}</p>
              <p><strong>Fuel Limit:</strong> {vehicle.fuelLimit}</p>
              <p><strong>Vehicle Number:</strong> {vehicle.vehicleNumber}</p>
            </div>
          ))}
        </div>
      )}
    </div>
    </div>
  );
}
//ghdgddkl
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
