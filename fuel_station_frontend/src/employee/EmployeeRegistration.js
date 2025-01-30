import { useState, useEffect } from "react";
import axios from "axios";
import { ValidatePassword } from '../formValidation/ValidatePassword';
import { ValidateNIC } from '../formValidation/ValidateNIC';
import { useNavigate } from "react-router-dom";
import "../components/formStyles.css";

function EmployeeRegistration() {
  
    const navigate = useNavigate(); 
    const [values, setEmployees] = useState({
        employeeName: '',
        employeeNic: '',
        employeeUsername: '',
        employeeContactnumber: '',
        employeePassword: '',
        confirmPassword: ''
    });

    const [existingUsers, setExistingUsers] = useState([]);
    const [passwordError, setPasswordError] = useState("");
    const [nicError, setNicError] = useState("");
    const [valPasswordError, setValPasswordError] = useState("");
    const [uniqueError, setUniqueError] = useState("");
    const employeeId=   localStorage.getItem("employeeId");
   

    const handleChanges = (e) => {
        setEmployees({ ...values, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!ValidateNIC(values.employeeNic)) {
            setNicError("Invalid NIC");
            return;
        }
        setNicError("");

        if (!ValidatePassword(values.employeePassword)) {
            setValPasswordError("Password must be at least 8 characters long, contain at least one uppercase letter, one number, and one special character (e.g., @$!%*?&).");
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
                        : "ContactNumber"
                } already exists.`
            );
            return;
        }
        setUniqueError("");

        const stationId = localStorage.getItem("stationId");
        if (!stationId) {
            alert("Station ID is missing.");
            return;
        }

        try {
          
        
            const response = await axios.post(`http://localhost:8080/api/employee/register`, values, {
                headers: { "Content-Type": "application/json" },
            });
      const employeeId=  localStorage.setItem("EmployeeId",response.data.employeeId);
             console.log(employeeId);
            console.log("Response:", response.data);

            setEmployees({
                employeeName: '',
                employeeNic: '',
                employeeUsername: '',
                employeeContactnumber: '',
                employeePassword: '',
                confirmPassword: ''
            });

            alert("Employee successfully added");
           
         navigate('/EmpMang');

        } catch (error) {
            console.error("Registration error:", error);
            alert("Network error occurred. Please try again");
        }
    };

   
    useEffect(() => {
        const fetchUsers = async () => {
            try {
                localStorage.getItem("employeeId");
              console.log(employeeId)
                    const response = await axios.get(`http://localhost:8080/api/employee/${employeeId}`);
                    setExistingUsers(response.data);            
            } catch (error) {
                console.error("Error fetching existing users:", error);
            }
        };
        fetchUsers();
    }, []);

    return (
         <div className="custom-form">
         <form onSubmit={handleSubmit}>
          <h2>Employee Registration</h2>
          <label htmlFor="employeeName">Employee Name:</label>
          <input
            type="text"
            placeholder="Enter employee name"
            name="employeeName"
            onChange={handleChanges}
            required
            value={values.employeeName}
          />
          
          <label htmlFor="employeeNic">NIC:</label>
          <input
            type="text"
            placeholder="Enter NIC No"
            name="employeeNic"
            onChange={handleChanges}
            required
            value={values.employeeNic}
          />
          {nicError && <p>{nicError}</p>}
  
          <label htmlFor="employeeContactnumber">Contact Number:</label>
          <input
            type="text"
            placeholder="Enter contact number"
            name="employeeContactnumber"
            onChange={handleChanges}
            required
            value={values.employeeContactnumber}
          />
  
          <label htmlFor="employeeUsername">Username:</label>
          <input
            type="text"
            placeholder="Enter username"
            name="employeeUsername"
            onChange={handleChanges}
            required
            value={values.employeeUsername}
          />
  
          <label htmlFor="employeePassword">Password:</label>
          <input
            type="password"
            placeholder="Enter password"
            name="employeePassword"
            onChange={handleChanges}
            required
            value={values.employeePassword}
          />
          {passwordError && <p>{passwordError}</p>}
          {valPasswordError && <p>{valPasswordError}</p>}
  
          <label htmlFor="confirmPassword">Confirm Password:</label>
          <input
            type="password"
            placeholder="Rewrite password"
            name="confirmPassword"
            onChange={handleChanges}
            required
            value={values.confirmPassword}
          />
          {uniqueError && <p>{uniqueError}</p>}
  
          <div className="button-container">
            <button type="submit" className="btn">Sign Up</button>
            <button type="button" className="btn" onClick={() => navigate("/EmpLogin")}>Sign In</button>
          </div>
        </form>
      </div>
    );
  }

export default EmployeeRegistration;
