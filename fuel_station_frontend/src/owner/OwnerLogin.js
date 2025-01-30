import React, { useState } from 'react';
import axios from "axios";
import OwnerDashboard from './OwnerDashboard';


const OwnerLogin = () => {
  const [formData, setFormData] = useState({ username: "", password: "" });
    const [isLogin, setIsLogin] = useState(false);

 

  const handleLogin = async (e) => {
    e.preventDefault();
   
try {
  
      const response = await axios.post("http://localhost:8080/api/owners/login",formData);
      console.log("Backend Response: ",response.data);
      if (response.data.status === 200) {
        setIsLogin(true);
        localStorage.setItem("ownerId",response.data.data.ownerId);
        alert("Login successful! Welcome " + response.data.data.username);
    } else {
        alert("Invalid username or password. Please try again.");
    }
 }catch (error) {
  console.error("Error: ", error);
  if (error.response && error.response.status === 401) {
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
          <OwnerDashboard />
        </div>
      ) : (
        <div className="custom-form">
          <form onSubmit={handleLogin}>
            <h2>Owner Login</h2>
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
              <button className="btn" type="submit">
                Sign In
              </button>
          </form>
        </div>
      )}
    </div>
  );
};



export default OwnerLogin;
