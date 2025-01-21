import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import CustomerDashboard from "./CustomerDashboard";

const CustomerLogin = () => {
  const [formData, setFormData] = useState({
    customerUsername: "",
    customerPassword: "",
  });

  const [isLogin,setIsLogin] = useState(false);
 const navigate = useNavigate();

  

  

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(
        "http://localhost:8080/api/customer/login",formData);
        
       console.log("Backend Response: ",response.data);
  
       if(response.data.customerUsername === formData.customerUsername){
    setIsLogin(true);

    alert("Login successful "+response.data.customerUsername);
}else{
  alert("Invalid username  or password");
}
    }catch(error){
      console.error("Error:",error);
      if(error.response && error.response.status === 401){
        alert("Invalid username or password.");
      } else{
        alert("Login failed." +error.message);
      }
    }
  
};

return(
  <div>
  {
    isLogin ? (
       <div>
        <CustomerDashboard />
       </div>
     ):
    
    (
    <form onSubmit={handleLogin}>
         <h2>Login</h2> 
     
      <label htmlFor="customerUsername">Username : </label>
      <input
          type="text"
          placeholder="Username"
          value={formData.customerUsername}
          onChange={(e) => setFormData({ ...formData, customerUsername: e.target.value })}
      />
      <br/><br/>
      <label htmlFor="customerPassword">Password : </label>
      <input
          type="password"
          placeholder="Password"
          value={formData.customerPassword}
          onChange={(e) => setFormData({ ...formData, customerPassword: e.target.value })}
      />
      <br/><br/>
      <button type="submit">Login</button>
  </form>
  )}
    
  </div>
);
}


export default CustomerLogin;
