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
       localStorage.setItem('customerId', response.data.customerId);

       
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
  {isLogin ? (
        <div>
          <CustomerDashboard />
        </div>
      ) : (
        <div className="custom-form">
            <form onSubmit={handleLogin} style={{ width: "300px", height: "" }}>
            <h2>User Login</h2>
            <label htmlFor="username">Username:</label>
            <input
              type="text"
              placeholder="Username"
              value={formData.customerUsername}
              onChange={(e) =>
                setFormData({ ...formData, customerUsername: e.target.value })
              }
              required
            />
          <label htmlFor="password">Password:</label>           
            <input
              type="password"
              placeholder="Password"
              value={formData.customerPassword}
              onChange={(e) =>
                setFormData({ ...formData, customerPassword: e.target.value })
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


export default CustomerLogin;
