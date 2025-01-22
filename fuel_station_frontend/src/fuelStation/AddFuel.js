import React, { useState } from 'react';
import axios from 'axios';

const AddFuel = () => {
  const [fuels, setFuels] = useState([{ fuelType: "", addedFuel: 0, pumpedFuel: 0 }]);

  const handleFuelChange = (index, field, value) => {
    const updatedFuels = [...fuels];
    updatedFuels[index][field] = value;
    setFuels(updatedFuels);
  };

  const addFuelField = () => {
    setFuels([...fuels, { fuelType: "petrol", addedFuel: 0, pumpedFuel: 0 }]);
  };

  const removeFuelField = (index) => {
    const updatedFuels = fuels.filter((_, i) => i !== index);
    setFuels(updatedFuels);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const stationId = localStorage.getItem("stationId"); 
    console.log(stationId);

    try {
      for (const fuel of fuels) {
        await axios.post(`http://localhost:8080/api/fuel/${stationId}`, fuel, {
          headers: { "Content-Type": "application/json" },
        });
      }
      alert("Fuel types added successfully!");
      setFuels([{ fuelType: "", addedFuel: 0, pumpedFuel: 0 }]);
    } catch (error) {
      console.error("Error:", error);
      alert("Failed to add fuels. Please try again.");
    }
  };


  return (
    <div>
      <h1>Add Fuel</h1>

        <form onSubmit={handleSubmit}>
          <label>Fuel Types:</label>
          {fuels.map((fuel, index) => (
            <div key={index}>
              <input
                type="text"
                placeholder="Enter Fuel Type"
                value={fuel.fuelType}
                onChange={(e) => handleFuelChange(index, 'fuelType', e.target.value)}
                required
              />
              <input
                type="number"
                placeholder="Enter Added Fuel"
                value={fuel.addedFuel}
                onChange={(e) => handleFuelChange(index, 'addedFuel', e.target.value)}
                required
              />
              <input
                type="number"
                placeholder="Enter Pumped Fuel"
                value={fuel.pumpedFuel}
                onChange={(e) => handleFuelChange(index, 'pumpedFuel', e.target.value)}
                required
              />
              {fuels.length > 1 && (
                <button type="button" onClick={() => removeFuelField(index)}>
                  Remove
                </button>
              )}
            </div>
          ))}
          <button type="button" onClick={addFuelField}>
            Add Another Fuel Type
          </button>
          <br />
          <button type="submit" onClick={handleSubmit}>Add Fuels</button>
        </form>
    </div>
  );
};

export default AddFuel;
