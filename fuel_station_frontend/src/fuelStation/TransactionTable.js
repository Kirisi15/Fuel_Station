import React, { useEffect, useState } from "react";

const TransactionTable = () => {
  const [transactions, setTransactions] = useState([]);

  // Fetch transactions from the API (replace with your actual API URL)
  useEffect(() => {
    const fetchTransactions = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/transactions"); // Adjust the endpoint as needed
        const data = await response.json();
        setTransactions(data);
      } catch (error) {
        console.error("Error fetching transactions:", error);
      }
    };

    fetchTransactions();
  }, []);

  return (
    <div>
      <h3>Transaction History</h3>
      <table border="1" cellPadding="8" cellSpacing="0">
        <thead>
          <tr>
            <th>Employee ID</th>
            <th>Quantity of Fuel</th>
            <th>Date and Time</th>
            <th>Fuel Type</th>
          </tr>
        </thead>
        <tbody>
          {transactions.map((transaction) => (
            <tr key={transaction.id}>
              <td>{transaction.employeeId}</td>
              <td>{transaction.quantity}</td>
              <td>{new Date(transaction.dateTime).toLocaleString()}</td>
              <td>{transaction.fuelType}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default TransactionTable;