import React, { useContext, useEffect, useState } from "react";
import '../ManageAppointments/ManageAppointments.css'
import { useNavigate, useParams } from "react-router-dom";
import axios from 'axios';
import UserContext from "../../context/UserContext";
import Cookies from "universal-cookie";




const ManageMedicalRecords = () => {

  const [selectedOption, setSelectedOption] = useState('');
  const [medicalrecords, setMedicaRecords] = useState([]);

  const navigate = useNavigate();

  const handleLogoClick = () => {
    navigate("/");
  };

  useEffect(() => {
    if (selectedOption === 'all') {
      console.log("Here in useEffect method")
      const cookies = new Cookies();
      const token = cookies.get('accessToken');
      axios.get(`http://localhost:9090/api/v1/patient/medical-record`, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      })
        .then(response => {
          console.log("Medical",response.data)
          setMedicaRecords(response.data)
        })
        .catch(error => console.error('Error:', error))
    }
  }, [selectedOption])




  const handleButtonClick = (option) => {
    setSelectedOption(option);
  };

  const displayAllMedicalRecords = () => {
    return (
      <table>
        <thead>
          <tr>
            <th>Patient Name</th>
            <th>Diagnosed Date</th>
            <th>Diagnosed Condition</th>
            <th>Treatment Plan</th>
            <th>Doctor Note</th>
            <th>Doctor Name</th>
          </tr>
        </thead>
        <tbody>
          {medicalrecords != null ? (
            
              <tr key={medicalrecords.id}>
                <td>{medicalrecords.patientName}</td>
                <td>{medicalrecords.date}</td>
                <td>{medicalrecords.diagnosedCondition}</td>
                <td>{medicalrecords.treatmentPlan}</td>
                <td>{medicalrecords.doctorNotes}</td>
                <td>{medicalrecords.doctorName}</td>
              </tr>
            
          ) : (
            <tr>
              <td colSpan="7">No Medical Record Found</td>
            </tr>
          )}
        </tbody>
      </table>
    );
};



  const maindashboard = () => {
    navigate("/patient")
  }



  return (
    <div className="manage-appointments">
      <nav>
        <img src={"https://source.unsplash.com/100x100?dental,logo"} alt="Company Logo" onClick={handleLogoClick} />
        <h1>United Dental Surgeries</h1>
      </nav>
      <h2>Manage Your Medical Records</h2>
      <div className="appointment-buttons">
        <button onClick={() => handleButtonClick("main")}>Main Dashboard</button>
        <button onClick={() => handleButtonClick("all")}>View All Medical Records</button>

      </div>
      <div className="appointment-details">
        {selectedOption === "main" && maindashboard()}
        {selectedOption === "all" && displayAllMedicalRecords()}
      </div>
    </div>
  );

}

export default ManageMedicalRecords;




