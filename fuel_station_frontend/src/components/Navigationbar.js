import React from "react";
import { useNavigate } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./Navigationbar.css";

const Navigationbar = () => {
  const navigate = useNavigate();

  const handleNavigation = (path) => {
    navigate(path);
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-dark custom-navbar">
      <div className="container-fluid">
        <a className="navbar-brand text-white" href="/">
          App Logo
        </a>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav">
            <li className="nav-item dropdown">
              <a
                className="nav-link dropdown-toggle text-white"
                href="#"
                id="signupDropdown"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                Signup
              </a>
              <ul className="dropdown-menu" aria-labelledby="signupDropdown">
                <li>
                  <a
                    className="dropdown-item"
                    onClick={() => handleNavigation("/ownerReg")}
                  >
                    Owner Registration
                  </a>
                </li>
                <li>
                  <a
                    className="dropdown-item"
                    onClick={() => handleNavigation("/cusReg")}
                  >
                    Customer Registration
                  </a>
                </li>
              </ul>
            </li>

            <li className="nav-item dropdown">
              <a
                className="nav-link dropdown-toggle text-white"
                href="#"
                id="signinDropdown"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                Sign In
              </a>
              <ul className="dropdown-menu" aria-labelledby="signinDropdown">
                <li>
                  <a
                    className="dropdown-item"
                    onClick={() => handleNavigation("/adminLog")}
                  >
                    Admin Login
                  </a>
                </li>
                <li>
                  <a
                    className="dropdown-item"
                    onClick={() => handleNavigation("/cusLogin")}
                  >
                    Customer Login
                  </a>
                </li>
                <li>
                  <a
                    className="dropdown-item"
                    onClick={() => handleNavigation("/OwnerLogin")}
                  >
                    Owner Login
                  </a>
                </li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default Navigationbar;
