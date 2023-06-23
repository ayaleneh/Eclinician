import { useNavigate } from "react-router-dom";
import '../PatientDashboard/PatientDashboard.css';
import logo from '../images/logo-color.png'

const PatientDashboard = () => {
  const navigate = useNavigate();
  const handleLogoClick = () => {
    navigate("/");
  };

  return (
    <div className="divpatient">
      <nav className="nav">
        <img src={logo} alt="United Dental Surgeries" className="logo" onClick={handleLogoClick} />
        <h1>United Dental Surgeries</h1>
      </nav>
      <div className="content">
        <h1>Welcome to your Dashboard</h1>
        <p>What would you like to do today?</p>
        <div className="dashboard-buttons">
          <button className="dashboard-button" onClick={()=>navigate("/patient/appointments")}>Manage Appointments</button>
          <button className="dashboard-button" onClick={()=>navigate("/patient/bills")}>View Bills</button>
          <button className="dashboard-button" onClick={()=>navigate("/patient/medicalrecords")}>View Medical Records</button>
        </div>
      </div>
    </div>
  );
}

export default PatientDashboard;
