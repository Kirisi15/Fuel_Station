import React, { useState } from 'react';
import axios from "axios";
import { useNavigate } from "react-router-dom";

const Login = () => {
  const [loginType, setLoginType] = useState("owner");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleLoginTypeChange = (type) => {
    setLoginType(type);
    setError("");
  };

  const handleLogin = async (e) => {
    e.preventDefault();
    

    if (!username || !password) {
      setError("Please enter both username and password.");
      return;
    }

    try {
      const response = await axios.post(
        "http://localhost:8080/api/owners",
        {
          ownerUsername: username,
          ownerPassword: password,
        }
        
      );

      const { ownerId } = response.data;

      localStorage.setItem("ownerId", ownerId);

      alert("Logged in successfully");

      navigate("/OwnerDashboard"); 
    } catch (error) {
      setError("Invalid username or password. Please try again.");
    }

    setUsername("");
    setPassword("");
    setError("");
  };

  return (
    <div>
      <h2>Fuel Station Login</h2>
      <button onClick={() => handleLoginTypeChange("owner")}>
        Owner Login
      </button>
      <button onClick={() => handleLoginTypeChange("station")}>
        Station Login
      </button>

      <form onSubmit={handleLogin}>
        <label htmlFor="ownerUsername">Username:</label>
        <input
          type="text"
          placeholder="UserName"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <br />
        <br />
        <label htmlFor="ownerPassword">Password:</label>
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <br />
        <br />
        <button type="submit">Login</button>
        <br />
        <br />
        {error && <p style={{ color: "red" }}>{error}</p>}
      </form>
    </div>
  );
};

export default Login;
