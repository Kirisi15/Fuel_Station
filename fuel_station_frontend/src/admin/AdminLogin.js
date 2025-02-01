import React, { useState } from "react";
import axios from "axios";
import AdminDashboard from "./AdminDashboard";

const AdminLogin = () => {
  const [formData, setFormData] = useState({ username: "", password: "" });
  const [isLogin, setIsLogin] = useState(false);

  const handleLogin = async (e) => {
    e.preventDefault();

    console.log("Request Data: ", formData);

    try {
      const response = await axios.post(
        "http://localhost:8080/api/admin/login",
        formData, // Pass as JSON
        { headers: { "Content-Type": "application/json" } }
      );

      console.log("Backend Response: ", response.data);
      if (response.data.status === 200) {
     // if (response.data.username === formData.username) {
        setIsLogin(true);
        alert("Login successful! Welcome " + response.data.data.username);
      // } else {
      //   alert("Invalid username or password. Please try again.");
      // }
    }
    } catch (error) {
      console.error("Error: ", error);

      if (error.response) {
        if (error.response.status === 400) {
          alert("Bad Request: " + (error.response.data.message || "Invalid login data."));
        } else if (error.response.status === 401) {
          alert("Invalid username or password. Please try again.");
        } else {
          alert("Error: " + (error.response.data.message || error.message));
        }
      } else {
        alert("Network error: " + error.message);
      }
    }
  };

  return (
    <div>
      {isLogin ? (
        <AdminDashboard />
      ) : (
        <div className="custom-form">
          <form onSubmit={handleLogin}>
            <h2>Admin Login</h2>
            <label htmlFor="username">Username:</label>
            <input
              type="text"
              placeholder="Username"
              value={formData.username}
              onChange={(e) => setFormData({ ...formData, username: e.target.value })}
              required
            />
            <label htmlFor="password">Password:</label>
            <input
              type="password"
              placeholder="Password"
              value={formData.password}
              onChange={(e) => setFormData({ ...formData, password: e.target.value })}
              required
            />
            <button className="btn" type="submit">
              Sign In
            </button>
          </form>
        </div>
      )}
    </div>
  );
};

export default AdminLogin;
