// UpdateOwnerDetails.js
import { useState, useEffect } from "react";
import axios from "axios";

function UpdateOwnerDetails() {
  const [ownerDetails, setOwnerDetails] = useState({
    name: "",
    nic: "",
    email: "",
    contactNumber: ""
  });

  useEffect(() => {
    const ownerId = localStorage.getItem("ownerId");
    console.log(ownerId) ;
    if (!ownerId) {
      console.error("No ownerId found in localStorage");
      return;
    }

    const fetchOwnerDetails = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/owners/${ownerId}`);
        console.log(response.data);
        setOwnerDetails(response.data.data);
      } catch (error) {
        console.error("Error fetching owner details:", error);
      }
    };

    fetchOwnerDetails();
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setOwnerDetails((prevDetails) => ({
      ...prevDetails,
      [name]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const ownerId = localStorage.getItem("ownerId");
    if (!ownerId) {
      alert("Owner ID not found. Please try again.");
      return;
    }

    try {
      const response = await axios.put(
        `http://localhost:8080/api/owners/${ownerId}`,
        ownerDetails,   { headers: { "Content-Type": "application/json" } }
      );
      console.log("Owner details updated:", response.data);
      alert("Owner details updated successfully!");
    } catch (error) {
      console.error("Error updating owner details:", error);
      alert("Error updating details. Please try again.");
    }
  };

  return (
    <div>
      <h1>Update Owner Details</h1>
      <form onSubmit={handleSubmit}>
        <label>
          Name:
          <input
            type="text"
            name="name"
            value={ownerDetails.name}
            onChange={handleChange}
            required
          />
        </label>
        <br />
        <br />
        <label>
          NIC:
          <input
            type="text"
            name="nic"
            value={ownerDetails.nic}
            onChange={handleChange}
            required
          />
        </label>
        <br />
        <br />
        <label>
          Email:
          <input
            type="email"
            name="email"
            value={ownerDetails.email}
            onChange={handleChange}
            required
          />
        </label>
        <br />
        <br />
        <label>
          Contact Number:
          <input
            type="text"
            name="contactNumber"
            value={ownerDetails.contactNumber}
            onChange={handleChange}
            required
          />
        </label>
        <br />
        <br />
        <button type="submit">Save Changes</button>
      </form>
    </div>
  );
}

export default UpdateOwnerDetails;
