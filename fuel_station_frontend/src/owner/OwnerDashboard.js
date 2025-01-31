import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from 'react-router-dom';
import '../components/Dashboard.css';

const OwnerDashboard = () => {
  const navigate = useNavigate(); 
  const [owner, setOwner] = useState({ name: "",nic:"",contactNumber:"" ,email: "" });
  const [stations, setStations] = useState([]);
  const [error, setError] = useState("");
  
  const ownerId = localStorage.getItem("ownerId"); 
  
  useEffect(() => {
    fetchOwnerDetails();
  
  }, []);

  const fetchOwnerDetails = async () => {
    try {
     
      const response = await axios.get(`http://localhost:8080/api/owners/${ownerId}`);
      setOwner(response.data);
    } catch (error) {
      console.error("Error fetching owner details", error);
    }
  };


  useEffect(() => {
  const fetchStations = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/api/fuelstation/?ownerId=${ownerId}`);
      setStations(response.data);
    } catch (error) {
      console.error("Error fetching stations", error);
    }
  };

  fetchStations();
}, [ownerId]);

const handleStationClick = (stationId) => {
  navigate("/stations/${stationId}");
};

const handleAddFuelStation = () => {
  navigate("/stationReg");
}

 return (
    <div className = "dashboard-btn">
      <h1>Owner Dashboard</h1>
      <button onClick={handleAddFuelStation}>Add FuelStation</button><br/><br/>
      
      <button className="btn" onClick={()=> navigate("/UpdateOwnerDetails") }>Update Details</button><br/><br/>
      <button className= "btn" onClick={()=>navigate("/StationManagement")}>Stations Management</button><br/><br/>
<div className="owner-container">
  {owner && owner.length > 0 ? (
    owner.map((owner) => (
      <div key={owner.ownerId} className="owner-box">
        <h2>Owner Details</h2>
        <p><strong>Name:</strong> {owner.name}</p>
        <p><strong>NIC:</strong> {owner.nic}</p>
        <p><strong>Contact Number:</strong> {owner.contactNumber}</p>
        <p><strong>Email:</strong> {owner.email}</p>
      </div>
    ))
  ) : (
    <p>No owner details available.</p>
  )}
</div>
<div>
      <h2> Stations</h2>
      {error && <p style={{ color: "red" }}>{error}</p>}
      {stations.length > 0 ? (
        stations.map((station) => (
          <div key={station.stationId}>
            <h3>{station.name}</h3>
            <p><strong>Location:</strong> {station.location}</p>
            <p><strong>Contact:</strong> {station.contactNumber}</p>
            <button onClick={() => handleStationClick(station.stationId)}>
              View Station Details
            </button>
          </div>
        ))
      ) : (
        <p>No stations found for this owner.</p>
      )}
    </div>

      
    </div>
  );
};

export default OwnerDashboard;