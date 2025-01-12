import React from 'react'
import { useNavigate } from 'react-router-dom';

const Navbar = () => {
    const navigate = useNavigate();

    const routes = {
        Reg: "/",
        ownerReg: "/ownerReg",
        customerReg : "/customerReg"
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
        <option value="customerReg">Customer Registration</option>
      </select>
    </nav>
      
    </div>
  )
}

export default Navbar
