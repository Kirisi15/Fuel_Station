import React, { useState } from "react";

import { useNavigate } from "react-router-dom";

const StationDashboard = () => {
    const [activeTab, setActiveTab] = useState("");
    const navigate = useNavigate();

    const handleNavigation = (path) => {
        setActiveTab(path);
        navigate(path);
    };

    return (
        <div>
            <h1>Fuel Station Dashboard</h1>
            <div className="tab-menu">
                <button onClick={() => handleNavigation("/edit-station")}>Edit Station</button><br/><br/>
                <button onClick={() => handleNavigation("/add-employee")}>Add Employee</button><br/><br/>
                <button onClick={() => handleNavigation("/transaction-details")}>Transaction Details</button><br/><br/>
                <button onClick={() => handleNavigation("/fuel-management")}>Fuel Management</button><br/><br/>
            </div>
        </div>
    );
};

export default StationDashboard;
