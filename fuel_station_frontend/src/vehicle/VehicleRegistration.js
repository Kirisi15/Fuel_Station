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
  const [error, setError] = useState("");
  const [customers, setCustomers] = useState([]);
  const qrCodeRef = useRef(null); // Ref for the QR code canvas

  useEffect(() => {
    const fetchCustomers = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/customer");
        setCustomers(response.data);
      } catch (error) {
        console.error("Failed to fetch customers:", error);
      }
    };

    fetchCustomers();
  }, []);

  const handleChanges = (e) => {
    setVehicles({ ...values, [e.target.name]: e.target.value });
  };

  const validateForm = () => {
    if (!values.vehicleType || !values.vehicleNumber || !values.fuelType || !values.customerId) {
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

      const qrData = `Vehicle ID: ${generatedVehicleId}, Vehicle Type: ${values.vehicleType}, Vehicle Number: ${values.vehicleNumber}, Fuel Type: ${values.fuelType}, Customer ID: ${values.customerId}`;
      setQrCodeData(qrData);

      setVehicles({
        vehicleType: "",
        vehicleNumber: "",
        fuelType: "",
        customerId: "",
      });

      alert("Vehicle successfully added");
    } catch (error) {
      setError("Failed to register the vehicle. Please try again.");
    }
  };

  const downloadQRCode = () => {
    const canvas = qrCodeRef.current.querySelector("canvas");
    const pngUrl = canvas.toDataURL("image/png").replace("image/png", "image/octet-stream");

    const downloadLink = document.createElement("a");
    downloadLink.href = pngUrl;
    downloadLink.download = `vehicle_qr_${vehicleId}.png`;
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
  };

  return (
    <div className="container">
      <h1>Vehicle Registration</h1>
      <form onSubmit={handleSubmit}>
        {error && <p style={{ color: "red" }}>{error}</p>}
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

        <label htmlFor="customerId">Customer:</label>
        <select
          name="customerId"
          onChange={handleChanges}
          value={values.customerId}
          required
        >
          <option value="">Select Customer</option>
          {customers.map((customer) => (
            <option key={customer.id} value={customer.id}>
              {customer.customerName} ({customer.customerEmail})
            </option>
          ))}
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
          <div ref={qrCodeRef}>
            <QRCodeCanvas value={qrCodeData} />
          </div>
          <button onClick={downloadQRCode}>Download QR Code</button>
        </div>
      )}
    </div>
  );
}

export default VehicleRegistration;
