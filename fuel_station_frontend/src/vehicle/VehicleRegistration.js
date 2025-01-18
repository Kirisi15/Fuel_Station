import { useState } from "react";
import React from "react";
import { QRCodeCanvas } from "qrcode.react"; // Correctly import QRCodeCanvas from qrcode.react
import axios from "axios";

function VehicleRegistration() {
  const [values, setVehicles] = useState({
    vehicleType: "",
    vehicleNumber: "",
    fuelType: "",
  });

  const [vehicleId, setVehicleId] = useState(null); // To store the generated vehicle ID
  const [qrCodeData, setQrCodeData] = useState(null); // To store the QR code content

  const handleChanges = (e) => {
    setVehicles({ ...values, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        "http://localhost:8080/api/vehicle",
        values,
        {
          headers: { "Content-Type": "application/json" },
        }
      );

      // Assuming the backend responds with the newly generated vehicle ID
      const generatedVehicleId = response.data.vehicleId;

      setVehicleId(generatedVehicleId);

      // Generate QR code content (you can customize this as needed)
      const qrData = `Vehicle ID: ${generatedVehicleId}, Vehicle Type: ${values.vehicleType}, Vehicle Number: ${values.vehicleNumber}, Fuel Type: ${values.fuelType}`;
      setQrCodeData(qrData);

      // Reset the form
      setVehicles({
        vehicleType: "",
        vehicleNumber: "",
        fuelType: "",
      });

      alert("Vehicle successfully added");
    } catch (error) {
      alert("Network error occurred. Please try again.");
    }
  };

  return (
    <div className="container">
      <h1>Vehicle Registration</h1>
      <form onSubmit={handleSubmit}>
        <label htmlFor="vehicleType">Vehicle Type: </label>
        <input
          type="text"
          placeholder="Enter the vehicle type"
          name="vehicleType"
          onChange={(e) => handleChanges(e)}
          required
          value={values.vehicleType}
        />
        <br />
        <br />

        <label htmlFor="vehicleNumber">Vehicle Number: </label>
        <input
          type="text"
          placeholder="Enter the vehicle number"
          name="vehicleNumber"
          onChange={(e) => handleChanges(e)}
          value={values.vehicleNumber}
          required
        />
        <br />
        <br />

        <label htmlFor="fuelType">Fuel Type: </label>
        <input
          type="text"
          placeholder="Enter the fuel type"
          name="fuelType"
          onChange={(e) => handleChanges(e)}
          required
          value={values.fuelType}
        />
        <br />
        <br />

        <button type="submit">Submit</button>
      </form>

      {/* Display the QR code if vehicle ID is generated */}
      {vehicleId && (
        <div>
          <h2>Vehicle Registered Successfully!</h2>
          <p>Vehicle ID: {vehicleId}</p>
          <h3>QR Code:</h3>
          <QRCodeCanvas value={qrCodeData} /> {/* Use QRCodeCanvas to generate the QR code */}
          <p>Scan this QR code to view vehicle details.</p>
        </div>
      )}
    </div>
  );
}

export default VehicleRegistration;
