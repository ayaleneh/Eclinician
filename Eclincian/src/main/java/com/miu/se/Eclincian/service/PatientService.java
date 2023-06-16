package com.miu.se.Eclincian.service;

import com.miu.se.Eclincian.entity.Appointment;
import com.miu.se.Eclincian.entity.Bill;
import com.miu.se.Eclincian.entity.MedicalRecord;
import com.miu.se.Eclincian.entity.Patient;

import java.util.List;

public interface PatientService {
    public List<Patient> getAllPatients();
    public Patient createPatient(Patient patient);

    public Patient getPatientById(Long id);

    public Patient updatePatient(Long patientId,Patient patient);

    public void deletePatient(Long id);

    public List<Appointment> getAllAppointments(Long patientId);
    public List<Appointment> getAllUpComingAppointments(Long patientId);

    public MedicalRecord getMedicalRecord(Long patientId);

    public List<Bill> getAllBillsBelongsToCurrentPatient(Long patientId);
}
