import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import EmployeeRegistration from "../employee/EmployeeRegistration";

const ManageEmployees = ({ stationId }) => {
  const [employees, setEmployees] = useState([]);
  const navigate = useNavigate(); 


  useEffect(() => {
    const fetchEmployees = async () => {
      try {
        const response = await fetch(
          `https://api.example.com/stations/${stationId}/employees`
        );
        if (!response.ok) {
          throw new Error("Failed to fetch employees");
        }
        const data = await response.json();
        setEmployees(data);
      } catch (error) {
        console.error("Error fetching employees:", error);
      }
    };

    fetchEmployees();
  }, [stationId]);

  // Delete an employee
  const handleDeleteEmployee = async (employeeId) => {
    const confirmDelete = window.confirm("Are you sure you want to delete this employee?");
    if (!confirmDelete) return;

    try {
      const response = await fetch(
        `https://api.example.com/fuelStation/${stationId}/employee/${employeeId}`,
        {
          method: "DELETE",
        }
      );
      if (!response.ok) {
        throw new Error("Failed to delete employee");
      }
      setEmployees((prev) => prev.filter((emp) => emp.id !== employeeId));
    } catch (error) {
      console.error("Error deleting employee:", error);
    }
  };

  
  const handleNavigateToAddEmployee = () => {
    navigate(`/EmpReg`);
    <EmployeeRegistration/>
  };

  return (
    <div>
      <h2>Manage Employees for Station {stationId}</h2>

      
      <h3>Employee List</h3>
      {employees.length === 0 ? (
        <p>No employees found.</p>
      ) : (
        <ul>
          {employees.map((employee) => (
            <li key={employee.employeeId}>
              <strong>{employee.employeeName}</strong> - {employee.position} -{" "}
              {employee.contact}
              <button
                onClick={() => handleDeleteEmployee(employee.id)}
                style={{
                  marginLeft: "10px",
                  backgroundColor: "red",
                  color: "white",
                  border: "none",
                  cursor: "pointer",
                  padding: "5px 10px",
                }}
              >
                Delete
              </button>
            </li>
          ))}
        </ul>
      )}

      {/* Redirect to Employee Registration */}
      <button
        onClick={handleNavigateToAddEmployee}
        style={{
          marginTop: "20px",
          backgroundColor: "blue",
          color: "white",
          padding: "10px 15px",
          border: "none",
          cursor: "pointer",
        }}
      >
        Add Employee
      </button>
    </div>
  );
};

export default ManageEmployees;
