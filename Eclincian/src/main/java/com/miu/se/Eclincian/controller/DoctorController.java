package com.miu.se.Eclincian.controller;

import com.miu.se.Eclincian.entity.Appointment;
import com.miu.se.Eclincian.entity.MedicalRecord;
import com.miu.se.Eclincian.entity.Patient;
import com.miu.se.Eclincian.service.DoctorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/appointments")
    public List<Appointment> getAllAppointments() {
        return doctorService.getAllAppointmentsForCurrentDoctor();
    }

    @GetMapping("/upcoming")
    public List<Appointment> getAllUpComingAppointment() {
        return doctorService.getAllUpComingAppointments();
    }

    @GetMapping("/patient-list")
    public List<Patient> getAllPatients() {
        return doctorService.getAllPatientBelongsToCurrentDoctor();
    }

    @PostMapping("/{doctorId}/add-medical-record/{patientId}")
    public MedicalRecord addMedicalRecord(@PathVariable Long doctorId,
                                          @PathVariable Long patientId,
                                          @RequestBody MedicalRecord medicalRecord) {
        return doctorService.addMedicalRecordForSelectedPatient(doctorId, patientId, medicalRecord);
    }

    @GetMapping("/get-medical-record/{patientId}")
    public List<MedicalRecord> getAllMedicalRecord(@PathVariable Long patientId) {
        return doctorService.getAllMedicalRecordForSelectedPatient(patientId);
    }
}
