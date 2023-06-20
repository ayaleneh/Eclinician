package com.miu.se.Eclincian.service;

import com.miu.se.Eclincian.entity.Appointment;
import com.miu.se.Eclincian.entity.Doctor;
import com.miu.se.Eclincian.entity.MedicalRecord;
import com.miu.se.Eclincian.entity.Patient;

import java.util.List;

public interface DoctorService {

    public List<Doctor> getAllDoctors();
    public Doctor createDoctor(Doctor doctor);

    public Doctor getDoctorById(Long id);

    public Doctor updateDoctor(Long doctorId,Doctor doctor);

    public void deleteDoctor(Long id);

    public List<Appointment> getAllAppointmentsForCurrentDoctor(); //get all appointments including the passed appointment for the current doctor

    public List<Appointment> getAllUpComingAppointments();// get all upcoming appointments for the current doctor

    public List<Patient> getAllPatientBelongsToCurrentDoctor();//get a list of patient belongs to the current doctor
    public MedicalRecord addMedicalRecordForSelectedPatient(Long doctorId,Long patientId, MedicalRecord medicalRecord);// check if the patient belongs to that specific doctor.

    public List<MedicalRecord> getAllMedicalRecordForSelectedPatient(Long patientId);
}
