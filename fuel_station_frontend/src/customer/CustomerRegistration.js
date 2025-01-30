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
                user.customerEmail === values.customerEmail ||
                user.customerNIC === values.customerNIC ||
                user.customerUsername === values.customerUsername 
              
            );
        
            if (duplicateUser) {
              setUniqueError(
               ` Duplicate entry detected! ${
                  duplicateUser.customerEmail === values.customerEmail
                    ? "CustomerEmail"
                    : duplicateUser.customerNIC === values.customerNIC
                    ? "CustomerNIC"
                    : "CustomerUsername"
                  } already exists.`
              );
              return;
            }
            setUniqueError("");

        axios
        .post('http://localhost:8080/api/customer/register',values, {
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
       alert("Customer successfully added");
      
    }).catch( (error) => {
        console.error("Registration error:", error);
        alert("Network error occured .plz try again");
    });
};
        
    return(
      <div>
      <div className="custom-form">
        <form onSubmit={handleSubmit}>
          <h2>Customer Registration</h2>
          <label htmlFor="name">Name:</label>
          <input
            type="text"
            placeholder="Enter Customer Name"
            name="customerName"
            value={values.customerName}
            onChange={handleChanges}
            required
          />
          <label htmlFor="nic">NIC:</label>
          <input
            type="text"
            placeholder="Enter NIC number"
            name="customerNIC"
            value={values.customerNIC}
            onChange={handleChanges}
            required
          />
          {nicError && <p>{nicError}</p>}
          <label htmlFor="email">Email:</label>
          <input
            type="email"
            placeholder="Email"
            name="customerEmail"
            value={values.customerEmail}
            onChange={handleChanges}
            required
          />
          {emailError && <p>{emailError}</p>}
          <label htmlFor="username">Username:</label>
          <input
            type="text"
            placeholder="Username"
            name="customerUsername"
            value={values.customerUsername}
            onChange={handleChanges}
            required
          />
          <label htmlFor="password">Password:</label>
          <input
            type="password"
            placeholder="Password"
            name="customerPassword"
            value={values.customerPassword}
            onChange={handleChanges}
            required
          />
          {passwordError && <p>{passwordError}</p>}
          {valPasswordError && <p>{valPasswordError}</p>}
          <label htmlFor="confirmPassword">Confirm Password:</label>
          <input
            type="password"
            placeholder="Rewrite password"
            name="confirmPassword"
            value={values.confirmPassword}
            onChange={handleChanges}
            required
          />
          {uniqueError && <p>{uniqueError}</p>}
          <button className="btn" type="submit">
            SignUp
          </button>
          <button type="submit" className="btn" >Sign In</button>
        </form>
      </div>
    </div>
    );
};
export default CustomerRegistration;