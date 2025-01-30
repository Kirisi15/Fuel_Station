import { useState, useEffect, useRef } from "react";
import React from "react";
import { QRCodeCanvas } from "qrcode.react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function VehicleRegistration() {
  const [values, setVehicles] = useState({
    vehicleType: "",
    vehicleNumber: "",
    fuelType: "",
    fuelLimit: "",
  });

  const navigate = useNavigate();
  const [vehicleId, setVehicleId] = useState(null);
  const [qrCodeData, setQrCodeData] = useState(null);
  const [error, setError] = useState("");
  const [customer, setCustomer] = useState(localStorage.getItem("customerId") || ""); 
  const qrCodeRef = useRef(null);
  const [existingVehicles, setExistingVehicles] = useState([]); 
  const [fuelLimits, setFuelLimits] = useState([]); 

  useEffect(() => {
    // Fetch existing vehicles
    const fetchExistingVehicles = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/vehicle");
        setExistingVehicles(Array.isArray(response.data) ? response.data : []);
      } catch (error) {
        console.error("Failed to fetch existing vehicles:", error);
      }
    };

    // Fetch fuel limits to populate the dropdown
    const fetchFuelLimits = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/fuelLimit/limits");
        console.log("Fuel Limits API Response:", response.data);
        setFuelLimits(Array.isArray(response.data) ? response.data : []);
      } catch (error) {
        console.error("Failed to fetch fuel limits:", error);
      }
    };

    fetchExistingVehicles();
    fetchFuelLimits();
  }, []);

  // Handle input changes
  const handleChanges = (e) => {
    const { name, value } = e.target;

    if (name === "vehicleType") {
      const selectedFuelLimit = fuelLimits.find((limit) => limit.vehicleType === value);
      setVehicles({
        ...values,
        [name]: value,
        fuelLimit: selectedFuelLimit ? selectedFuelLimit.fuelLimit : "",
      });
    } else {
      setVehicles({ ...values, [name]: value });
    }
  };

  // Validate the form before submission
  const validateForm = () => {
    if (!values.vehicleType || !values.vehicleNumber || !values.fuelType || !values.fuelLimit) {
      setError("All fields are required.");
      return false;
    }

    if (!Array.isArray(existingVehicles)) {
      setError("Existing vehicles data is not valid.");
      return false;
    }

    const isDuplicate = existingVehicles.some(
      (vehicle) => vehicle.vehicleNumber === values.vehicleNumber
    );

    if (isDuplicate) {
      setError("A vehicle with this number already exists.");
      return false;
    }

    if (!/^[A-Za-z0-9-]+$/.test(values.vehicleNumber)) {
      setError("Vehicle number should be alphanumeric (e.g., ABC-1234).");
      return false;
    }

    if (!/^\d+(\.\d{1,2})?$/.test(values.fuelLimit)) {
      setError("Fuel limit should be a valid number.");
      return false;
    }

    setError("");
    return true;
  };

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validateForm()) return;

    // Log data before making the request
    console.log("Submitting vehicle data:", values);

    try {
      const response = await axios.post(
        "http://localhost:8080/api/vehicle",
        {
          vehicleType: values.vehicleType,
          vehicleNumber: values.vehicleNumber,
          fuelType: values.fuelType,
          fuelLimit: Number(values.fuelLimit), 
          customerId: customer, 
        },
        {
          headers: { "Content-Type": "application/json" },
        }
      );

      console.log("Vehicle successfully registered:", response.data);
      const generatedVehicleId = response.data.vehicleId;

      setVehicleId(generatedVehicleId);
      navigate(`/vehicle-qr/${generatedVehicleId}`, { state: { qrCodeData: generatedVehicleId } });

      alert("Vehicle successfully added");
    } catch (error) {
      console.error("Error registering vehicle:", error.response?.data || error.message);
      setError("Failed to register the vehicle. Please check your input and try again.");
    }
  };

  // QR Code Download Function
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
        <select name="vehicleType" onChange={handleChanges} value={values.vehicleType} required>
          <option value="">Select Vehicle Type</option>
          {fuelLimits.map((limit) => (
            <option key={limit.id} value={limit.vehicleType}>
              {limit.vehicleType}
            </option>
          ))}
        </select>
        <br /><br />

        <label htmlFor="vehicleNumber">Vehicle Number:</label>
        <input
          type="text"
          placeholder="Enter the vehicle number"
          name="vehicleNumber"
          onChange={handleChanges}
          value={values.vehicleNumber}
          required
        />
        <br /><br />

        <label htmlFor="fuelType">Fuel Type:</label>
        <select name="fuelType" onChange={handleChanges} value={values.fuelType} required>
          <option value="">Select Fuel Type</option>
          <option value="Petrol">Petrol</option>
          <option value="Diesel">Diesel</option>
          <option value="CNG">CNG</option>
          <option value="Electric">Electric</option>
        </select>
        <br /><br />

        <label htmlFor="fuelLimit">Fuel Limit:</label>
        <input
          type="number"
          step="1"
          placeholder="Fuel limit will be auto-filled"
          name="fuelLimit"
          value={values.fuelLimit}
          readOnly
        />
        <br /><br />

        <button type="submit">Submit</button>
      </form>
    </div>
  );
}

export default VehicleRegistration;
