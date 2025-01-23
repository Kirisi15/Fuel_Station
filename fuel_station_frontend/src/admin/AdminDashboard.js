import React from 'react'
import { useNavigate } from 'react-router-dom';

const AdminDashboard = () => {
  const navigate = useNavigate();

  return (
    <div>
      <h1>Admin Dashboard</h1>
      <button type = "button" onClick={() => navigate("/viewStations")}>View Stations</button>
      <button>View Employees</button>

    </div>
  )
}

export default AdminDashboard
