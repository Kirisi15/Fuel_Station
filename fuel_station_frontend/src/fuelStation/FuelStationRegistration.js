import React from 'react'
import axios from 'axios';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';


const FuelStationRegistration = () => {
    const [formData, setFormData] = useState({stationName: "",address: "", licenseNumber: "", contactNumber: ""});
    const [isRegistered, setIsRegistered] = useState(false);
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        const ownerId=  localStorage.getItem("ownerId")
        try {
          const response = await axios.post(`http://localhost:8080/fuel-stations/${ownerId}`, formData, {
            headers: { "Content-Type": "application/json" },
          });
          console.log("Response:", response.data);
          const { stationId } = response.data;        
            localStorage.setItem("stationId",response.data.stationId);
            setIsRegistered(true);
          alert("Fuel Station registered successfully!");
        } catch (error) {
          console.error("Error:", error);
          alert("Network error occurred. Please try again later.");
        }
      };

      const handleAddFuel = () => {
        navigate("/addFuel");
      };

  return (
    <div>
      <h1>Fuel Station Registration</h1>

      {isRegistered ? (
        <div>
          <button onClick={handleAddFuel}>Add Fuel</button>
        </div>
      ) : (
      <form onSubmit = {handleSubmit}>

      <label htmlFor="stationName">Fuel Station Name : </label>
      <input 
        type="text"
        placeholder='Enter Fuel Station Name'
        value = {formData.stationName}
        onChange={(e) => setFormData({...formData, stationName: e.target.value})}
        required
      />
      <br />
      <br />

      <label htmlFor="address">Station Address : </label>
      <input 
        type="text"
        placeholder='Enter Fuel Station Address'
        value = {formData.address}
        onChange={(e) => setFormData({...formData, address: e.target.value})}
        required
      />
      <br />
      <br />

      <label htmlFor="licenseNumber">Station License Number : </label>
      <input 
        type="text"
        placeholder='Enter License Number'
        value = {formData.licenseNumber}
        onChange={(e) => setFormData({...formData, licenseNumber: e.target.value})}
        required
      />
      <br />
      <br />

      <label htmlFor="contactNumber">Station Contact Number : </label>
      <input 
        type="text"
        placeholder='Enter Contact Number'
        value = {formData.contactNumber}
        onChange={(e) => setFormData({...formData, contactNumber: e.target.value})}
        required
      />
      <br />
      <br />
      <button name = "register" type='submit'>Register</button>
      </form>
      )}
    </div>
  )
}

export default FuelStationRegistration
