import React, { useRef } from "react";
import { useParams, useLocation } from "react-router-dom";
import { QRCodeCanvas } from "qrcode.react";

function VehicleQRCodePage() {
  const { vehicleId } = useParams();
  const location = useLocation();
  const qrCodeRef = useRef(null);

  const qrCodeData = location.state?.qrCodeData || `Vehicle ID: ${vehicleId}`;

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
      <h1>Vehicle Registered Successfully!</h1>
      <p>Vehicle ID: {vehicleId}</p>
      <h3>QR Code:</h3>
      <div ref={qrCodeRef}>
        <QRCodeCanvas value={qrCodeData} />
      </div>
      <button onClick={downloadQRCode}>Download QR Code</button>
    </div>
  );
}

export default VehicleQRCodePage;
