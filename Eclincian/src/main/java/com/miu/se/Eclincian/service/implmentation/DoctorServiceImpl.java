package com.miu.se.Eclincian.service.implmentation;

import com.miu.se.Eclincian.entity.Appointment;
import com.miu.se.Eclincian.entity.Doctor;
import com.miu.se.Eclincian.repository.DoctorRepository;
import com.miu.se.Eclincian.service.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        return null;
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return null;
    }

    @Override
    public Doctor updateDoctor(Doctor doctor) {
        return null;
    }

    @Override
    public void deleteDoctor(Long id) {

    }

    @Override
    public List<Appointment> getAppointments(Long doctorId) {
        return null;
    }
}
