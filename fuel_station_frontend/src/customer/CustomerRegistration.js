import React, { useState } from "react";
import axios from "axios";


const CustomerRegistration = () => {
  const [formData, setFormData] = useState({
    customerNIC: "",
    customerName: "",
    customerEmail: "",
    customerUsername: "",
    customerPassword: "",
    confirmPassword: "",
  });

  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (formData.customerPassword !== formData.confirmPassword) {
      setErrorMessage("Passwords do not match.");
      setSuccessMessage("");
      return;
    }

    try {
      const response = await axios.post(
        "http://localhost:8080/api/customers/register",
        {
          customerNIC: formData.customerNIC,
          customerName: formData.customerName,
          customerEmail: formData.customerEmail,
          customerUsername: formData.customerUsername,
          customerPassword: formData.customerPassword,
        }
      );

      setSuccessMessage("Registration successful!");
      setErrorMessage("");
      setFormData({
        customerNIC: "",
        customerName: "",
        customerEmail: "",
        customerUsername: "",
        customerPassword: "",
        confirmPassword: "",
      });
    } catch (error) {
      setErrorMessage("Registration failed. Please try again.");
      setSuccessMessage("");
    }
  };

  return (
    <div>
      <h2>Customer Registration</h2>
      {successMessage && <p style={{ color: "green" }}>{successMessage}</p>}
      {errorMessage && <p style={{ color: "red" }}>{errorMessage}</p>}
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="customerNIC">Customer NIC:</label>
          <input
            type="text"
            id="customerNIC"
            name="customerNIC"
            value={formData.customerNIC}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label htmlFor="customerName">Customer Name:</label>
          <input
            type="text"
            id="customerName"
            name="customerName"
            value={formData.customerName}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label htmlFor="customerEmail">Customer Email:</label>
          <input
            type="email"
            id="customerEmail"
            name="customerEmail"
            value={formData.customerEmail}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label htmlFor="customerUsername">Customer Username:</label>
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
          <label htmlFor="customerPassword">Customer Password:</label>
          <input
            type="password"
            id="customerPassword"
            name="customerPassword"
            value={formData.customerPassword}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label htmlFor="confirmPassword">Confirm Password:</label>
          <input
            type="password"
            id="confirmPassword"
            name="confirmPassword"
            value={formData.confirmPassword}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit">Register</button>
      </form>
    </div>
  );
};

export default CustomerRegistration;


