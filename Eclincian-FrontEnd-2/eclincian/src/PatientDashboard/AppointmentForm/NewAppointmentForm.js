import axios from "axios";
import { useEffect, useState } from "react";
import Cookies from "universal-cookie";
import './NewAppointmentForm.css';

const NewAppointmentForm = ({onAppointmentCreation}) => {
  const [doctors, setDoctors] = useState([]);
  const [date, setDate] = useState('');
  const [time, setTime] = useState('');
  const [location, setLocation] = useState('');
  const [selectedDoctor, setSelectedDoctor] = useState('');
  const cookies = new Cookies();
  const token = cookies.get('accessToken');

  const handleSubmit = e => {
    e.preventDefault();
    const appointment = {
      doctor: { id: selectedDoctor },
      appointmentDate: date,
      appointmentTime: time,
      appointmentLocation: location
    }
    axios.post(`http://localhost:9090/api/v1/patient/appointments`, appointment, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    })
      .then(response => {
        console.log("Appointment created", response.data);
        onAppointmentCreation();
      })
      .catch(error => {
        console.error('Error creating appointment', error);
      });
  };


  useEffect(() => {
    axios.get(`http://localhost:9090/api/v1/patient/doctors`, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    })
      .then(response => {
        setDoctors(response.data);
        console.log("doctor list", response.data.data);
      })
      .catch(error => {
        console.error('Error while fetching doctors', error)
      });
  }, [])

  return (
    <div className="appointment-form">
      <form onSubmit={handleSubmit}>
        <div className="form-input">
          <label>Date:</label>
          <input type="date" onChange={e => setDate(e.target.value)} />
        </div>
        
        <div className="form-input">
          <label>Time:</label>
          <input type="time" onChange={e => setTime(e.target.value)} />
        </div>
        
        <div className="form-input">
          <label>Location:</label>
          <input type="text" onChange={e => setLocation(e.target.value)} placeholder="Location" />
        </div>

        <div className="form-input">
          <label>Doctor:</label>
          <select onChange={e => setSelectedDoctor(e.target.value)}>
            <option disabled selected value=''> -- select a doctor -- </option>
            {doctors.map(doctor =>
              <option key={doctor.id} value={doctor.id}>{doctor.fname || 'No name'}</option>
            )}
          </select>
        </div>

        <div className="form-input">
          <button type="submit" className="submit-button">Create Appointment</button>
        </div>
      </form>
    </div>
  );
}

export default NewAppointmentForm;
