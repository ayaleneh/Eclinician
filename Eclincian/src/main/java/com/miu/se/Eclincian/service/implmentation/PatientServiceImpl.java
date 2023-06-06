package com.miu.se.Eclincian.service.implmentation;

import com.miu.se.Eclincian.entity.Appointment;
import com.miu.se.Eclincian.entity.MedicalRecord;
import com.miu.se.Eclincian.entity.Patient;
import com.miu.se.Eclincian.repository.AppointmentRepository;
import com.miu.se.Eclincian.repository.PatientRepository;
import com.miu.se.Eclincian.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    public PatientServiceImpl(PatientRepository patientRepository,
                              AppointmentRepository appointmentRepository){
        this.patientRepository= patientRepository;
        this.appointmentRepository = appointmentRepository;
    }
    @Override
    public Patient createPatient(Patient patient) {
        return null;
    }

    @Override
    public Patient getPatientById(Long id) {
        return null;
    }

    @Override
    public Patient updatePatient(Patient patient) {
        return null;
    }

    @Override
    public void deletePatient(Long id) {

    }

    @Override
    public List<Appointment> getAppointments(Long patientId) {
        return appointmentRepository.getAllAppointmentsForCurrentPatient(patientId);
    }

    @Override
    public MedicalRecord getMedicalRecord(Long patientId) {
        return null;
    }
}
