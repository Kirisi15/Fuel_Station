import React from 'react'
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
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

const HomePage = () => {
  return (
    <div>
      <Router>
        <Navigationbar />
        <Routes>
          <Route path="/" element={<h1>Welcome to the App</h1>} />
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
          </Routes>
        </Router>
    </div>
  )
}

export default HomePage
