import React, { useState } from 'react';
import axios from "axios";


const OwnerLogin = () => {
  const [formData, setFormData] = useState({ username: "", password: "" });
    const [isLogin, setIsLogin] = useState(false);

 

  const handleLogin = async (e) => {
    e.preventDefault();
    
try {
      const response = await axios.post("http://localhost:8080/api/owners/login",formData);
      if (response.data.username === formData.username) {
        setIsLogin(true);
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
    <div>{
    
       ( <form onSubmit={handleLogin}>
         <h2>Owner Login</h2>
        <label htmlFor="username">Username : </label>
        <input
          type="text"
          placeholder="username"
          value={formData.username}
          onChange={(e) => setFormData({...formData, username: e.target.value})}
        />
        <br />
        <br />
        <label htmlFor="password">Password : </label>
        <input
          type="password"
          placeholder="password"
          value={formData.password}
          onChange={(e) => setFormData({...formData,password: e.target.value})}
        />
        <br/><br/>
        <button type="submit">Login</button>
        
       
      </form>
      )}
    </div>
  );
}

export default OwnerLogin;
