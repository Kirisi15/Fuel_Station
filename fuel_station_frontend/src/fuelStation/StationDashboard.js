import React, { useState } from "react";
import '../components/Dashboard.css';
import { useNavigate } from "react-router-dom";
const stationId = localStorage.getItem("stationId");
console.log(stationId)
const StationDashboard = () => {
    const [activeTab, setActiveTab] = useState("");
    const navigate = useNavigate();

    const handleNavigation = (path) => {
        
        setActiveTab(path);
        navigate(path);
    };

    return (
        <div>
            <h2>Fuel Station Dashboard</h2>
            <div className="dashboard-btn">
                <button type="submit" className="btn" onClick={() => handleNavigation("/EditStation")}>EditStation</button><br/><br/>
                <button type="submit" className="btn" onClick={() => handleNavigation("/EmpMang")}>Mang Employee</button><br/><br/>
                <button type="submit" className="btn" onClick={() => handleNavigation("/TransactionTable")}>Transaction Details</button><br/><br/>
            </div>
        </div>
    );
};

export default StationDashboard;
