package com.miu.se.Eclincian.service;

import com.miu.se.Eclincian.entity.Appointment;
import com.miu.se.Eclincian.entity.MedicalRecord;
import com.miu.se.Eclincian.entity.Patient;

import java.util.List;

public interface PatientService {
    public Patient createPatient(Patient patient);

    public Patient getPatientById(Long id);

    public Patient updatePatient(Patient patient);

    public void deletePatient(Long id);

    public List<Appointment> getAppointments(Long patientId);

    public MedicalRecord getMedicalRecord(Long patientId);
}
