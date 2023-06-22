import React, { useContext, useEffect, useState } from "react";
import '../ManageAppointments/ManageAppointments.css'
import { useNavigate, useParams } from "react-router-dom";
import axios from 'axios';
import UserContext from "../../context/UserContext";
import Cookies from "universal-cookie";




const ManageBills = () => {

  const { user } = useContext(UserContext)//used to access currently authenticated user detail(id and role)

  const [selectedOption, setSelectedOption] = useState('');
  const [bills, setBills] = useState([]);
  //const [selectedAppointment, setSelectedAppointment] = useState(null);// used to hold selected Appointment
  const navigate = useNavigate();
  const cookies = new Cookies();
  const token = cookies.get('accessToken');

  const { patientId } = useParams();


  const handleLogoClick = () => {
    navigate("/");
  };

  useEffect(() => {
    if (selectedOption === 'all') {
      const cookies = new Cookies();
      const token = cookies.get('accessToken');
      axios.get(`http://localhost:9090/api/v1/patient/get-bills`, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      })
        .then(response => setBills(response.data))
        .catch(error => console.error('Error:', error))
    }
  }, [selectedOption])




  const handleButtonClick = (option) => {
    setSelectedOption(option);
  };


  const displayAllAppointments = () => {
    return (
      <table>
        <thead>
          <tr >
            <th>Amount</th>
            <th>Issue Date</th>
          </tr>
        </thead>
        <tbody>
          {bills.length > 0 ? (
            bills.map(bill => (
              <tr key={bill.id}>
                <td>${bill.amount}</td>
                <td>{bill.issueDate}</td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="5">No appointments found</td>
            </tr>
          )}
        </tbody>
      </table>
    );
  };


  const maindashboard =()=>{
    navigate("/patient")
  }



  return (
    <div className="manage-appointments">
      <nav>
        <img src={"https://source.unsplash.com/100x100?dental,logo"} alt="Company Logo" onClick={handleLogoClick} />
        <h1>United Dental Surgeries</h1>
      </nav>
      <h2>Manage Your Bills</h2>
      <div className="appointment-buttons">
        <button onClick={() => handleButtonClick("main")}>Main Dashboard</button>
        <button onClick={() => handleButtonClick("all")}>View All Bills</button>

      </div>
      <div className="appointment-details">
        {selectedOption === "main" && maindashboard()}
        {selectedOption === "all" && displayAllAppointments()}
      </div>
    </div>
  );

}

export default ManageBills;




