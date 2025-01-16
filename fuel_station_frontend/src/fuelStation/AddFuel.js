import React, { useState } from 'react';
import axios from 'axios';

const AddFuel = () => {
  const [fuels, setFuels] = useState(['']);
  const [stationId, setStationId] = useState('');

  const handleFuelTypeChange = (index, value) => {
    const updatedFuelTypes = [...fuels];
    updatedFuelTypes[index] = value;
    setFuels(updatedFuelTypes);
  };

  const addFuelTypeField = () => {
    setFuels([...fuels, '']);
  };

  const removeFuelTypeField = (index) => {
    const updatedFuelTypes = fuels.filter((_, i) => i !== index);
    setFuels(updatedFuelTypes);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Loop through the fuels and add each one to the station
    for (const fuelType of fuels) {
      try {
        const fuelEntity = { fuelType }; // You can expand this object based on your FuelEntity structure
        const response = await axios.post(
          "http://localhost:8080/api/fuel", 
          fuelEntity, 
          { params: { stationId } } // pass stationId as a query parameter
        );
        console.log('Response:', response.data);
        alert('Fuel types added to the station successfully!');
      } catch (error) {
        alert('Network error occurred. Please try again later.');
        console.error('Error:', error);
      }
    }
  };

  return (
    <div>
      <h1>Add Fuel</h1>
      <form onSubmit={handleSubmit}>
        <label>Station ID:</label>
        <input
          type="text"
          placeholder="Enter Station ID"
          value={stationId}
          onChange={(e) => setStationId(e.target.value)}
          required
        />
        <br />
        <label>Fuel Types:</label>
        {fuels.map((fuel, index) => (
          <div key={index}>
            <input
              type="text"
              placeholder="Enter Fuel Type"
              value={fuel}
              onChange={(e) => handleFuelTypeChange(index, e.target.value)}
              required
            />
            {fuels.length > 1 && (
              <button type="button" onClick={() => removeFuelTypeField(index)}>
                Remove
              </button>
            )}
          </div>
        ))}
        <button type="button" onClick={addFuelTypeField}>
          Add Another Fuel Type
        </button>
        <br />
        <button type="submit">Add Fuels</button>
      </form>
    </div>
  );
};

export default AddFuel;
