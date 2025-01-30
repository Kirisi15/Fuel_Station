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
       if (response.data.status === 200) {
       localStorage.setItem('customerId', response.data.data.customerId);
setIsLogin(true);
localStorage.setItem("customerId",response.data.data.customerId);
    alert("Login successful "+response.data.data.customerUsername);
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
          <form onSubmit={handleLogin}>
            <h2>Customer Login</h2>

            <input
              type="text"
              placeholder="Username"
              value={formData.customerUsername}
              onChange={(e) =>
                setFormData({ ...formData, customerUsername: e.target.value })
              }
              required
            />
            <br />
            <input
              type="password"
              placeholder="Password"
              value={formData.customerPassword}
              onChange={(e) =>
                setFormData({ ...formData, customerPassword: e.target.value })
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
                onClick={() => navigate("/OwnerRegistration")}
              >
                Register
              </button>
            </div>
          </form>
        </div>
      )}
    </div>
  );
};


export default CustomerLogin;
