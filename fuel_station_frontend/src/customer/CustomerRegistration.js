import React,{useState,useEffect} from 'react';
import axios from 'axios';
import { ValidateEmail } from '../formValidation/ValidateEmail';
import { ValidatePassword } from '../formValidation/ValidatePassword';

import { ValidateNIC } from '../formValidation/ValidateNIC';

function CustomerRegistration(){

    const [values,setCustomers] = useState({
      customerName :'',
      customerNIC  : '',
      customerEmail  : '',
      customerUsername : '',
      customerPassword  : '',
      confirmPassword : ""
    });
    const [existingUsers, setExistingUsers] = useState([]);
      const [emailError, setEmailError] = useState("");
      const [passwordError, setPasswordError] = useState("");
      const [nicError, setNicError] = useState("");
      const [valPasswordError, setValPasswordError] = useState("");
      const [uniqueError, setUniqueError] = useState("");
     
      const [isRegistered, setIsRegistered] = useState(false);
      useEffect(() => {
        const fetchUsers = async () => {
          try {
            const response = await axios.get("http://localhost:8080/api/customer");
            if (Array.isArray(response.data)) {
              setExistingUsers(response.data);
            } else {
              console.error("API response is not an array:", response.data);
            } 
          } catch (error) {
            console.error("Error fetching existing users:", error);
          }
        };
    
        fetchUsers();
      }, []);

    
    const handleChanges = (e) => {
        setCustomers({...values,[e.target.name]:e.target.value})
    }
 
    const handleSubmit =   (e) => {
        e.preventDefault();
         if (!ValidateEmail(values.customerEmail)) {
              setEmailError("Invalid email");
              return;
            }
            setEmailError("");
        
            if (!ValidateNIC(values.customerNIC)) {
              setNicError("Invalid NIC");
              return;
            }
            setNicError("");
        
            if (!ValidatePassword(values.customerPassword)) {
              setValPasswordError("Password must be at least 8 characters long, contain at least one uppercase letter, one number, and one special character (e.g., @$!%*?&).");
              return;
            }
            setValPasswordError("");
        
            if (values.customerPassword !== values.confirmPassword) {
              setPasswordError("The passwords entered do not match");
              return;
            }
            setPasswordError("");
           
            if (!Array.isArray(existingUsers)) {
              alert("An error occurred while validating user data. Please try again later.");
              return;
            }
        
            const duplicateUser = existingUsers.find(
              (user) =>
                user.email === values.customerEmail ||
                user.nic === values.customerNIC ||
                user.username === values.customerUsername 
              
            );
        
            if (duplicateUser) {
              setUniqueError(
               ` Duplicate entry detected! ${
                  duplicateUser.email === values.customerEmail
                    ? "CustomerEmail"
                    : duplicateUser.nic === values.customerNIC
                    ? "CustomerNIC"
                    :"CustomerUsername"
                    // : duplicateUser.username === values.customerUsername
                    // ? "CustomerUsername"
                    
                    
                } already exists.`
              );
              return;
            }
            setUniqueError("");

        axios
        .post('http://localhost:8080/api/customer',values, {
        headers: {"Content-Type": "application/json"},
    })
    .then(() => {
       // console.log("Response:", response.data);
       setCustomers({
        customerName :'',
      customerNIC  : '',
      customerEmail  : '',
      customerUserName : '',
      customerPassword  : '',
      confirmPassword : ""
       });
       setIsRegistered(true);
       alert("Customer successfully added");
      
    }).catch( (error) => {
        console.error("Registration error:", error);
        alert("Network error occured .plz try again");
    });
};
        
    return(
        <div className = "container">
            <h1>Customer Registration Form</h1>
             {(
                <form onSubmit = {handleSubmit} >
                
                <label htmlFor ="customerName">Name : </label>  
               <input
                type = "text" 
                placeholder="Enter the customername" 
                name = "customerName" 
                onChange={(e) =>handleChanges(e)} 
                required 
                value = {values.customerName}/>
               <br/><br/> 
              
               <label htmlFor ="customerNIC">NIC : </label>
               <input type = "text" 
               placeholder="Enter the NIC No" 
               name = "customerNIC" 
               onChange={(e) =>handleChanges(e)}  
               value = {values.customerNIC} 
               required/>
               {nicError && <p style = {{ color:"red", fontSize: "12px"}}>{nicError}</p>}
               <br/><br/>
               
               
               <label htmlFor ="customerEmail">Email : </label>
               <input type = "email"
                placeholder="Enter the email" 
                name = "customerEmail" 
                onChange={(e) =>handleChanges(e)} 
                required 
                value = {values.customerEmail}/>
            {emailError && <p style={{ color :"red", fontSize :"12px"}}>{emailError}</p>}       
               <br/><br/>
               
               <label htmlFor ="customerUsername">User Name : </label>
               <input type = "text" 
               placeholder="Enter the username" 
               name = "customerUsername" 
               onChange={(e) =>handleChanges(e)} 
               required 
               value = {values.customerUsername}/>
               <br/><br/>
              
               
              
               <label htmlFor ="customerPassword">Password : </label>
               <input type = "password"
                placeholder="Enter the Password" 
                name = "customerPassword" 
                onChange={(e) =>handleChanges(e)} 
                required 
                value = {values.customerPassword }/>
                {passwordError && <p style = {{ color :"red", fontSize:"12px"}}>{passwordError}</p>}
                {valPasswordError && <p style ={{ color :"red",fontSize : "12px"}}>{valPasswordError}</p>}
               <br/><br/>

               <label htmlFor ="confirmPassword">Confirm Password : </label>
               <input type = "password"
                placeholder="Rewrite password" 
                name = "confirmPassword" 
                onChange={(e) =>handleChanges(e)} 
                required 
                value = {values.confirmPassword}/>
               <br/><br/>

               {uniqueError && (
                <p style = {{ color:"red", fontSize :"12px"}}>{uniqueError}</p>
               )}

               <button type = "submit" >Submit</button>
               </form>
            )}
           
            </div>
            );
};
export default CustomerRegistration;