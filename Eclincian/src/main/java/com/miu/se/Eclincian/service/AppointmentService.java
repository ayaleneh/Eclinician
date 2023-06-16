package com.miu.se.Eclincian.service;

import com.miu.se.Eclincian.entity.Appointment;

import java.util.List;

public interface AppointmentService {
    public Appointment createAppointment(Long patientId,Appointment appointment);
    public List<Appointment> getAllAppointment();

    public Appointment getAppointmentById(Long id);

    public Appointment updateAppointment(Long patientId,Long appointmentId,Appointment appointment);

    public void deleteAppointment(Long patientId,Long appointmentId);
}
