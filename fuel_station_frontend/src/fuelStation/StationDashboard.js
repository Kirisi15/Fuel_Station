import React, { useState } from "react";

const StationDashboard = () => {
  const [activeTab, setActiveTab] = useState("dashboard");

  // Handlers for each functionality
  const handleEditStation = () => {
    console.log("Editing station...");
  };

  const handleAddEmployee = () => {
    console.log("Adding employee...");
  };

  const handleViewTransactions = () => {
    console.log("Viewing transactions...");
  };

  const handleFuelManagement = () => {
    console.log("Managing fuel...");
  };

  return (
    <div className="station-dashboard">
      <h1>Station Dashboard</h1>
      <div className="tabs">
        <button onClick={() => setActiveTab("editStation")}>
          Edit Station
        </button>
        <button onClick={() => setActiveTab("addEmployee")}>
          Add Employee
        </button>
        <button onClick={() => setActiveTab("transactionDetails")}>
          Transaction Details
        </button>
        <button onClick={() => setActiveTab("fuelManagement")}>
          Fuel Management
        </button>
      </div>

      <div className="tab-content">
        {activeTab === "editStation" && (
          <div>
            <h2>Edit Station</h2>
            <button onClick={handleEditStation}>Edit</button>
          </div>
        )}
        {activeTab === "addEmployee" && (
          <div>
            <h2>Add Employee</h2>
            <form onSubmit={handleAddEmployee}>
              <label>
                Employee Name:
                <input type="text" name="employeeName" required />
              </label>
              <label>
                Role:
                <input type="text" name="role" required />
              </label>
              <button type="submit">Add Employee</button>
            </form>
          </div>
        )}
        {activeTab === "transactionDetails" && (
          <div>
            <h2>Transaction Details</h2>
            <button onClick={handleViewTransactions}>View Transactions</button>
          </div>
        )}
        {activeTab === "fuelManagement" && (
          <div>
            <h2>Fuel Management</h2>
            <button onClick={handleFuelManagement}>Manage Fuel</button>
          </div>
        )}
      </div>
    </div>
  );
};
i
export default StationDashboard;