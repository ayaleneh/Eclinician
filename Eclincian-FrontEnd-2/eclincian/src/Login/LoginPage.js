//  import React, { useState } from 'react';
//  import '../Login/Login.css'
//  import Header from '../Header/Header';
// import Footer from '../Footer/Footer';

// function LoginPage() {
//     const [username, setUsername] = useState('');
//     const [password, setPassword] = useState('');

//     const handleInputChange = (event) => {
//         const { name, value } = event.target;

//         if(name === 'username') {
//             setUsername(value);
//         } else {
//             setPassword(value);
//         }
//     }

//     const handleSubmit = (event) => {
//         event.preventDefault();
//         // Perform login action
//         console.log('username:', username);
//         console.log('password:', password);
//     }

//     return (
//         <div className="login-page">
//             <h2>Login</h2>
//             <form onSubmit={handleSubmit}>
//                 <div className="input-group">
//                     <label>Username</label>
//                     <input type="text" name="username" value={username} onChange={handleInputChange} required />
//                 </div>
//                 <div className="input-group">
//                     <label>Password</label>
//                     <input type="password" name="password" value={password} onChange={handleInputChange} required />
//                 </div>
//                 <div className="input-group">
//                     <button type="submit">Login</button>
//                 </div>
//             </form>
//         </div>
//     );
// }

// export default LoginPage;

import { useContext, useState } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import UserContext from "../context/UserContext";
import axiosInstance from "../api/axiosInstance";
import Cookies from 'universal-cookie';
import '../Login/Login.css'

const LoginPage = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");

  const { user, setUser } = useContext(UserContext);

  const navigate = useNavigate();
  const location = useLocation();

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!/^\S+@\S+\.\S+$/.test(email)) {
      setEmailError("Invalid email address");
      return;
    }
    setEmailError("");

    if (password.length < 3) {
      setPasswordError("Password must be at least 3 characters");
      return;
    }
    setPasswordError("");

    axiosInstance.post('/authenticate/login', {email, password})
    .then(response => {
      console.log(response.data);

      if (!response.data.accessToken) {
        throw new Error('No token returned from backend');
      }

      const cookies = new Cookies();
      cookies.set('accessToken', response.data.accessToken, {
        path: '/',
      });

      if(response.data.roles[0] === 'ADMIN') {
        setUser({
          isAuthenticated: true,
          role: "admin",
        });
        navigate("/admin")
      } else if(response.data.roles[0] === 'DOCTOR') {
        setUser({
          isAuthenticated: true,
          role: "PATIENT",
          id: response.data.userId
        });
        navigate("/doctor")
      } else {
        setUser({
          isAuthenticated: true,
          role: "PATIENT",
          id: response.data.userId
        });
        navigate("/patient")
      }

      console.log(user);
    })
    .catch(error => console.error(error))

    // if (location.state) navigate("/properties/" + location.state.propertyId);
    // else navigate("/");
  };

  return (
    <div className="container">
      <h1 className="title">Login</h1>
      <form onSubmit={handleSubmit}>
        <input
          type="email"
          name="email"
          required
          placeholder="Email Address"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          className={emailError !== "" ? "input-error" : ""}
        />
        {emailError && <p className="error-text">{emailError}</p>}
        <input
          type="password"
          name="password"
          required
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          className={passwordError !== "" ? "input-error" : ""}
        />
        {passwordError && <p className="error-text">{passwordError}</p>}
        <button type="submit">Sign In</button>
        <div className="links">
          <Link to={"/forgot-password"}>Forgot Password?</Link>
          <Link to={"/register"}>Not Registered?</Link>
        </div>
      </form>
    </div>
  );
};

export default LoginPage;
