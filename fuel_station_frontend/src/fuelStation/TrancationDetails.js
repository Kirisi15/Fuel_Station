import React, { useEffect, useState } from "react";
import { getTransactions, deleteTransaction, getTransactionById } from "../services/transactionService";
import TransactionForm from "./TransactionForm";

const Transactions = () => {
    const [transactions, setTransactions] = useState([]);
    const [editingTransaction, setEditingTransaction] = useState(null);

    useEffect(() => {
        fetchTransactions();
    }, []);

    const fetchTransactions = async () => {
        const data = await getTransactions();
        setTransactions(data);
    };

    const handleDelete = async (id) => {
        const success = await deleteTransaction(id);
        if (success) {
            alert("Transaction deleted successfully!");
            fetchTransactions();
        }
    };

    const handleEdit = async (id) => {
        const transaction = await getTransactionById(id);
        setEditingTransaction(transaction);
    };

    const handleTransactionSaved = () => {
        fetchTransactions();
        setEditingTransaction(null); // Reset form after saving
    };

    return (
        <div>
            <h2>Fuel Station Transactions</h2>

            {/* Render Add/Edit Transaction Form */}
            <TransactionForm transactionToEdit={editingTransaction} onTransactionSaved={handleTransactionSaved} />

            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Fuel Type</th>
                        <th>Amount</th>
                        <th>Price</th>
                        <th>Date</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {transactions.map(transaction => (
                        <tr key={transaction.id}>
                            <td>{transaction.id}</td>
                            <td>{transaction.fuelType}</td>
                            <td>{transaction.amount}</td>
                            <td>${transaction.price}</td>
                            <td>{new Date(transaction.date).toLocaleString()}</td>
                            <td>
                                <button onClick={() => handleEdit(transaction.id)}>Edit</button>
                                <button onClick={() => handleDelete(transaction.id)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default Transactions;
