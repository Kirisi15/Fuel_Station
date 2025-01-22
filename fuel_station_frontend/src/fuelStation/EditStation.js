
import React, { useState ,useEffect} from "react";


const EditStation = () => {
  const [stationName, setStationName] = useState("");
  const [address, setaddress] = useState("");
  const [licenseNumber,setlicenseNumber]=useState("");
  const [contactNumber,setcontactNumber]=useState("");

  useEffect(() => {
    const fetchStationDetails = async () => {
      try {
        
        const response = await fetch("http://localhost:8080/{id}");
        if (!response.ok) {
          throw new Error("Failed to fetch station details");
        }
        const data = await response.json();

      
        setStationName(data.stationName || "");
        setaddress(data.address || "");
        setlicenseNumber(data.licenseNumber || "");
        setcontactNumber(data.contactNumber || "");
      } catch (error) {
        console.error("Error fetching station details:", error);
      }
    };

    fetchStationDetails();
  }, [])

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Station updated:", { stationName, address,licenseNumber,contactNumber });
  };

 
  const handleDeleteStation = () => {
    const confirmDelete = window.confirm(
      `Are you sure you want to delete the station "${stationName}"?`
    );
    if (confirmDelete) {
      console.log("Station deleted:", { stationName, address,licenseNumber,contactNumber });
      
      setStationName("");
      setaddress("");
      setlicenseNumber("");
        setcontactNumber("");
      
    }
  };

  return (
    <div>
      <h2>Edit Station</h2>
      <form onSubmit={handleSubmit}>
        <label>
          Station Name:
          <input
            type="text"
            value={stationName}
            onChange={(e) => setStationName(e.target.value)}
            required
          />
        </label><br/> <br/>
        <label>
          Address:
          <input
            type="text"
            value={address}
            onChange={(e) => setaddress(e.target.value)}
            required
          />
        </label><br/><br/>
        <label>
          LicenseNumber:
          <input
            type="text"
            value={licenseNumber}
            onChange={(e) => setlicenseNumber(e.target.value)}
            required
          />
        </label><br/><br/>
        <label>
          ContactNumber:
          <input
            type="text"
            value={contactNumber}
            onChange={(e) => setcontactNumber(e.target.value)}
            required
          />
        </label><br/><br/>
        
        <button type="submit"style={{
          marginTop: "10px",
          backgroundColor: "red",
          color: "white",
          padding: "10px 15px",
          border: "none",
          cursor: "pointer",
        }}>Update Station</button>
      </form>
      
      <button
        onClick={handleDeleteStation}
        style={{
          marginTop: "10px",
          backgroundColor: "red",
          color: "white",
          padding: "10px 15px",
          border: "none",
          cursor: "pointer",
        }}
      >
        Delete Station
      </button>
    </div>
  );
};

export default EditStation;
