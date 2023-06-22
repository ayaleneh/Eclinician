package com.miu.se.Eclincian.service;

import com.miu.se.Eclincian.entity.*;
import com.miu.se.Eclincian.entity.dto.response.AppointmentResponseDTO;
import com.miu.se.Eclincian.entity.dto.response.DoctorResponseDTO;
import com.miu.se.Eclincian.entity.dto.response.MedicalRecordResponseDTO;

import java.util.List;

public interface PatientService {
    public List<Patient> getAllPatients();
    public Patient createPatient(Patient patient);

    public Patient getPatientById(Long id);

    public Patient updatePatient(Long patientId,Patient patient);

    public void deletePatient(Long patientId);

    public List<AppointmentResponseDTO> getAllAppointments();
    public List<AppointmentResponseDTO> getAllUpComingAppointments();

    public MedicalRecordResponseDTO getMedicalRecord();

    public List<Bill> getAllBillsBelongsToCurrentPatient();

    public List<DoctorResponseDTO> getAllDoctors();
}
