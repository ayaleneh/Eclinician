package com.miu.se.Eclincian.service;

import com.miu.se.Eclincian.entity.Appointment;
import com.miu.se.Eclincian.entity.Bill;
import com.miu.se.Eclincian.entity.MedicalRecord;
import com.miu.se.Eclincian.entity.Patient;
import com.miu.se.Eclincian.entity.dto.response.AppointmentResponseDTO;

import java.util.List;

public interface PatientService {
    public List<Patient> getAllPatients();
    public Patient createPatient(Patient patient);

    public Patient getPatientById(Long id);

    public Patient updatePatient(Long patientId,Patient patient);

    public void deletePatient(Long patientId);

    public List<AppointmentResponseDTO> getAllAppointments();
    public List<AppointmentResponseDTO> getAllUpComingAppointments();

    public MedicalRecord getMedicalRecord();

    public List<Bill> getAllBillsBelongsToCurrentPatient();
}
