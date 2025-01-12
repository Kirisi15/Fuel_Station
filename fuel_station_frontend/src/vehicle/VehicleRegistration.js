import {useState} from 'react';
import React from 'react'
import axios from 'axios';
function VehicleRegistration(){

    const [values,setVehicles] = useState({
      vehicleType :'',
      vehicleNumber  : '',
      fuelType  : '',
      
    })

    const handleChanges = (e) => {
        setVehicles({...values,[e.target.name]:e.target.value})
    }
 
    const handleSubmit =  async (e) => {
        e.preventDefault()
        try{
       await axios.post('http://localhost:8080/api/vehicle',values, {
        headers: {"Content-Type": "application/json"},
    });
       setVehicles({
        vehicleType :'',
        vehicleNumber  : '',
        fuelType  : '',
      
       });
       alert("Vehicle successfully added");
       console.log(values);
    
    }catch (error){
        alert("Network error occured .plz try again");
    }
}
    return(
        <div className = "container">
            <h1>Vehicle Registration </h1>
            <form onSubmit = {handleSubmit} >
                
                 <label htmlFor ="vehicleType">Vehicle Type : </label>  
                <input
                 type = "text" 
                 placeholder="Enter the vehicle" 
                 name = "vehicleType" 
                 onChange={(e) =>handleChanges(e)} 
                 required 
                 value = {values.vehicleType}/>
                <br/><br/> 
               
                <label htmlFor ="vehicleNumber">Vehice Number : </label>
                <input type = "text" 
                placeholder="Enter the Vehicle No" 
                name = "vehicleNumber" 
                onChange={(e) =>handleChanges(e)}  
                value = {values.vehicleNumber} 
                required/>
                <br/><br/>
                
                
                <label htmlFor ="fuelType">Fuel Type : </label>
                <input type = "text"
                 placeholder="Enter the fuel type" 
                 name = "fuelType" 
                 onChange={(e) =>handleChanges(e)} 
                 required 
                 value = {values.fuelType}/>
                <br/><br/>
                
                <button type = "submit" >Submit</button>
                </form>
            </div>
            );
}
export default VehicleRegistration;