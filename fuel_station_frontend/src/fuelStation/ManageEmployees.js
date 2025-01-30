import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const ManageEmployees = ({ stationId }) => {
  const navigate = useNavigate();
  const [employees, setEmployees] = useState([]);

  useEffect(() => {
    const fetchEmployees = async () => {
      try {
        const stationId = localStorage.getItem("stationId");
        console.log(stationId);
        const employeeId=localStorage.getItem("employeeId");
        console.log(employeeId);
        const response = await axios.get(
          
          `http://localhost:8080/api/employee/${stationId}`
        );
console.log(response.data);
        setEmployees(response.data);
      } catch (error) {
        console.error("Error fetching employees:", error);
      }
    };

    fetchEmployees();
  }, [stationId]);

  const handleDeleteEmployee = async (employeeId) => {
    const confirmDelete = window.confirm("Are you sure you want to delete this employee?");
    if (!confirmDelete) return;

    try {
      await axios.delete(`http://localhost:8080/api/employee/${employeeId}`);

      // Remove the deleted employee from the state
      setEmployees((prevEmployees) => prevEmployees.filter(emp => emp.employeeId !== employeeId));
    } catch (error) {
      console.error("Error deleting employee:", error);
    }
  };

  const handleNavigateToAddEmployee = () => {
    navigate(`/EmpReg`);
  };

  return (
    <div>
      <h2>Manage Employees for Station {stationId}</h2>

      <button
        onClick={handleNavigateToAddEmployee}
        style={{
          marginBottom: "20px",
          backgroundColor: "blue",
          color: "white",
          padding: "10px 15px",
          border: "none",
          cursor: "pointer",
        }}
      >
        Add Employee
      </button>

      {employees.length === 0 ? (
        <p>No employees found.</p>
      ) : (
        <table style={{ width: "100%", borderCollapse: "collapse" }}>
          <thead>
            <tr>
              <th style={{ border: "1px solid #ddd", padding: "8px" }}>Employee ID</th>
              <th style={{ border: "1px solid #ddd", padding: "8px" }}>Name</th>
              <th style={{ border: "1px solid #ddd", padding: "8px" }}>NIC</th>
              <th style={{ border: "1px solid #ddd", padding: "8px" }}>Contact Number</th>
              <th style={{ border: "1px solid #ddd", padding: "8px" }}>Actions</th>
            </tr>
          </thead>
          <tbody>
            {employees.map((employee) => (
              <tr key={employee.empId}>
                <td style={{ border: "1px solid #ddd", padding: "8px" }}>{employee.employeeId}</td>
                <td style={{ border: "1px solid #ddd", padding: "8px" }}>{employee.employeeName}</td>
                <td style={{ border: "1px solid #ddd", padding: "8px" }}>{employee.employeeNic}</td>
                <td style={{ border: "1px solid #ddd", padding: "8px" }}>{employee.employeeContactnumber}</td>
                <td style={{ border: "1px solid #ddd", padding: "8px" }}>
                  <button
                    onClick={() => handleDeleteEmployee(employee.employeeId)}
                    style={{
                      backgroundColor: "red",
                      color: "white",
                      border: "none",
                      cursor: "pointer",
                      padding: "5px 10px",
                    }}
                  >
                  Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
      </div>
  );
};

export default ManageEmployees;
