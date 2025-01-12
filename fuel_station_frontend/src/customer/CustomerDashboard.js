import React, { useEffect, useState } from "react";
import axios from "axios";

const API_BASE = "http://localhost:8080";

// API service functions
const getCustomerById = (id) => axios.get(`${API_BASE}/api/customer/${id}`);
const getTransactions = () => axios.get(`${API_BASE}/transactions`);

const CustomerDashboard = ({ customerId }) => {
  const [customer, setCustomer] = useState(null);
  const [transactions, setTransactions] = useState([]);
  const [fuelQuota, setFuelQuota] = useState(100); // Example: Default fuel quota

  useEffect(() => {
    // Fetch customer details
    getCustomerById(customerId)
      .then((res) => setCustomer(res.data))
      .catch((err) => console.error("Error fetching customer details:", err));

    // Fetch transactions
    getTransactions()
      .then((res) => {
        // Filter transactions for the customer's vehicles
        const customerTransactions = res.data.filter(
          (transaction) => transaction.vehicle.customerId === customerId
        );
        setTransactions(customerTransactions);
      })
      .catch((err) => console.error("Error fetching transactions:", err));
  }, [customerId]);

  // Calculate pumped fuel and remaining fuel
  const pumpedFuel = transactions.reduce((sum, tx) => sum + tx.quantity, 0);
  const remainingFuel = fuelQuota - pumpedFuel;

  return (
    <div style={{ padding: "20px" }}>
      <h1>Customer Dashboard</h1>
      {customer && (
        <div>
          <h2>Welcome, {customer.customerName}</h2>
          <p>
            <strong>NIC:</strong> {customer.customerNIC}
          </p>
          <p>
            <strong>Email:</strong> {customer.customerEmail}
          </p>
        </div>
      )}
      <div>
        <h3>Fuel Quota Summary</h3>
        <p>
          <strong>Total Quota:</strong> {fuelQuota} Liters
        </p>
        <p>
          <strong>Pumped Fuel:</strong> {pumpedFuel} Liters
        </p>
        <p>
          <strong>Remaining Fuel:</strong> {remainingFuel} Liters
        </p>
      </div>
      <div>
        <h3>Recent Transactions</h3>
        <table border="1" cellPadding="10">
          <thead>
            <tr>
              <th>Date</th>
              <th>Quantity (Liters)</th>
              <th>Fuel Type</th>
            </tr>
          </thead>
          <tbody>
            {transactions.map((tx) => (
              <tr key={tx.transactionId}>
                <td>{new Date(tx.dateTime).toLocaleString()}</td>
                <td>{tx.quantity}</td>
                <td>{tx.fuel.fuelType}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default CustomerDashboard;
