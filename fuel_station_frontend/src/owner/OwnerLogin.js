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
      if (response.data.username === formData.username) {
        setIsLogin(true);
        localStorage.setItem("ownerId",response.data.ownerId);
        alert("Login successful! Welcome " + response.data.username);
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
            <br />
            <div className="button-container">
              <button className="btn" type="submit">
                Login
              </button>
              <button
                className="btn"
                type="button"
                onClick={() => alert("Redirecting to registration...")}
              >
                SignUp
              </button>
            </div>
          </form>
        </div>
      )}
    </div>
  );
};



export default OwnerLogin;
