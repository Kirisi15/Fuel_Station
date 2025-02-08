import { FaFacebook, FaTwitter, FaInstagram, FaLinkedin } from "react-icons/fa";
import './Footer.css'; // Import the CSS file

function Footer() {
  return (
    <footer className="footer">
      <div className="footer-content">
        {/* Left Section */}
        <div className="text-section">
          <h2>Fuel Station</h2>
          <p>&copy; 2025 Fuel Station. All rights reserved.</p>
        </div>
        
        {/* Quick Links */}
        <div className="links">
          <a href="/about" className="hover:text-yellow-400 transition-colors duration-300">About</a>
          <a href="/services" className="hover:text-yellow-400 transition-colors duration-300">Services</a>
          <a href="/contact" className="hover:text-yellow-400 transition-colors duration-300">Contact</a>
          <a href="/privacy" className="hover:text-yellow-400 transition-colors duration-300">Privacy Policy</a>
        </div>
        
        {/* Social Media Icons */}
        <div className="social-media">
          <a href="#" className="text-gray-400 hover:text-white transition-colors duration-300">
            <FaFacebook size={24} />
          </a>
          <a href="#" className="text-gray-400 hover:text-white transition-colors duration-300">
            <FaTwitter size={24} />
          </a>
          <a href="#" className="text-gray-400 hover:text-white transition-colors duration-300">
            <FaInstagram size={24} />
          </a>
          <a href="#" className="text-gray-400 hover:text-white transition-colors duration-300">
            <FaLinkedin size={24} />
          </a>
        </div>
      </div>
    </footer>
  );
}

export default Footer;
