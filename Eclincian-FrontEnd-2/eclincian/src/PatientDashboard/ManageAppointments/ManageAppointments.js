import React,{useEffect, useState} from "react";
import '../ManageAppointments/ManageAppointments.css'
import { useNavigate, useParams } from "react-router-dom";
import axios from 'axios';
import AppointmentForm from '../../AppointmentForm/AppointmentForm';


const ManageAppointments=()=>{
  const [selectedOption, setSelectedOption]= useState('');
  const [appointments, setAppointments]= useState([]);
  const [selectedAppointment, setSelectedAppointment]= useState(null);// used to hold selected Appointment
  const navigate = useNavigate();

  const {patientId}= useParams();

  useEffect(()=>{
    if(selectedOption==='all'){
      axios.get(`http://localhost:9090/api/v1/patient/${patientId}/appointments`)//change the endpoint with the one that have security and didn't need to pass the id
      .then(response=>setAppointments(response.data))
      .catch(error=>console.error('Error:',error))
      // console.log(appointments)
    }
  },[selectedOption,patientId])


  const handleButtonClick=(option)=>{
    setSelectedOption(option);
  };


const displayAllAppointments = () => {
  return (
    <table>
      <thead>
        <tr >
          <th>Appointment Date</th>
          <th>Appointment Time</th>
          <th>Appointment Location</th>
          <th>Doctor Name</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        {appointments.map(appointment => (
          <tr key={appointment.id}>
            <td>{appointment.appointmentDate}</td>
            <td>{appointment.appointmentTime}</td>
            <td>{appointment.appointmentLocation}</td>
            <td>{appointment.doctorName}</td>
            <td>
              {/* <button onClick={()=>navigate(`/edit-appointment/${appointment.id}`)}>Edit</button> */}
              <button onClick={() => {setSelectedAppointment(appointment); setSelectedOption('create')}}>Edit</button>

              <button>Delete</button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};


const displayUpcomingAppointments=()=>{
  // replace this with actual API call and handle the response
  return <p>Upcoming Appointments data here</p>;
}
const displayCreateAppointmentForm = () => {
return <AppointmentForm appointment={selectedAppointment}/>
};

return (
  <div className="manage-appointments">
    <nav>
      <img src={"https://source.unsplash.com/100x100?dental,logo"} alt="Company Logo" />
      <h1>United Dental Surgeries</h1>
    </nav>
    <h2>Manage Your Appointments</h2>
    <div className="appointment-buttons">
      <button onClick={() => handleButtonClick("all")}>View All Appointments</button>
      <button onClick={() => handleButtonClick("upcoming")}>View Upcoming Appointments</button>
      <button onClick={() => handleButtonClick("create")}>Create Appointment</button>
    </div>
    <div className="appointment-details">
      {selectedOption === "all" && displayAllAppointments()}
      {selectedOption === "upcoming" && displayUpcomingAppointments()}
      {selectedOption === "create" && displayCreateAppointmentForm()}
    </div>
  </div>
);

}

export default ManageAppointments;