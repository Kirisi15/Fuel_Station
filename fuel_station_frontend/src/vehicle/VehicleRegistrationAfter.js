import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";

function VehicleQRCodePage() {
  const [vehicleId, setVehicleId] = useState(null);
  const location = useLocation();

  useEffect(() => {
    const storedVehicleId = localStorage.getItem("vehicleId");
    setVehicleId(storedVehicleId);
  }, []);

  
  const qrCodeData = location.state?.qrCodeData || `Vehicle ID: ${vehicleId}`;

  
  const qrCodeUrl = `https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=${encodeURIComponent(qrCodeData)}`;

  const downloadQRCode = () => {
    const downloadLink = document.createElement("a");
    downloadLink.href = qrCodeUrl;
    downloadLink.download = `vehicle_qr_${vehicleId}.png`;
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
  };

  return (
    <div className="container">
      <h1>Vehicle Registered Successfully!</h1>
      <p>Vehicle ID: {vehicleId}</p>
      <h3>QR Code:</h3>
      <img src={qrCodeUrl} alt="Vehicle QR Code" />
      <button onClick={downloadQRCode}>Download QR Code</button>
    </div>
  );
}

export default VehicleQRCodePage;
