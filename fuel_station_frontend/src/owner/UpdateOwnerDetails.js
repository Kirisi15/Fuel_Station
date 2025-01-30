import { useState, useEffect } from "react";
import axios from "axios";

function UpdateOwnerDetails() {
  const [ownerDetails, setOwnerDetails] = useState({
    name: "",
    nic: "",
    email: "",
    contactNumber: ""
  });
  const [error, setError] = useState(""); // To store error messages

  useEffect(() => {
    const ownerId = localStorage.getItem("ownerId");
    if (!ownerId) {
      setError("No owner ID found in localStorage.");
      console.error("No ownerId found in localStorage");
      return;
    }

    const fetchOwnerDetails = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/owners/${ownerId}`);
        
        if (response.data && response.data.data) {
          setOwnerDetails(response.data.data);
        } else {
          setError("Invalid API response format.");
          console.error("Unexpected API response:", response.data);
        }
      } catch (error) {
        console.error("Error fetching owner details:", error);
        setError("Failed to fetch owner details. Please try again.");
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
      setError("Owner ID not found. Please try again.");
      alert("Owner ID not found. Please try again.");
      return;
    }

    try {
      const requestBody = {
        name: ownerDetails.name.trim(),
        nic: ownerDetails.nic.trim(),
        email: ownerDetails.email.trim(),
        contactNumber: ownerDetails.contactNumber.trim()
      };

      const response = await axios.put(
        `http://localhost:8080/api/owners/${ownerId}`,
        requestBody,
        {
          headers: { "Content-Type": "application/json" }
        }
      );

      console.log("Owner details updated:", response.data);
      alert("Owner details updated successfully!");
      setError(""); // Clear errors on success
    } catch (error) {
      console.error("Error updating owner details:", error);

      if (error.response) {
        setError(`Update failed: ${error.response.data.message || "Bad Request"}`);
      } else {
        setError("Network error. Please try again.");
      }
    }
  };

  return (
    <div>
      <h1>Update Owner Details</h1>
      {error && <p style={{ color: "red" }}>{error}</p>} {/* Display error messages */}
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
        <br /><br />
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
        <br /><br />
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
        <br /><br />
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
        <br /><br />
        <button type="submit">Save Changes</button>
      </form>
    </div>
  );
}

export default UpdateOwnerDetails;
