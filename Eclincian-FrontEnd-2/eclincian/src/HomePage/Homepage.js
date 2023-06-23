import React from "react";
import './HomePage.css';
import { useNavigate } from "react-router-dom";
import logo from '../images/logo-color.png'
import preventive from '../images/PreventiveDentistry.jpg'
import restorative from '../images/restorative-dentistry-crown-dental.jpg'
import cosmetic from '../images/Cosmetic-Dentistry.jpg'
import pediatric from '../images/Pediatric Dentistry.jpg'
function HomePage() {
  const navigate = useNavigate();
  return (
    <div className="divhome">
      <nav className="nav">
        <img src={logo} alt="United Dental Surgeries" className="logo" />
        <h1>United Dental Surgeries</h1>
        <div className="nav-right">
          <button onClick={() => navigate("/login")} className="button">Login</button>
        </div>
      </nav>
      <div className="header-content">
        <h2>Quality Dental Care, for a Lifetime of Healthy Smiles</h2>
        <p>We provide comprehensive dental care with a personal touch.</p>
      </div>
      <h2>Our Services</h2>
      <section className="services">
        <div className="service">
          <p>Preventive Dentistry</p>
          <img src={preventive} alt="Preventive Dentistry" />
        </div>
        <div className="service">
          <p>Restorative Dentistry</p>
          <img src={restorative} alt="Restorative Dentistry" />
        </div>
        <div className="service">
          <p>Cosmetic Dentistry</p>
          <img src={cosmetic} alt="Cosmetic Dentistry" />
        </div>
        <div className="service">
          <p>Pediatric Dentistry</p>
          <img src={pediatric} alt="Pediatric Dentistry" />
        </div>
      </section>
      <footer className="footer">
        <p>&copy; 2023 United Dental Surgeries. All rights reserved.</p>
      </footer>
    </div>
  );
}

export default HomePage;

