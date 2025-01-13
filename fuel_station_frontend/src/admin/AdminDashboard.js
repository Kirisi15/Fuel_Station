import React, { useEffect, useState } from 'react'
import axios from 'axios';
import './Admin.css';

const AdminDashboard = () => {
    const [stations, setStations] = useState([]);

    useEffect(() => {
        axios 
            .get("http://localhost:8080/fuel-stations")
            .then((response) => {
                setStations(response.data);
            })
            .catch((error) => {
                console.error("Error in fetching: ",error);
            });
    },[]);

  return (
    <div>
      <h1>Admin Dashboard</h1>
        <div className = "stations-container">
            {stations.map((station) => (
            <div key={station.stationId} className='station-box'>
                    <h2>{station.stationName}</h2>
                    <p><strong>Fuel Type :</strong>Fuel Type : {station.fuelType}</p>
                    <p><strong>Address :</strong> {station.address}</p>
                    <p><strong>License Number :</strong> {station.licenseNumber}</p>
                    <p><strong>ContactNumber :</strong> {station.contactNumber}</p>
            </div> 
            ))}
        </div>
    </div>
  )
}

export default AdminDashboard
