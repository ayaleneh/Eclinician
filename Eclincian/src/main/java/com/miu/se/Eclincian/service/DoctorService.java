package com.miu.se.Eclincian.service;

import com.miu.se.Eclincian.entity.Appointment;
import com.miu.se.Eclincian.entity.Doctor;

import java.util.List;

public interface DoctorService {
    public Doctor createDoctor(Doctor doctor);

    public Doctor getDoctorById(Long id);

    public Doctor updateDoctor(Doctor doctor);

    public void deleteDoctor(Long id);

    public List<Appointment> getAppointments(Long doctorId);
}
