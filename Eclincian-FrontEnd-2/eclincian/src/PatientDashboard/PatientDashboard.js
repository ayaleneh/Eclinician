import { useNavigate } from "react-router-dom"
import '../PatientDashboard/PatientDashboard.css'
const PatientDashboard=()=>{
  const navigate =useNavigate();


  return(
  <div className="dashboard">
    <h1>Welcome to your Patient Dashboard</h1>
    <p>What would you like to do today?</p>
    <div className="dashboard-buttons">
     <button onClick={()=>navigate("/patient/appointments")}>Manage Appointments</button>
     <button onClick={()=>navigate("/bills")}>View Bills</button>
     <button onClick={()=>navigate("/medicalrecords")}>View Medical Records</button>
    </div>
  </div>
  );
}
export default PatientDashboard;