import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function StationManagement() {
  const [stations, setStations] = useState([]);
  const [error, setError] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    const ownerId = localStorage.getItem("ownerId"); // Assuming ownerId is stored in localStorage
    console.log("Fetching stations for owner ID:", ownerId);

    const fetchStations = async (ownerId) => {
      try {
        const response = await axios.get(
          `http://localhost:8080/fuel-stations/owner/${ownerId}`
        );
        console.log("Fetched stations:", response.data);
        if (response.data && response.data.length > 0) {
          setStations(response.data);
          setError("");
        } else {
          setError("No stations found for this owner.");
        }
      } catch (err) {
        console.error("Error fetching stations:", err);
        setError("Failed to fetch station details. Please try again.");
      }
    };

    if (ownerId) {
      fetchStations(ownerId);
    }
  }, []);

  const handleNavigation = (path) => {
    navigate(path);
  };

  return (
    <div>
      <button onClick={() => handleNavigation("/StationReg")}>
        Add Station
      </button>
      <br />
      <br />
      <div style={styles.container}>
        <h1 style={styles.header}>Station Management</h1>
        {error && <p style={styles.error}>{error}</p>}
        {stations.length === 0 ? (
          <p style={styles.message}>No stations found for this owner.</p>
        ) : (
          <div style={styles.grid}>
            {stations.map((station, index) => (
              <div
                key={index}
                style={styles.card}
               // onClick={() => handleNavigation(`/stations/${station.id}/dashboard`)}
              >
                <h3 style={styles.cardHeader}>{station.name}</h3>
                <p>
                  <strong>Location:</strong> {station.location}
                </p>
                <p>
                  <strong>Contact:</strong> {station.contactNumber}
                </p>
                <p>
                  <strong>Fuel Types:</strong> {station.fuelTypes.join(", ")}
                </p>
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
    cursor: "pointer",
    transition: "transform 0.2s",
  },
  cardHeader: {
    color: "#007bff",
    marginBottom: "10px",
  },
};

export default StationManagement;
