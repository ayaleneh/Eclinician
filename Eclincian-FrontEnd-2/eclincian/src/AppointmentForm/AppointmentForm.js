import React,{useEffect, useState} from "react";
import '../AppointmentForm/AppointmentForm.css'

const AppointmentForm = ({ appointment }) => {
  const [appointmentDate, setAppointmentDate] = useState(appointment ? appointment.appointmentDate : '');
  const [appointmentTime, setAppointmentTime] = useState(appointment ? appointment.appointmentTime : '');
  const [appointmentLocation, setAppointmentLocation] = useState(appointment ? appointment.appointmentLocation : '');
  const [doctorName, setDoctorName] = useState(appointment ? appointment.doctorName : '');

  const handleSave = () => {
    // Handle save appointment
  };

  return (
    <form className="appointment-form" onSubmit={handleSave}>
      <label>
        Date:
        <input type="date" value={appointmentDate} onChange={(e) => setAppointmentDate(e.target.value)} />
      </label>
      <label>
        Time:
        <input type="time" value={appointmentTime} onChange={(e) => setAppointmentTime(e.target.value)} />
      </label>
      <label>
        Location:
        <input type="text" value={appointmentLocation} onChange={(e) => setAppointmentLocation(e.target.value)} />
      </label>
      <label>
        Doctor Name:
        <input type="text" value={doctorName} onChange={(e) => setDoctorName(e.target.value)} />
      </label>
      <button type="submit">Save</button>
    </form>
  );
};
export default AppointmentForm;