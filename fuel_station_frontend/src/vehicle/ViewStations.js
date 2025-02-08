import React, { useState, useEffect } from "react";
import axios from "axios";

const ViewStations = () => {
  const [stations, setStations] = useState([]);
  const [fuelTypes, setFuelTypes] = useState({});
  const [selectedFuelId, setSelectedFuelId] = useState(null);
  const [selectedStationId, setSelectedStationId] = useState(null);
  const [selectedFuelType, setSelectedFuelType] = useState("");
  const [quantity, setQuantity] = useState(0);
  const [showModal, setShowModal] = useState(false);

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/fuelstation")
      .then((response) => {
        console.log("Fetched stations:", response.data); // Debugging line
        setStations(Array.isArray(response.data) ? response.data : []);
      })
      .catch((error) => console.error("Error fetching stations:", error));
  }, []);

  useEffect(() => {
    const fetchFuelTypes = async () => {
      const fuelTypesData = {};
      for (const station of stations) {
        try {
          const response = await axios.get(
            `http://localhost:8080/api/fuel/types/${station.stationId}`
          );
          fuelTypesData[station.stationId] = response.data;
        } catch (error) {
          console.error(`Error fetching fuel types for station ${station.stationId}:`, error);
        }
      }
      setFuelTypes(fuelTypesData);
    };

    if (stations.length > 0) {
      fetchFuelTypes();
    }
  }, [stations]);

  const handleFuelClick = (stationId, fuelId, fuelType) => {
    setSelectedStationId(stationId);
    setSelectedFuelType(fuelType);
    setSelectedFuelId(fuelId);
    setShowModal(true);
  };

  const handleAddFuel = async () => {
    try {
      await axios.put(`http://localhost:8080/api/fuel/add-fuel`, {
        stationId: selectedStationId,
        fuelType: selectedFuelType, 
        quantity: parseFloat(quantity), 
      });
      alert("Fuel quantity added successfully!");
      setShowModal(false);
      setQuantity(0);
    } catch (error) {
      console.error("Error updating fuel quantity:", error);
      alert("Failed to add fuel quantity. Please try again.");
    }
  };

  return (
    <div>
      <h1>Admin Dashboard</h1>
      <div>
        {stations.map((station) => (
          <div key={station.stationId}>
            <h2>{station.stationName}</h2>
            <p>Address: {station.address}</p>
            <p>License Number: {station.licenseNumber}</p>
            <p>Contact Number: {station.contactNumber}</p>

            {fuelTypes[station.stationId] &&
              fuelTypes[station.stationId].map((fuel) => (
                <button
                  key={fuel.fuelId}
                  onClick={() => handleFuelClick(station.stationId, fuel.fuelId, fuel.fuelType)}
                >
                  {fuel.fuelType}
                </button>
              ))}
          </div>
        ))}
      </div>

      {showModal && (
        <div>
          <h3>Add Fuel Quantity</h3>
          <p>Station ID: {selectedStationId}</p>
          <p>Fuel Type: {selectedFuelType}</p>
          <input
            type="number"
            value={quantity}
            onChange={(e) => setQuantity(e.target.value)}
            placeholder="Enter quantity to add"
          />
          <button onClick={handleAddFuel}>Add Fuel</button>
          <button onClick={() => setShowModal(false)}>Cancel</button>
        </div>
      )}
    </div>
  );
};

export default ViewStations;
