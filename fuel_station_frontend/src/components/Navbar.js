import React from 'react'
import { useNavigate } from 'react-router-dom';

const Navbar = () => {
    const navigate = useNavigate();

    const routes = {
        Reg: "/",
        ownerReg: "/ownerReg",
        cusReg: "/cusReg",
        adminLog: "/adminLog",
        cusLogin: "/cusLogin"
      };

      const handleSelect = (event) => {
        const value = event.target.value;
        navigate(routes[value] || "/"); // Navigate to the selected route or default to "/"
      };

  return (
    <div>
        <nav>
      <select onChange={handleSelect}>
        <option value="home">Signup</option>
        <option value="ownerReg">Owner Registration</option>
        <option value="cusReg">Customer Registration</option>
      </select>

      <select onChange={handleSelect}>
        <option value="home">Sign In</option>
        <option value="adminLog">Admin Login</option>
        <option value="cusLogin">CustomerLogin</option>
      </select>
    </nav>
      
    </div>
  )
}

export default Navbar
