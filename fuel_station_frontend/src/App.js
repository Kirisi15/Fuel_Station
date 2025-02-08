import './App.css';
import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import "bootstrap/dist/js/bootstrap.bundle.min.js";
import HomePage from './HomePage';
import VehicleRegistration from "./vehicle/VehicleRegistration";
import Navigationbar from './components/Navigationbar';

import OwnerRegistration from './owner/OwnerRegistration';
import FuelStationRegistration from "./fuelStation/FuelStationRegistration";
import CustomerRegistration from "./customer/CustomerRegistration";
import CustomerDashboard from "./customer/CustomerDashboard";
import AdminLogin from "./admin/AdminLogin";
import CustomerLogin from "./customer/CustomerLogin";
import EmployeeRegistration from "./employee/EmployeeRegistration";
import OwnerLogin from "./owner/OwnerLogin";
import OwnerDashboard from "./owner/OwnerDashboard";
import ManageEmployees from "./fuelStation/ManageEmployees";
import AddFuel from "./fuelStation/AddFuel";
import StationDashboard from "./fuelStation/StationDashboard";
import TransactionTable from "./fuelStation/TransactionTable";
import ViewStations from "./vehicle/ViewStations";
import EditStation from "./fuelStation/EditStation";

import VehicleRegistrationAfter from "./vehicle/VehicleRegistrationAfter";

import StationManagement from './owner/StationManagement';
import UpdateOwnerDetails from './owner/UpdateOwnerDetails';
import HomePageDesign from './HomePageDesign';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

function App() {
  return (
    <div>
       
      <Router>
      <Navigationbar />
        <Routes>
        <Route path = "/" element= {<HomePage />}/>
        <Route path="/ownerReg" element={<OwnerRegistration />} />
          <Route path="/stationReg" element={<FuelStationRegistration />} />
          <Route path="/cusReg" element={<CustomerRegistration/>}/>
          <Route path="/vehicleReg" element={<VehicleRegistration />} />
          <Route path="/dashboard/:customerId" element={<CustomerDashboard />} />
          <Route path="/adminLog" element={<AdminLogin />} />
          <Route path="/cusLogin" element={<CustomerLogin />} />
          
          <Route  path="/OwnerLogin" element={<OwnerLogin/>}/> 
          <Route path="/OwnerDashboard" element={<OwnerDashboard/>}/>     
          <Route  path="/addFuel" element={<AddFuel/>}/>  
          <Route  path="/EmpMang" element={<ManageEmployees/>}/>  
          <Route  path="EmpReg" element={<EmployeeRegistration/>}/>  
          <Route path="TransactionTable" element={<TransactionTable/>}/> 
          <Route path="StationDashboard" element={<StationDashboard/>}/>
          <Route path="ViewStations" element={<ViewStations/>} />
          <Route  path="/vehReg" element={<VehicleRegistration/>}/>
          <Route path="EditStation"  element={<EditStation/>}/>
          <Route path="/vehicle-qr/:vehicleId" element={<VehicleRegistrationAfter/>}/>
          <Route path="UpdateOwnerDetails" element={<UpdateOwnerDetails/>}/>
          <Route path='StationManagement' element={<StationManagement/>}/>
          <Route path='/HomePageDesign' element={<HomePageDesign/>}/>
          
          </Routes>
      
      </Router>
     
    </div>
    );
}

export default App;
