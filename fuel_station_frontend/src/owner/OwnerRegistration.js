import React, { useState, useEffect } from "react";
import axios from "axios";
import { ValidateEmail } from "../formValidation/ValidateEmail";
import { ValidatePassword } from "../formValidation/ValidatePassword";
import { useNavigate } from "react-router-dom";
import { ValidateNIC } from "../formValidation/ValidateNIC";

const OwnerRegistration = () => {
  const [formData, setFormData] = useState({
    name: "",
    nic: "",
    email: "",
    contactNumber: "",
    username: "",
    password: "",
    confirmPassword: "",
  });

  const [existingUsers, setExistingUsers] = useState([]);
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const [nicError, setNicError] = useState("");
  const [valPasswordError, setValPasswordError] = useState("");
  const [uniqueError, setUniqueError] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/owners");
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

  const handleSubmit = (e) => {
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

    if (!Array.isArray(existingUsers)) {
      alert("An error occurred while validating user data. Please try again later.");
      return;
    }

    const duplicateUser = existingUsers.find(
      (user) =>
        user.email === formData.email ||
        user.nic === formData.nic ||
        user.username === formData.username ||
        user.contactNumber === formData.contactNumber
    );

    if (duplicateUser) {
      setUniqueError(
        `Duplicate entry detected! ${
          duplicateUser.email === formData.email
            ? "Email"
            : duplicateUser.nic === formData.nic
            ? "NIC"
            : duplicateUser.username === formData.username
            ? "Username"
            : "Contact Number"
        } already exists.`
      );
      return;
    }
    setUniqueError("");

    axios
      .post("http://localhost:8080/api/owners", formData, {
        headers: { "Content-Type": "application/json" },
      })
      .then(() => {
        setFormData({
          name: "",
          nic: "",
          email: "",
          contactNumber: "",
          username: "",
          password: "",
          confirmPassword: "",
        });
        alert("Owner registered successfully!");
        navigate("/login");
      })
      .catch((error) => {
        console.error("Registration error:", error);
        alert("An error occurred during registration. Please try again.");
      });
  };

  return (
    <div>
      <h1>Fuel Station Owner Registration</h1>
      <form onSubmit={handleSubmit}>
        <label htmlFor="name">Name:</label>
        <input
          type="text"
          placeholder="Enter name"
          value={formData.name}
          onChange={(e) => setFormData({ ...formData, name: e.target.value })}
          required
        />
        <br />
        <br />

        <label htmlFor="nic">NIC:</label>
        <input
          type="text"
          placeholder="Enter NIC number"
          value={formData.nic}
          onChange={(e) => setFormData({ ...formData, nic: e.target.value })}
          required
        />
        {nicError && <p style={{ color: "red", fontSize: "12px" }}>{nicError}</p>}
        <br />
        <br />

        <label htmlFor="email">Email:</label>
        <input
          type="text"
          placeholder="Enter email"
          value={formData.email}
          onChange={(e) => setFormData({ ...formData, email: e.target.value })}
          required
        />
        {emailError && <p style={{ color: "red", fontSize: "12px" }}>{emailError}</p>}
        <br />
        <br />

        <label htmlFor="contactNumber">Contact Number:</label>
        <input
          type="text"
          placeholder="Enter contact number"
          value={formData.contactNumber}
          onChange={(e) =>
            setFormData({ ...formData, contactNumber: e.target.value })
          }
          required
        />
        <br />
        <br />

        <label htmlFor="username">Username:</label>
        <input
          type="text"
          placeholder="Enter username"
          value={formData.username}
          onChange={(e) =>
            setFormData({ ...formData, username: e.target.value })
          }
          required
        />
        <br />
        <br />

        <label htmlFor="password">Password:</label>
        <input
          type="password"
          placeholder="Enter password"
          value={formData.password}
          onChange={(e) =>
            setFormData({ ...formData, password: e.target.value })
          }
          required
        />
        {passwordError && (
          <p style={{ color: "red", fontSize: "12px" }}>{passwordError}</p>
        )}
        {valPasswordError && (
          <p style={{ color: "red", fontSize: "12px" }}>{valPasswordError}</p>
        )}
        <br />
        <br />

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
        <br />
        <br />

        {uniqueError && (
          <p style={{ color: "red", fontSize: "12px" }}>{uniqueError}</p>
        )}

        <button name="register" type="submit">
          SignUp
        </button>
        <button type="button" onClick={() => navigate("/login")}>
          SignIn
        </button>
      </form>
    </div>
  );
};

export default OwnerRegistration;
