import React from "react";
import './HomePage.css';
import { useNavigate } from "react-router-dom";
function HomePage() {
  const navigate= useNavigate();
  return (
    <div className="divhome">
        <nav className="nav">
          <img src="https://source.unsplash.com/100x100?dental,logo" alt="United Dental Surgeries" className="logo"/>
          <h1>United Dental Surgeries</h1>
          <div className="nav-right">
            <button onClick={()=>navigate("/login")} className="button">Login</button>
          </div>
        </nav>
        <div className="header-content">
          <h2>Quality Dental Care, for a Lifetime of Healthy Smiles</h2>
          <p>We provide comprehensive dental care with a personal touch.</p>
        </div>
      <section className="services">
        <h2>Our Services</h2>
        <div className="service">
          <p>Preventive Dentistry</p>
          <img src="https://source.unsplash.com/100x100?tooth-cleaning" alt="Preventive Dentistry" />
        </div>
        <div className="service">
          <p>Restorative Dentistry</p>
          <img src="https://source.unsplash.com/100x100?filling" alt="Restorative Dentistry" />
        </div>
        <div className="service">
          <p>Cosmetic Dentistry</p>
          <img src="https://source.unsplash.com/100x100?braces" alt="Cosmetic Dentistry" />
        </div>
        <div className="service">
          <p>Pediatric Dentistry</p>
          <img src="https://source.unsplash.com/100x100?child-dentist" alt="Pediatric Dentistry" />
        </div>
      </section>
      <footer className="footer">
        <p>&copy; 2023 United Dental Surgeries. All rights reserved.</p>
      </footer>
    </div>
  );
}

export default HomePage;

