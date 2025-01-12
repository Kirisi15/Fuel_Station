import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import './App.css';

import React from 'react';

import VehicleRegistration from "./vehicle/VehicleRegistration";
import Navbar from "./components/Navbar";
import OwnerRegistration from './owner/OwnerRegistration';
import FuelStationRegistration from "./fuelStation/FuelStationRegistration";
import CustomerRegistration from "./customer/CustomerRegistration";
import CustomerDashboard from "./customer/CustomerDashboard";

function App() {
  return (
    <Router>
      <Navbar />
        <Routes>
          <Route path="/" element={<h1>Welcome to the App</h1>} />
          <Route path="/ownerReg" element={<OwnerRegistration />} />
          <Route path="/stationReg" element={<FuelStationRegistration />} />
          <Route path="/cusReg" element={<CustomerRegistration/>}/>
          <Route path="/vehicleReg" element={<VehicleRegistration />} />
          <Route path="/customerDash" element={<CustomerDashboard />} />
          

        </Routes>
    </Router>

  );
}

export default App;
