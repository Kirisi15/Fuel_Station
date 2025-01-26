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
    fuelLimit: ""
  });

  const navigate = useNavigate();

  const [vehicleId, setVehicleId] = useState(null);
  const [qrCodeData, setQrCodeData] = useState(null);
  const [error, setError] = useState("");
  const [customer, setCustomer] = useState([]);
  const qrCodeRef = useRef(null);
  const [existingVehicles, setExistingVehicles] = useState([]);
  const [fuelLimits, setFuelLimits] = useState([]); // To store the fetched fuel limits

  useEffect(() => {
    setCustomer(localStorage.getItem("customerId"));

    const fetchExistingVehicles = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/vehicle");
        setExistingVehicles(response.data);
      } catch (error) {
        console.error("Failed to fetch existing vehicles:", error);
      }
    };

    const fetchFuelLimits = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/fuel/limits");
        setFuelLimits(response.data);
      } catch (error) {
        console.error("Failed to fetch fuel limits:", error);
      }
    };

    fetchExistingVehicles();
    fetchFuelLimits();
  }, []);

  const handleChanges = (e) => {
    const { name, value } = e.target;

    if (name === "vehicleType") {
      // Automatically set the fuel limit based on the selected vehicle type
      const selectedFuelLimit = fuelLimits.find((limit) => limit.vehicleType === value);
      setVehicles({
        ...values,
        [name]: value,
        fuelLimit: selectedFuelLimit ? selectedFuelLimit.fuelLimit : ""
      });
    } else {
      setVehicles({ ...values, [name]: value });
    }
  };

  const validateForm = () => {
    if (!values.vehicleType || !values.vehicleNumber || !values.fuelType || !values.fuelLimit) {
      setError("All fields are required.");
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

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validateForm()) return;

    try {
      const response = await axios.post(
        `http://localhost:8080/api/vehicle/${customer}`,
        values,
        {
          headers: { "Content-Type": "application/json" },
        }
      );

      const generatedVehicleId = response.data.vehicleId;

      setVehicleId(generatedVehicleId);
      navigate(`/vehicle-qr/${generatedVehicleId}`, { state: { qrCodeData: generatedVehicleId } });

      alert("Vehicle successfully added");
    } catch (error) {
      setError("Failed to register the vehicle. Please try again.");
    }
  };
     /* const qrData = `Vehicle ID: ${generatedVehicleId}, Vehicle Type: ${values.vehicleType}, Vehicle Number: ${values.vehicleNumber}, Fuel Type: ${values.fuelType}, Fuel Limit: ${values.fuelLimit}`;
      setQrCodeData(qrData);

      setExistingVehicles([...existingVehicles, { vehicleNumber: values.vehicleNumber }]);

      setVehicles({
        vehicleType: "",
        vehicleNumber: "",
        fuelType: "",
        fuelLimit: ""
      });
*/
      

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
        <select
          name="vehicleType"
          onChange={handleChanges}
          value={values.vehicleType}
          required
        >
          <option value="">Select Vehicle Type</option>
          {fuelLimits.map((limit) => (
            <option key={limit.id} value={limit.vehicleType}>
              {limit.vehicleType}
            </option>
          ))}
        </select>
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

        <label htmlFor="fuelLimit">Fuel Limit:</label>
        <input
          type="number"
          step="1"
          placeholder="Fuel limit will be auto-filled"
          name="fuelLimit"
          value={values.fuelLimit}
          readOnly
        />
        <br />
        <br />

        <button type="submit">Submit</button>
      </form>

      {/* {vehicleId && (
        <div>
          <h2>Vehicle Registered Successfully!</h2>
          <p>Vehicle ID: {vehicleId}</p>
          <h3>QR Code:</h3>
          <div ref={qrCodeRef}>
            <QRCodeCanvas value={qrCodeData} />
          </div>
          <button onClick={downloadQRCode}>Download QR Code</button>
        </div>
      )} */}
    </div>
  );
}

export default VehicleRegistration;
