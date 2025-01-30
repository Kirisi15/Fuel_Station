import React, { useState, useEffect } from "react";
import axios from "axios";

const EditStation = () => {
  const [stationName, setStationName] = useState("");
  const [address, setAddress] = useState("");
  const [licenseNumber, setLicenseNumber] = useState("");
  const [contactNumber, setContactNumber] = useState("");
  const [isLoading, setIsLoading] = useState(); // Track loading state

  

  useEffect(() => {
    const fetchStationDetails = async () => {
     
const stationId = localStorage.getItem("stationId");
console.log(stationId);
      try {
         if (!stationId) {
           console.error("stationId is not provided");
           return;
         }

        const response = await axios.get(`http://localhost:8080/api/fuelstation/${stationId}`);

        
        
        

        
        setStationName(response.data.data.stationName);
          setAddress(response.data.data.address);
          setLicenseNumber(response.data.data.licenseNumber);
     setContactNumber(response.data.data.contactNumber);
        setIsLoading(false); 

      } catch (error) {
        console.error("Error fetching station details:");
        setIsLoading(false); 
      }
    };

    
       fetchStationDetails();
    
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const stationId = localStorage.getItem("stationId");
      const response = await fetch(`http://localhost:8080/api/fuelstation/${stationId}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          stationName,
          address,
          licenseNumber,
          contactNumber,
        }),
      });

      if (!response.ok) {
        throw new Error("Failed to update station");
      }

      alert("Station updated successfully!");
      

    } catch (error) {
      console.error("Error updating station:", error);
    }
  };

  return (
    <div>
      <h2>Edit Station</h2>
      {isLoading ? (
        <p>Loading station details...</p> 
      ) : (
        <form onSubmit={handleSubmit}>
          <label>
            Station Name:
            <input
              type="text"
              value={stationName}
              onChange={(e) => setStationName(e.target.value)}
              required
            />
          </label>
          <br />
          <br />
          <label>
            Address:
            <input
              type="text"
              value={address}
              onChange={(e) => setAddress(e.target.value)}
              required
            />
          </label>
          <br />
          <br />
          <label>
            License Number:
            <input
              type="text"
              value={licenseNumber}
              onChange={(e) => setLicenseNumber(e.target.value)}
              required
            />
          </label>
          <br />
          <br />
          <label>
            Contact Number:
            <input
              type="text"
              value={contactNumber}
              onChange={(e) => setContactNumber(e.target.value)}
              required
            />
          </label>
          <br />
          <br />
          <button type="submit">Update Station</button>
        </form>
      )}
    </div>
  );
};

export default EditStation;
