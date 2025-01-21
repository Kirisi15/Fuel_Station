import { useState, useEffect, useRef } from "react";
import React from "react";
import { QRCodeCanvas } from "qrcode.react";
import axios from "axios";




function VehicleRegistration() {
  const [values, setVehicles] = useState({
    vehicleType: "",
    vehicleNumber: "",
    fuelType: "",
    customerId: "",
  });

  const [vehicleId, setVehicleId] = useState(null);
  const [qrCodeData, setQrCodeData] = useState(null);

      setError("All fields are required.");
      return false;
    }
    if (!/^[A-Za-z]+$/.test(values.vehicleType)) {
      setError("Vehicle type should contain only alphabets.");
      return false;
    }
    if (!/^[A-Za-z0-9-]+$/.test(values.vehicleNumber)) {
      setError("Vehicle number should be alphanumeric (e.g., ABC-1234).");
      return false;
    }
    setError("");
    return true;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validateForm()) return;

    try {
      const response = await axios.post(
        "http://localhost:8080/api/vehicle",
        values,
        {
          headers: { "Content-Type": "application/json" },
        }
      );

      const generatedVehicleId = response.data.vehicleId;

      setVehicleId(generatedVehicleId);


      setQrCodeData(qrData);

      setVehicles({
        vehicleType: "",
        vehicleNumber: "",
        fuelType: "",


      alert("Vehicle successfully added");
    } catch (error) {
      setError("Failed to register the vehicle. Please try again.");
    }

  };

  return (
    <div className="container">
      <h1>Vehicle Registration</h1>
      <form onSubmit={handleSubmit}>

        <label htmlFor="vehicleType">Vehicle Type:</label>
        <input
          type="text"
          placeholder="Enter the vehicle type"
          name="vehicleType"
          onChange={handleChanges}
          required
          value={values.vehicleType}
        />
        <br />
        <br />

        <label htmlFor="vehicleNumber">Vehicle Number:</label>
        <input
          type="text"
          placeholder="Enter the vehicle number"
          name="vehicleNumber"
          onChange={handleChanges}
          value={values.vehicleNumber}
          required
        />
        <br />
        <br />

        <label htmlFor="fuelType">Fuel Type:</label>
        <select
          name="fuelType"
          onChange={handleChanges}
          value={values.fuelType}
          required
        >
          <option value="">Select Fuel Type</option>
          <option value="Petrol">Petrol</option>
          <option value="Diesel">Diesel</option>
          <option value="CNG">CNG</option>
          <option value="Electric">Electric</option>
        </select>

        <br />
        <br />

        <button type="submit">Submit</button>
      </form>

      {vehicleId && (
        <div>
          <h2>Vehicle Registered Successfully!</h2>
          <p>Vehicle ID: {vehicleId}</p>
          <h3>QR Code:</h3>

        </div>
      )}
    </div>
  );
}

export default VehicleRegistration;
