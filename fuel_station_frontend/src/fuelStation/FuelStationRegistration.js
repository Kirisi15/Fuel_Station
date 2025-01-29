import React, { useState, useEffect } from 'react';
import axios from 'axios';
import StationDashboard from './StationDashboard';

const FuelStationRegistration = () => {
  const [formData, setFormData] = useState({
    stationName: "",
    address: "",
    licenseNumber: "",
    contactNumber: "",
  });
  const [isRegistered, setIsRegistered] = useState(false);
  const [existingStations, setExistingStations] = useState([]); // Ensure it's initialized as an empty array
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchExistingStations = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/fuelstation");

        // Ensure response.data is an array
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

    // Safely check for duplicates
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

    setError(""); // Clear any previous error
    return true;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!validationForm()) return;

    const ownerId = localStorage.getItem("ownerId");

    try {
      const response = await axios.post(
        `http://localhost:8080/api/fuelstation`,
        formData,
        {
          headers: { "Content-Type": "application/json" },
        }
      );

      // Update localStorage and state
      localStorage.setItem("stationId", response.data.stationId);

      setIsRegistered(true);

      // Add the new station to the existing stations list
      setExistingStations([
        ...existingStations,
        {
          stationName: formData.stationName,
          licenseNumber: formData.licenseNumber,
          contactNumber: formData.contactNumber,
          address: formData.address,
        },
      ]);

      // Clear the form
      setFormData({
        stationName: "",
        address: "",
        licenseNumber: "",
        contactNumber: "",
      });
      setError(""); // Clear errors
      alert("Fuel Station registered successfully!");
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
        <form onSubmit={handleSubmit}>
          {error && <p style={{ color: "red" }}>{error}</p>}
          <h1>Fuel Station Registration</h1>

          <label htmlFor="stationName">Fuel Station Name: </label>
          <input
            type="text"
            placeholder="Enter Fuel Station Name"
            value={formData.stationName}
            onChange={(e) =>
              setFormData({ ...formData, stationName: e.target.value })
            }
            required
          />
          <br />
          <br />

          <label htmlFor="address">Station Address: </label>
          <input
            type="text"
            placeholder="Enter Fuel Station Address"
            value={formData.address}
            onChange={(e) =>
              setFormData({ ...formData, address: e.target.value })
            }
            required
          />
          <br />
          <br />

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
          <br />
          <br />

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
          <br />
          <br />

          <button name="register" type="submit">
            Register
          </button>
        </form>
      )}
    </div>
  );
};

export default FuelStationRegistration;
