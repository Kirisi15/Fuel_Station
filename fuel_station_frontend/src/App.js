import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import './App.css';

import React from 'react';

import VehicleRegistration from "./vehicle/VehicleRegistration";
import Navbar from "./components/Navbar";
import OwnerRegistration from './owner/OwnerRegistration';
import FuelStationRegistration from "./fuelStation/FuelStationRegistration";

function App() {
  return (
    <Router>
      <Navbar />
        <Routes>
          <Route path="/" element={<h1>Welcome to the App</h1>} />
          <Route path="/ownerReg" element={<OwnerRegistration />} />
          <Route path="/stationReg" element={<FuelStationRegistration />} />
          
          <Route path="/vehicleReg" element={<VehicleRegistration />} />
          

        </Routes>
    </Router>

  );
}

export default App;
