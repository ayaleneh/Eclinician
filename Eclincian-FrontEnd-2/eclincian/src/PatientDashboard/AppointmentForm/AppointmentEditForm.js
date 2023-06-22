import React, { useEffect, useState } from "react";
import './AppointmentForm.css'
import axios from "axios";
import Cookies from "universal-cookie";
import { useNavigate } from "react-router-dom";

const AppointmentForm = ({ appointment, onAppointmentUpdated }) => {
  console.log("appointment", appointment.doctor)
  const [date, setDate] = useState(appointment ? appointment.appointmentDate : '');
  const [time, setTime] = useState(appointment ? appointment.appointmentTime : '');
  const [location, setLocation] = useState(appointment ? appointment.appointmentLocation : '');
  const [selectedDoctor, setSelectedDoctor] = useState('');

  const [doctors, setDoctors] = useState([]);
  const navigate = useNavigate();

  const cookies = new Cookies();
  const token = cookies.get('accessToken');

  const handleSubmit = e => {
    e.preventDefault();
    const updatedAppointment = {
      doctor: { id: selectedDoctor },
      appointmentDate: date,
      appointmentTime: time,
      appointmentLocation: location
    }
    axios.put(`http://localhost:9090/api/v1/patient/update-appointments/${appointment.id}`, updatedAppointment, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    })
      .then(response => {
        console.log("Appointment updated", response.data);
        onAppointmentUpdated();
      })
      .catch(error => {
        console.error('Error updating appointment', error);
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
      })
      .catch(error => {
        console.error('Error while fetching doctors', error)
      });
  }, [])

  return (
    <form className="appointment-form" onSubmit={handleSubmit}>
      <label>
        Date:
        <input type="date" value={date} onChange={(e) => setDate(e.target.value)} />
      </label>
      <label>
        Time:
        <input type="time" value={time} onChange={(e) => setTime(e.target.value)} />
      </label>
      <label>
        Location:
        <input type="text" value={location} onChange={(e) => setLocation(e.target.value)} />
      </label>
      <label>
        Doctor Name:
        <select value={selectedDoctor} onChange={e => setSelectedDoctor(e.target.value)}>
          <option value=""> -- select a doctor -- </option>
          {doctors.map(doctor =>
            <option key={doctor.id} value={doctor.id}>{doctor.fname || 'No name'}</option>
          )}
        </select>
      </label>
      <button type="submit">Save</button>
    </form>
  );
};
export default AppointmentForm;
