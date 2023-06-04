package com.miu.se.Eclincian.service;

import com.miu.se.Eclincian.entity.Appointment;

public interface AppointmentService {
    public Appointment createAppointment(Appointment appointment);

    public Appointment getAppointmentById(Long id);

    public Appointment updateAppointment(Appointment appointment);

    public void deleteAppointment(Long id);
}
