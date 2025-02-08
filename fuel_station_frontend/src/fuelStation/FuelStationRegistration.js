import React, { useState, useEffect } from "react";
import axios from "axios";
import StationDashboard from "./StationDashboard";
import "../components/formStyles.css";

const FuelStationRegistration = () => {
  const [formData, setFormData] = useState({
    stationName: "",
    address: "",
    licenseNumber: "",
    contactNumber: "",
  });
  const [isRegistered, setIsRegistered] = useState(false);
  const [existingStations, setExistingStations] = useState([]);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchExistingStations = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/fuelstation");
        const stations = Array.isArray(response.data) ? response.data : [];
        setExistingStations(stations);
      } catch (error) {
        console.error("Failed to fetch existing stations:", error);
        setError("Failed to fetch existing stations. Please try again later.");
      }
    };

    fetchExistingStations();
  }, []);

  const validationForm = () => {
    if (
      !formData.stationName ||
      !formData.address ||
      !formData.licenseNumber ||
      !formData.contactNumber
    ) {
      setError("All fields are required.");
      return false;
    }

    const isDuplicate =
      Array.isArray(existingStations) &&
      existingStations.some(
        (station) =>
          station.stationName === formData.stationName ||
          station.licenseNumber === formData.licenseNumber ||
          station.contactNumber === formData.contactNumber
      );

    if (isDuplicate) {
      setError("A station with similar details already exists.");
      return false;
    }

    setError("");
    return true;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!validationForm()) return;

    const ownerId = localStorage.getItem("ownerId");

    try {
      const requestData = { ...formData, ownerId };

      const response = await axios.post(
        "http://localhost:8080/api/fuelstation",
        requestData,
        {
          headers: { "Content-Type": "application/json" },
        }
      );

      console.log("API Response:", response.data);

      if (response.status === 200 && response.data.data?.stationId) {
        alert("Fuel Station registered successfully!");
        localStorage.setItem("stationId", response.data.data.stationId);
      } else {
        setError("Station registered, but no station ID returned.");
      }

      setExistingStations([...existingStations, formData]);

      setFormData({
        stationName: "",
        address: "",
        licenseNumber: "",
        contactNumber: "",
      });

      setError("");

    } catch (error) {
      console.error("Error:", error);
      setError("Network error occurred. Please try again later.");
    }
  };

  return (
    <div>
      {isRegistered ? (
        <StationDashboard />
      ) : (
        <div className="custom-form">
          <form onSubmit={handleSubmit}>
            <h2>Station Registration</h2>

            {error && <p style={{ color: "red" }}>{error}</p>}

            <div className="inputGroup">
              <label htmlFor="stationName">Station Name: </label>
              <input
                type="text"
                placeholder="Enter Station Name"
                value={formData.stationName}
                onChange={(e) =>
                  setFormData({ ...formData, stationName: e.target.value })
                }
                required
              />
              <label htmlFor="address">Station Address: </label>
              <input
                type="text"
                placeholder="Enter Station Address"
                value={formData.address}
                onChange={(e) =>
                  setFormData({ ...formData, address: e.target.value })
                }
                required
              />
              <label htmlFor="licenseNumber">Station License Number: </label>
              <input
                type="text"
                placeholder="Enter License Number"
                value={formData.licenseNumber}
                onChange={(e) =>
                  setFormData({ ...formData, licenseNumber: e.target.value })
                }
                required
              />

              <label htmlFor="contactNumber">Station Contact Number: </label>
              <input
                type="text"
                placeholder="Enter Contact Number"
                value={formData.contactNumber}
                onChange={(e) =>
                  setFormData({ ...formData, contactNumber: e.target.value })
                }
                required
              />

              <button type="submit" className="btn">
                Sign Up
              </button>
            </div>
          </form>
        </div>
      )}
    </div>
  );
};

export default FuelStationRegistration;
