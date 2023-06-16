package com.miu.se.Eclincian.service.implmentation;

import com.miu.se.Eclincian.entity.Appointment;
import com.miu.se.Eclincian.entity.Doctor;
import com.miu.se.Eclincian.entity.MedicalRecord;
import com.miu.se.Eclincian.entity.Patient;
import com.miu.se.Eclincian.repository.AppointmentRepository;
import com.miu.se.Eclincian.repository.DoctorRepository;
import com.miu.se.Eclincian.repository.MedicalRecordRepository;
import com.miu.se.Eclincian.repository.PatientRepository;
import com.miu.se.Eclincian.service.DoctorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository,
                             AppointmentRepository appointmentRepository,
                             PatientRepository patientRepository,
                             MedicalRecordRepository medicalRecordRepository) {
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }


    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    @Override
    public Doctor updateDoctor(Long doctorId, Doctor doctor) {

        Optional<Doctor> existingDoctor = doctorRepository.findById(doctorId);
        if (existingDoctor.isPresent()) {
            Doctor existingDoctorEntity = existingDoctor.get();
            BeanUtils.copyProperties(doctor, existingDoctorEntity, "id");
            doctorRepository.save(existingDoctorEntity);
            return existingDoctorEntity;
        }
        return null;
    }

    @Override
    public void deleteDoctor(Long doctorId) {
        doctorRepository.deleteById(doctorId);
    }

    @Override
    public List<Appointment> getAllAppointmentsForCurrentDoctor(Long doctorId) {
        return appointmentRepository.getAllAppointmentsByDoctorId(doctorId);
    }

    @Override
    public List<Appointment> getAllUpComingAppointments(Long doctorId) {
        return appointmentRepository.getAllUpComingAppointmentForCurrentDoctor(doctorId);
    }

    @Override
    public List<Patient> getAllPatientBelongsToCurrentDoctor(Long doctorId) {
        return doctorRepository.getAllPatients(doctorId);
    }


    //check if the patient belongs to that specific doctor
    @Override
    @Transactional
    public MedicalRecord addMedicalRecordForSelectedPatient(Long doctorId, Long patientId, MedicalRecord medicalRecord) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new IllegalArgumentException("Patient not found"));
        boolean isPatientBelongsToDoctor = doctorRepository.existsByIdAndPatientsContaining(doctorId, patient);// check if the patient belongs to doctor

        if (!isPatientBelongsToDoctor) {
            throw new IllegalArgumentException("Patient does not belong to the doctor");
        }

        medicalRecord = medicalRecordRepository.save(medicalRecord);
        return medicalRecord;
    }

    @Override
    public List<MedicalRecord> getAllMedicalRecordForSelectedPatient(Long patientId, Long doctorId) {
        return medicalRecordRepository.getMedicalRecordsByPatientIdAndDoctorId(patientId, doctorId);
    }

}
