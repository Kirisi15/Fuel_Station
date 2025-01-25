import React, { useState, useEffect } from "react";
import axios from "axios";
import { ValidateEmail } from "../formValidation/ValidateEmail";
import { ValidatePassword } from "../formValidation/ValidatePassword";
import { useNavigate } from "react-router-dom";
import { ValidateNIC } from "../formValidation/ValidateNIC";
import '../components/formStyles.css';


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
        //navigate("/homePageDesign");
      })
      .catch((error) => {
        console.error("Registration error:", error);
        alert("An error occurred during registration. Please try again.");
      });
  };

  return (
    <div>
      <div className="custom-form">
        <form onSubmit={handleSubmit}>
          <h2>Owner Registration</h2>
          <input
            type="text"
            placeholder="name"
            value={formData.name}
            onChange={(e) => setFormData({ ...formData, name: e.target.value })}
            required
          />
          <br />
          <input
            type="text"
            placeholder="NIC number"
            value={formData.nic}
            onChange={(e) => setFormData({ ...formData, nic: e.target.value })}
            required
          />
          {nicError && <p>{nicError}</p>}
          <br />
          <input
            type="text"
            placeholder="email"
            value={formData.email}
            onChange={(e) => setFormData({ ...formData, email: e.target.value })}
            required
          />
          {emailError && <p>{emailError}</p>}
          <br />
          <input
            type="text"
            placeholder="contact number"
            value={formData.contactNumber}
            onChange={(e) =>
              setFormData({ ...formData, contactNumber: e.target.value })
            }
            required
          />
          <br />
          <input
            type="text"
            placeholder="username"
            value={formData.username}
            onChange={(e) =>
              setFormData({ ...formData, username: e.target.value })
            }
            required
          />
          <br />
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
          <br />
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

          {uniqueError && <p>{uniqueError}</p>}

          <button className="btn" type="submit">SignUp</button>
          <button className="btn" type="button" onClick={() => navigate("/OwnerLogin")}>
            SignIn
          </button>
        </form>
      </div>
    </div>
  );
};

export default OwnerRegistration;
