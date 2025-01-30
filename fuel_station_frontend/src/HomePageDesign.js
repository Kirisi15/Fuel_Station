import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "./HomePageDesign.css";
import { useNavigate } from "react-router-dom";
import CustomerLogin from "./customer/CustomerLogin";

const HomePageDesign = () => {
  const navigate = useNavigate(); 
  return (
    <div
      className="hero-section"
      style={{
        backgroundImage: "url('/image5.jpg')",
        backgroundSize: "cover",
        backgroundPosition: "center",
        height: "100vh",
      }}
    >
    <div className="cus-in-homepage">
        <CustomerLogin isHomepage={true} />
    </div>
    </div>
  );
};

export default HomePageDesign;
