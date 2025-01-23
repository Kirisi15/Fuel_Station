import './App.css';
import React from 'react';


import VehicleRegistration from "./vehicle/VehicleRegistration";
import Navbar from "./components/Navbar";
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
// import UpdateOwnerDetails from "./owner/UpdateOwnerDetails";
import EditStation from "./fuelStation/EditStation";




function App() {
  return (
    <div>
      <HomePage />
    </div>


           

          <Route  path="/OwnerLogin" element={<OwnerLogin/>}/> 
          <Route path="/OwnerDashboard" element={<OwnerDashboard/>}/>     
          <Route  path="/addFuel" element={<AddFuel/>}/>  
          <Route  path="/EmpMang" element={<ManageEmployees/>}/>  
         <Route  path="EmpReg" element={<EmployeeRegistration/>}/>  
         <Route path="TransactionTable" element={<TransactionTable/>}/> 
         <Route path="StationDashboard" element={<StationDashboard/>}/>
         <Route path="EditStation"  element={<EditStation/>}/>
         {/* <Route path="UpdateOwnerDetails" element={<UpdateOwnerDetails/>}/> */}
         
         


        </Routes>
    </Router>


  );
}

export default App;
