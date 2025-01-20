import { useState } from "react";
import React from "react";

import axios from "axios";

function VehicleRegistration() {
  const [values, setVehicles] = useState({
    vehicleType: "",
    vehicleNumber: "",
    fuelType: "",
  });


  const handleChanges = (e) => {
    setVehicles({ ...values, [e.target.name]: e.target.value });
  };

  };

  return (
    <div className="container">
      <h1>Vehicle Registration</h1>
      <form onSubmit={handleSubmit}>

        <input
          type="text"
          placeholder="Enter the vehicle type"
          name="vehicleType"

          required
          value={values.vehicleType}
        />
        <br />
        <br />

        <input
          type="text"
          placeholder="Enter the vehicle number"
          name="vehicleNumber"

          value={values.vehicleNumber}
          required
        />
        <br />
        <br />

        <br />
        <br />

        <button type="submit">Submit</button>
      </form>


      {vehicleId && (
        <div>
          <h2>Vehicle Registered Successfully!</h2>
          <p>Vehicle ID: {vehicleId}</p>
          <h3>QR Code:</h3>

          <p>Scan this QR code to view vehicle details.</p>
        </div>
      )}
    </div>
  );
}

export default VehicleRegistration;
