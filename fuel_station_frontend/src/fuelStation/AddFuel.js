import React, { useState, useEffect } from 'react';
import axios from 'axios';

const AddFuel = () => {
  const [fuels, setFuels] = useState([{ fuelType: "", addedFuel: 0, pumpedFuel: 0 }]);
  const [existingFuelTypes, setExistingFuelTypes] = useState([]);
  const stationId = localStorage.getItem("stationId");

  useEffect(() => {
    const fetchExistingFuelTypes = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/fuel/types/${stationId}`);
        setExistingFuelTypes(response.data);
      } catch (error) {
        console.error("Error fetching existing fuel types:", error);
      }
    };
    fetchExistingFuelTypes();
  }, [stationId]);

  const handleFuelChange = (index, field, value) => {
    const updatedFuels = [...fuels];
    updatedFuels[index][field] = value;
    setFuels(updatedFuels);
  };

  const addFuelField = () => {
    setFuels([...fuels, { fuelType: "", addedFuel: 0, pumpedFuel: 0 }]);
  };

  const removeFuelField = (index) => {
    const updatedFuels = fuels.filter((_, i) => i !== index);
    setFuels(updatedFuels);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      for (const fuel of fuels) {
        if (existingFuelTypes.includes(fuel.fuelType)) {
          alert(`Fuel type "${fuel.fuelType}" already exists for this station.`);
          return;
        }

        await axios.post(`http://localhost:8080/api/fuel/${stationId}`, fuel, {
          headers: { "Content-Type": "application/json" },
        });
      }

      alert("Fuel types added successfully!");
      setFuels([{ fuelType: "", addedFuel: 0, pumpedFuel: 0 }]);

      const response = await axios.get(`http://localhost:8080/api/fuel/types/${stationId}`);
      setExistingFuelTypes(response.data);
    } catch (error) {
      console.error("Error adding fuels:", error);
      alert("Failed to add fuels. Please try again.");
    }
  };

  const availableFuelTypes = [
    "Petrol Octane 92",
    "Petrol Octane 95",
    "Diesel",
  ].filter((type) => !existingFuelTypes.includes(type));

  return (
    <div>
      <h1>Add Fuel for Station ID: {stationId}</h1>

      <form onSubmit={handleSubmit}>
        <label htmlFor="fuelTypes">Fuel Types : </label>
        {fuels.map((fuel, index) => (
          <div key={index}>
            <select
              value={fuel.fuelType}
              onChange={(e) => handleFuelChange(index, 'fuelType', e.target.value)}
              required
            >
              <option value="">Select Fuel Type</option>

              {availableFuelTypes.map((type) => (
                <option key={type} value={type}>
                  {type}
                </option>
              ))}
            </select>
            <br/>
            {fuels.length > 1 && (
              <button type="button" onClick={() => removeFuelField(index)}>
                Remove
              </button>
            )}
          </div>
        ))}
        <button type="button" onClick={addFuelField}>Add Another Fuel Type</button>
        <button type="submit">Submit</button>
      </form>
    </div>
  );
};

export default AddFuel;
