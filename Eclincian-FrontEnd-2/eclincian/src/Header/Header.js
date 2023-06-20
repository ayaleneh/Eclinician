import React from 'react';
import './Header.css';
import { useNavigate } from 'react-router-dom';

const Header = () => {
  const navigate= useNavigate();
  return (
     <nav>
          <img src="https://source.unsplash.com/100x100?dental,logo" alt="United Dental Surgeries" className="logo"/>
          <h1 class="logo-text">United Dental Surgeries</h1>
          <div className="nav-right">
            <button onClick={()=>navigate("/login")}>Login</button>
          </div>
        </nav>
  )
};

export default Header;
