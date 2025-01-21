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
                <button onClick={() => handleNavigation("/EditStation")}>EditStation</button><br/><br/>
                <button onClick={() => handleNavigation("/EmpMang")}>Mang Employee</button><br/><br/>
                <button onClick={() => handleNavigation("/TransactionTable")}>Transaction Details</button><br/><br/>
                <button onClick={() => handleNavigation("/AddFuel")}>AddFuel</button><br/><br/>
            </div>
        </div>
    );
};

export default StationDashboard;
