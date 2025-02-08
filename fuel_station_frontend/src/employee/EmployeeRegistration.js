import React, { useState, useEffect } from "react";
import axios from "axios";
import { ValidateEmail } from "../formValidation/ValidateEmail";
import { ValidatePassword } from "../formValidation/ValidatePassword";
import { useNavigate } from "react-router-dom";
import { ValidateNIC } from "../formValidation/ValidateNIC";
import '../components/formStyles.css';

const EmployeeRegistration = () => {
  const stationId = localStorage.getItem("stationId");

  const [formData, setFormData] = useState({
    name: "",
    nic: "",
    email: "",
    contactNumber: "",
    username: "",
    password: "",
    confirmPassword: "",
  });

  const [existingEmployees, setExistingEmployees] = useState([]);
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const [nicError, setNicError] = useState("");
  const [valPasswordError, setValPasswordError] = useState("");
  const [uniqueError, setUniqueError] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    const fetchEmployees = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/employee");
        if (Array.isArray(response.data)) {
          setExistingEmployees(response.data);
        } else {
          console.error("API response is not an array:", response.data);
        }
      } catch (error) {
        console.error("Error fetching existing employees:", error);
      }
    };

    fetchEmployees();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!ValidateEmail(formData.email)) {
      setEmailError("Invalid email");
      return;
    }
    setEmailError("");

    if (!ValidateNIC(formData.nic)) {
      setNicError("Invalid NIC");
      return;
    }
    setNicError("");

    if (!ValidatePassword(formData.password)) {
      setValPasswordError(
        "Password must be at least 8 characters long, contain at least one uppercase letter, one number, and one special character (e.g., @$!%*?&)."
      );
      return;
    }
    setValPasswordError("");

    if (formData.password !== formData.confirmPassword) {
      setPasswordError("The passwords entered do not match");
      return;
    }
    setPasswordError("");

    if (!Array.isArray(existingEmployees)) {
      alert("An error occurred while validating user data. Please try again later.");
      return;
    }

    const duplicateEmployee = existingEmployees.find(
      (employee) =>
        employee.email === formData.email ||
        employee.nic === formData.nic ||
        employee.username === formData.username ||
        employee.contactNumber === formData.contactNumber
    );

    if (duplicateEmployee) {
      setUniqueError(
        `Duplicate entry detected! ${
          duplicateEmployee.email === formData.email
            ? "Email"
            : duplicateEmployee.nic === formData.nic
            ? "NIC"
            : duplicateEmployee.username === formData.username
            ? "Username"
            : "Contact Number"
        } already exists.`
      );
      return;
    }
    setUniqueError("");

    if (!stationId) {
      alert("Error: Station ID is missing. Please log in again.");
      return;
    }

    try {
      await axios.post(
        "http://localhost:8080/api/employee/register",
        {
          name: formData.name,
          nic: formData.nic,
          email: formData.email,
          contactNumber: formData.contactNumber,
          username: formData.username,
          password: formData.password,
          stationId: stationId, // Include station ID
        },
        { headers: { "Content-Type": "application/json" } }
      );

      setFormData({
        name: "",
        nic: "",
        email: "",
        contactNumber: "",
        username: "",
        password: "",
        confirmPassword: "",
      });

      alert("Employee registered successfully!");
      // navigate("/homePageDesign");
    } catch (error) {
      console.error("Registration error:", error);
      alert(error.response?.data?.message || "An error occurred during registration. Please try again.");
    }
  };

  return (
    <div>
      <div className="custom-form">
        <form onSubmit={handleSubmit}>
          <h2>Employee Registration</h2>
          <div className="inputGroup">
            <label htmlFor="name">Name:</label>
            <input
              type="text"
              placeholder="Enter name"
              value={formData.name}
              onChange={(e) => setFormData({ ...formData, name: e.target.value })}
              required
            />
            <label htmlFor="nic">NIC:</label>
            <input
              type="text"
              placeholder="Enter NIC number"
              value={formData.nic}
              onChange={(e) => setFormData({ ...formData, nic: e.target.value })}
              required
            />
            {nicError && <p>{nicError}</p>}
            <label htmlFor="email">Email:</label>
            <input
              type="text"
              placeholder="Enter email"
              value={formData.email}
              onChange={(e) => setFormData({ ...formData, email: e.target.value })}
              required
            />
            {emailError && <p>{emailError}</p>}
            <label htmlFor="contactNumber">ContactNumber:</label>
            <input
              type="text"
              placeholder="contact number"
              value={formData.contactNumber}
              onChange={(e) =>
                setFormData({ ...formData, contactNumber: e.target.value })
              }
              required
            />
            <label htmlFor="username">Username:</label>
            <input
              type="text"
              placeholder="username"
              value={formData.username}
              onChange={(e) =>
                setFormData({ ...formData, username: e.target.value })
              }
              required
            />
            <label htmlFor="password">Password:</label>
            <input
              type="password"
              placeholder="password"
              value={formData.password}
              onChange={(e) =>
                setFormData({ ...formData, password: e.target.value })
              }
              required
            />
            {passwordError && <p>{passwordError}</p>}
            {valPasswordError && <p>{valPasswordError}</p>}
            <label htmlFor="confirmPassword">Confirm Password:</label>
            <input
              type="password"
              placeholder="Rewrite password"
              value={formData.confirmPassword}
              onChange={(e) =>
                setFormData({ ...formData, confirmPassword: e.target.value })
              }
              required
            />
            {uniqueError && <p>{uniqueError}</p>}

            <button type="submit" className="btn">Sign Up</button>
            <button type="button" className="btn" onClick={() => navigate("/EmployeeLogin")}>Sign In</button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default EmployeeRegistration;
