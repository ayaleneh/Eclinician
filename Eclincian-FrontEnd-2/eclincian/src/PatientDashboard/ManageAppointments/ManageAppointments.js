import React, { useContext, useEffect, useState } from "react";
import '../ManageAppointments/ManageAppointments.css'
import { useNavigate, useParams } from "react-router-dom";
import axios from 'axios';
import AppointmentEditForm from '../AppointmentForm/AppointmentEditForm';
import UserContext from "../../context/UserContext";
import Cookies from "universal-cookie";
import NewAppointmentForm from '../AppointmentForm/NewAppointmentForm'



const ManageAppointments = () => {

  const { user } = useContext(UserContext)//used to access currently authenticated user detail(id and role)

  const [selectedOption, setSelectedOption] = useState('');
  const [appointments, setAppointments] = useState([]);
  const [selectedAppointment, setSelectedAppointment] = useState(null);// used to hold selected Appointment
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
      axios.get(`http://localhost:9090/api/v1/patient/appointments`, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      })
        .then(response => setAppointments(response.data))
        .catch(error => console.error('Error:', error))
    }
    if (selectedOption === 'upcoming') {
      const cookies = new Cookies();
      const token = cookies.get('accessToken');
      axios.get(`http://localhost:9090/api/v1/patient/upcoming`, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      })
        .then(response => setAppointments(response.data))
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
            <th>Patient Name</th>
            <th>Appointment Date</th>
            <th>Appointment Time</th>
            <th>Appointment Location</th>
            <th>Doctor Name</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {appointments.length > 0 ? (
            appointments.map(appointment => (
              <tr key={appointment.id}>
                <td>{appointment.patientName}</td>
                <td>{appointment.appointmentDate}</td>
                <td>{appointment.appointmentTime}</td>
                <td>{appointment.appointmentLocation}</td>
                <td>{appointment.doctorName}</td>
                <td>
                  <button onClick={() => { setSelectedAppointment(appointment); setSelectedOption('edit') }}>Edit</button>
                  {/* <button onClick={() => { setSelectedAppointment(appointment); setSelectedOption('delete') }}>Delete</button> */}
                  <button onClick={() => deleteSelectedAppointment(appointment)}>Delete</button>

                </td>
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


  const displayUpcomingAppointments = () => {
    return (
      <table>
        <thead>
          <tr >
            <th>Patient Name</th>
            <th>Appointment Date</th>
            <th>Appointment Time</th>
            <th>Appointment Location</th>
            <th>Doctor Name</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {appointments.length > 0 ? (
            appointments.map(appointment => (
              <tr key={appointment.id}>
                <td>{appointment.patientName}</td>
                <td>{appointment.appointmentDate}</td>
                <td>{appointment.appointmentTime}</td>
                <td>{appointment.appointmentLocation}</td>
                <td>{appointment.doctorName}</td>
                <td>
                  <button onClick={() => { setSelectedAppointment(appointment); setSelectedOption('edit') }}>Edit</button>
                  {/* <button onClick={() => { setSelectedAppointment(appointment); setSelectedOption('delete') }}>Delete</button> */}
                  <button onClick={() => deleteSelectedAppointment(appointment)}>Delete</button>

                </td>
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
  }
  const displayEditAppointmentForm = () => {
    return <AppointmentEditForm appointment={selectedAppointment} onAppointmentUpdated={() => setSelectedOption('all')} />
  };

  const maindashboard =()=>{
    navigate("/patient")
  }
  const displayCreateAppointmentForm = () => {
    //create new Appointment;
    return <NewAppointmentForm onAppointmentCreation={() => setSelectedOption('all')} />

  }
  const deleteSelectedAppointment = (appointment) => {
    axios.delete(`http://localhost:9090/api/v1/patient/appointments/${appointment.id}`, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    })
      .then(response => {
        console.log(response.data);
        return axios.get(`http://localhost:9090/api/v1/patient/appointments`, { // Refetch appointments after delete
          headers: {
            Authorization: `Bearer ${token}`
          }
        });
      })
      .then(response => {
        setAppointments(response.data); // Refresh appointments list
        setSelectedOption('all'); // Navigate to 'View All Appointments' view.
      })
      .catch(error => {
        console.error("Error:", error);
      });
  }


  return (
    <div className="manage-appointments">
      <nav>
        <img src={"https://source.unsplash.com/100x100?dental,logo"} alt="Company Logo" onClick={handleLogoClick} />
        <h1>United Dental Surgeries</h1>
      </nav>
      <h2>Manage Your Appointments</h2>
      <div className="appointment-buttons">
        <button onClick={() => handleButtonClick("main")}>Main Dashboard</button>
        <button onClick={() => handleButtonClick("all")}>View All Appointments</button>
        <button onClick={() => handleButtonClick("upcoming")}>View Upcoming Appointments</button>
        <button onClick={() => handleButtonClick("create")}>Create Appointment</button>

      </div>
      <div className="appointment-details">
        {selectedOption === "main" && maindashboard()}
        {selectedOption === "all" && displayAllAppointments()}
        {selectedOption === "upcoming" && displayUpcomingAppointments()}
        {selectedOption === "create" && displayCreateAppointmentForm()}
        {selectedOption === "edit" && displayEditAppointmentForm()}
        {/* {selectedOption === "delete" && deleteSelectedAppointment()} */}
        {/* {selectedOption ==="create" && <NewAppointmentForm onAppointmentCreation={()=>setSelectedOption('all')}/>} */}
        {/* {selectedOption === "edit" && <AppointmentEditForm appointment={selectedAppointment} onAppointmentUpdated={() => setSelectedOption('all')} />} */}

      </div>
    </div>
  );

}

export default ManageAppointments;




