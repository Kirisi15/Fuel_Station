import React, { useState } from "react";
import axios from "axios";
import AdminDashboard from "./AdminDashboard";

const AdminLogin = () => {
  const [formData, setFormData] = useState({ adminUsername: "", adminPassword: "" });
  const [isLogin, setIsLogin] = useState(false);

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
        const response = await axios.post("http://localhost:8080/api/admin/login", formData);
        console.log("Backend Response: ", response.data);

        if (response.data.adminUsername === formData.adminUsername) {
            setIsLogin(true);
            alert("Login successful! Welcome " + response.data.adminUsername);
        } else {
            alert("Invalid username or password. Please try again.");
        }
    } catch (error) {
        console.error("Error: ", error);
        if (error.response && error.response.status === 401) {
            setIsLogin(true);
            alert("Invalid username or password. Please try again.");
        } else {
            alert("Login failed: " + error.message);
        }
    }
};

  return (
    <div>
      {isLogin ? (
        <div>
          <AdminDashboard />
        </div>
      ) : (
        <div className="custom-form">
          <form onSubmit={handleLogin}>
            <h2>Admin Login</h2>
            <label htmlFor="username">Username:</label>
            <input
              type="text"
              placeholder="Username"
              value={formData.adminUsername}
              onChange={(e) => setFormData({ ...formData, adminUsername: e.target.value })}
              required
            />
            <label htmlFor="password">Password:</label>
            <input
              type="password"
              placeholder="Password"
              value={formData.adminPassword}
              onChange={(e) => setFormData({ ...formData, adminPassword: e.target.value })}
              required
            />
              <button className="btn" type="submit">Sign In</button>
          </form>
        </div>
      )}
    </div>
  );
};

export default AdminLogin;
