import {useState} from "react";
import axios from "axios";
//import { ValidateEmail } from '../formValidation/ValidateEmail';
import { ValidatePassword } from '../formValidation/ValidatePassword';
import { useNavigate } from 'react-router-dom';
import { ValidateNIC } from '../formValidation/ValidateNIC';

function EmployeeRegistration(){

    const [values,setEmployees] = useState({
       employeeName :'',
       employeeNic : '',
       employeeUsername : '',
       employeeContactnumber : '',
       employeePassword : '',
       confirmPassword : ''
    })

    const [existingUsers, setExistingUsers] = useState([]);
   // const [emailError, setEmailError] = useState("");
    const [passwordError, setPasswordError] = useState("");
    const [nicError, setNicError] = useState("");
    const [valPasswordError, setValPasswordError] = useState("");
    const [uniqueError, setUniqueError] = useState("");
    const navigate = useNavigate();
    const [isRegistered, setIsRegistered] = useState(false);
    useEffect(() => {
      const fetchUsers = async () => {
        try {
          const response = await axios.get("http://localhost:8080/employees");
          setExistingUsers(response.data); 
        } catch (error) {
          console.error("Error fetching existing users:", error);
        }
      };
  
      fetchUsers();
    }, []);



    const handleChanges = (e) => {
        setEmployees({...values,[e.target.name]:e.target.value})
    }
 
    const handleSubmit =  async (e) => {
        e.preventDefault();

        

        if(!ValidateNIC(values.employeeNic)){
            setNicError("Invalid nic");
            return;
        }
        setNicError("");

        if(!ValidatePassword(values.employeePassword)){
          setValPasswordError("Password must be atleast 8 characters long,contain at least one uppercase letter, one number, and one special character (e.g., @$!%*?&).");
          return;  
        }
        setValPasswordError("");
        if (values.employeePassword !== values.confirmPassword) {
            setPasswordError("The passwords entered do not match");
            return;
        }
        setPasswordError("");
        const duplicateUser = existingUsers.find(
            (user) =>
             
              user.nic === values.employeeNic ||
              user.username === values.employeeUsername ||
              user.contactNumber === values.employeeContactnumber
            
          );
      
          if (duplicateUser) {
            setUniqueError(
              `Duplicate entry detected! ${
              
                  duplicateUser.nic === values.employeeNic
                  ? "NIC"
                  : duplicateUser.username === values.employeeUsername
                  ? "Username"
                  :"ContactNumber"
                  
              } already exists.`
            );
            return;
          }
          setUniqueError("");


        
       axios.post('http://localhost:8080/employee',values, {
        headers: {"Content-Type": "application/json"},
    })

    .then((response) => {
        console.log("Response:",response.data);
    
       setEmployees({
        employeeName  :'',
        employeeNic : '',
        employeeUsername : '',
        employeeContactnumber : '',
        employeePassword : '',
        confirmPassword : ''
       });
       setIsRegistered(true);
       alert("Employee successfully added");
     
    
    }).catch( (error) => {
        console.error("Registration error:",error);
        alert("Network error occured .plz try again");
    });
};
    return(
        <div className = "container">
            <h1>Registration Form</h1>
            <form onSubmit = {handleSubmit} >
                
                 <label htmlFor ="employeeName">Employee Name : </label>  
                <input
                 type = "text" 
                 placeholder="Enter the employeename" 
                 name = "employeeName" 
                 onChange={(e) =>handleChanges(e)} 
                 required 
                 value = {values.employeeName }/>
                <br/><br/> 
               
                <label htmlFor ="employeeNic">NIC : </label>
                <input type = "text" 
                placeholder="Enter the NIC No" 
                name = "employeeNic" 
                onChange={(e) =>handleChanges(e)}  
                value = {values.employeeNic} 
                required/>
                    {nicError && <p style = {{ color:"red", fontSize: "12px"}}>{nicError}</p>}
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
            </div>
            );
}
export default EmployeeRegistration;