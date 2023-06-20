package com.miu.se.Eclincian.service;

import com.miu.se.Eclincian.entity.Appointment;
import com.miu.se.Eclincian.entity.dto.response.AppointmentResponseDTO;

import java.util.List;

public interface AppointmentService {

    public Appointment createAppointment(Appointment appointment);
    public List<AppointmentResponseDTO> getAllAppointment();

    public Appointment getAppointmentById(Long id);

    public Appointment updateAppointment(Long appointmentId,Appointment appointment);

    public void deleteAppointment(Long appointmentId);
}
