package com.miu.se.Eclincian.service;

import com.miu.se.Eclincian.entity.Appointment;

import java.util.List;

public interface AppointmentService {
    public Appointment createAppointment(Appointment appointment);
    public List<Appointment> getAllAppointment();

    public Appointment getAppointmentById(Long id);

    public Appointment updateAppointment(Long appointmentId,Appointment appointment);

    public void deleteAppointment(Long id);
}
