import logo from './logo.svg';
import './App.css';
import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import Homepage from './HomePage/Homepage'
import Login from './Login/LoginPage'
import Header from './Header/Header';
import Footer from './Footer/Footer';
import PatientDashboard from './PatientDashboard/PatientDashboard';
import ManageAppointments from './PatientDashboard/ManageAppointments/ManageAppointments';
import UserContext from "./context/UserContext";
import { useState } from 'react';
import ManageBills from './PatientDashboard/ManageBills/ManageBills';
import ManageMedicalRecords from './PatientDashboard/ManageMedicalRecord/ManageMedicalRecord';
function App() {
  const [user, setUser] = useState({
    isAuthenticated: false,
    id: null,
  });

  // return (
  // <Router>
  //   <UserContext.Provider value={{ user, setUser }}>
  //   <Routes>
  //   <Route path='/' element={<Homepage/>}/>
  //   <Route path='/login' element={<Login/>}/>
  //   <Route path='/patient' element={<PatientDashboard/>}/>
  //   <Route path='/patient/appointments' element={<ManageAppointments/>}/>
  //   <Route path='/patient/:patientId/appointments' Component={ManageAppointments}/>
  //   </Routes>
  //   </UserContext.Provider>
  // </Router>
  // );

  return (
    <Router>
      <UserContext.Provider value={{ user, setUser }}>
        <Routes>
          <Route path='/' element={<Homepage/>}/>
          <Route path='/login' element={<Login/>}/>
          <Route path='/patient' element={<PatientDashboard/>}/>
          <Route path='/patient/appointments' element={<ManageAppointments/>}/>
          <Route path='/patient/bills' element={<ManageBills/>}/>
          <Route path='/patient/medicalrecords' element={<ManageMedicalRecords/>}/>
        </Routes>
      </UserContext.Provider>
    </Router>
  );
}
export default App;
