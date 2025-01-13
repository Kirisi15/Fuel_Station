import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const CustomerLogin = () => {
  const [formData, setFormData] = useState({
    customerUsername: "",
    customerPassword: "",
  });

  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(
        "http://localhost:8080/api/customers/login",
        {
          customerUsername: formData.customerUsername,
          customerPassword: formData.customerPassword,
        }
      );

      // Assuming the API returns a customer object and token
      const { customerId } = response.data;

      // Save customerId to localStorage or state management (like Redux)
      localStorage.setItem("customerId", customerId);

      // Redirect to the Customer Dashboard
      navigate(`/dashboard/${customerId}`);
    } catch (error) {
      setErrorMessage("Invalid username or password. Please try again.");
    }
  };

  return (
    <div>
      <h2>Customer Login</h2>
      {errorMessage && <p style={{ color: "red" }}>{errorMessage}</p>}
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="customerUsername">Username:</label>
          <input
            type="text"
            id="customerUsername"
            name="customerUsername"
            value={formData.customerUsername}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label htmlFor="customerPassword">Password:</label>
          <input
            type="password"
            id="customerPassword"
            name="customerPassword"
            value={formData.customerPassword}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit">Login</button>
      </form>
    </div>
  );
};

export default CustomerLogin;
