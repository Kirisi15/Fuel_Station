import React, { useState } from "react";
import axios from "axios";
import CustomerDashboard from "./CustomerDashboard";
import { useNavigate } from "react-router-dom";

const CustomerLogin = ({ isHomepage }) => {
  const [formData, setFormData] = useState({
    username: "",
    password: "",
  });
  const [isLogin, setIsLogin] = useState(false);
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    console.log("Payload being sent:", formData);

    try {
      const response = await axios.post(
        "http://localhost:8080/api/customer/login",
        JSON.stringify(formData),
        { headers: { "Content-Type": "application/json" } }
      );

      console.log("Backend Response:", response.data);
      if (response.data.status === 200) {
        setIsLogin(true); 
        alert("Login successful: " + response.data.data.customerUsername);

        if (isHomepage) {
          navigate("/dashboard/:customerId");
        }
      } else {
        alert("Invalid username or password.");
      }
    } catch (error) {
      console.error("Error:", error);
      if (error.response) {
        console.log("Server Response:", error.response.data); 
        alert(`Login failed: ${error.response.data.message || "Bad Request"}`);
      } else {
        alert("Login failed. Please try again later.");
      }
    }
  };

  return (
    <div>
      {isLogin ? (
        isHomepage ? (
          <CustomerDashboard />
        ) : (
          <div><CustomerDashboard /></div>
        )
      ) : (
        <div className="custom-form">
          <form onSubmit={handleLogin} style={{ width: "300px" }}>
            <h2>User Login</h2>

            <label htmlFor="username">Username:</label>
            <input
              type="text"
              placeholder="Username"
              value={formData.username}
              onChange={(e) =>
                setFormData({ ...formData, username: e.target.value })
              }
              required
            />

            <label htmlFor="password">Password:</label>
            <input
              type="password"
              placeholder="Password"
              value={formData.password}
              onChange={(e) =>
                setFormData({ ...formData, password: e.target.value })
              }
              required
            />
            <br />
            <div className="button-container">
              <button className="btn" type="submit">
                Sign In
              </button>
            </div>
          </form>
        </div>
      )}
    </div>
  );
};

export default CustomerLogin;
