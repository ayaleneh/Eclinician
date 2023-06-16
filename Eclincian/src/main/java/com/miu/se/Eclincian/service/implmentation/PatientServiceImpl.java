package com.miu.se.Eclincian.service.implmentation;

import com.miu.se.Eclincian.entity.Appointment;
import com.miu.se.Eclincian.entity.Bill;
import com.miu.se.Eclincian.entity.MedicalRecord;
import com.miu.se.Eclincian.entity.Patient;
import com.miu.se.Eclincian.repository.AppointmentRepository;
import com.miu.se.Eclincian.repository.MedicalRecordRepository;
import com.miu.se.Eclincian.repository.PatientRepository;
import com.miu.se.Eclincian.service.PatientService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;

    private final MedicalRecordRepository medicalRecordRepository;

    public PatientServiceImpl(PatientRepository patientRepository,
                              AppointmentRepository appointmentRepository,
                              MedicalRecordRepository medicalRecordRepository) {
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }


    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public Patient updatePatient(Long patientId,Patient patient) {

        Optional<Patient> existingPatient = patientRepository.findById(patientId);
        if (existingPatient.isPresent()){
            Patient existingPatientEntity=existingPatient.get();
            BeanUtils.copyProperties(patient,existingPatientEntity,"id");
            patientRepository.save(existingPatientEntity);
            return existingPatientEntity;
        }
        return null;
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public List<Appointment> getAllAppointments(Long patientId) {
        return appointmentRepository.getAllAppointmentsForCurrentPatient(patientId);
    }

    @Override
    public List<Appointment> getAllUpComingAppointments(Long patientId) {
        return appointmentRepository.getAllUpComingAppointmentForCurrentPatient(patientId);
    }

    @Override
    public MedicalRecord getMedicalRecord(Long patientId) {
        return patientRepository.getAllMedicalRecordByPatientId(patientId);
    }

    @Override
    public List<Bill> getAllBillsBelongsToCurrentPatient(Long patientId) {
        return patientRepository.getAllBillsBelongToCurrentPatient(patientId);
    }

}
