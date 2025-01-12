import {useState} from "react";
import axios from "axios";
function EmployeeRegistration(){

    const [values,setEmployees] = useState({
       employeeJobrole :'',
       employeeNic : '',
       employeeUsername : '',
       employeeContactnumber : '',
       employeePassword : ''
    })

    const handleChanges = (e) => {
        setEmployees({...values,[e.target.name]:e.target.value})
    }
 
    const handleSubmit =  async (e) => {
        e.preventDefault()
        try{
       await axios.post('http://localhost:8080/api/customer',values, {
        headers: {"Content-Type": "application/json"},
    });
       setEmployees({
       employeeJobrole  :'',
        employeeNic : '',
        employeeUsername : '',
        employeeContactnumber : '',
        employeePassword : ''
       });
       alert("Employee successfully added");
       console.log(values);
    
    }catch (error){
        alert("Network error occured .plz try again");
    }
}
    return(
        <div className = "container">
            <h1>Registration Form</h1>
            <form onSubmit = {handleSubmit} >
                
                 <label htmlFor ="employeeJobrole">Jobrole : </label>  
                <input
                 type = "text" 
                 placeholder="Enter the employeename" 
                 name = "employeeJobrole" 
                 onChange={(e) =>handleChanges(e)} 
                 required 
                 value = {values.employeeJobrole }/>
                <br/><br/> 
               
                <label htmlFor ="employeeNic">NIC : </label>
                <input type = "text" 
                placeholder="Enter the NIC No" 
                name = "employeeNic" 
                onChange={(e) =>handleChanges(e)}  
                value = {values.employeeNic} 
                required/>
                <br/><br/>
                
                
                <label htmlFor ="employeeContactnumber">Contact Number : </label>
                <input type = "text"
                 placeholder="Enter the contactnumber" 
                 name = "employeeContactnumber" 
                 onChange={(e) =>handleChanges(e)} 
                 required 
                 value = {values.employeeContactnumber}/>
                <br/><br/>
                
                <label htmlFor ="employeeUsername">User Name : </label>
                <input type = "text" 
                placeholder="Enter the username" 
                name = "employeeUsername" 
                onChange={(e) =>handleChanges(e)} 
                required 
                value = {values.employeeUsername}/>
                <br/><br/>
               
                
               
                <label htmlFor ="employeePassword">Password : </label>
                <input type = "password"
                 placeholder="Enter the Password" 
                 name = "employeePassword" 
                 onChange={(e) =>handleChanges(e)} 
                 required 
                 value = {values.employeePassword}/>
                <br/><br/>
                
                
                <button type = "submit" >Submit</button>
                </form>
            </div>
            );
}
export default EmployeeRegistration;