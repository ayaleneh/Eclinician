package com.miu.se.Eclincian.controller;

import com.miu.se.Eclincian.entity.Appointment;
import com.miu.se.Eclincian.entity.MedicalRecord;
import com.miu.se.Eclincian.entity.Patient;
import com.miu.se.Eclincian.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return new ResponseEntity<>(doctorService.getAllAppointmentsForCurrentDoctor(), HttpStatus.OK);
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<Appointment>> getAllUpComingAppointment() {
        return new ResponseEntity<>(doctorService.getAllUpComingAppointments(),HttpStatus.OK);
    }

    @GetMapping("/patient-list")
    public ResponseEntity<List<Patient>> getAllPatients() {
        return new ResponseEntity<>(doctorService.getAllPatientBelongsToCurrentDoctor(),HttpStatus.OK);
    }

    @PostMapping("/{doctorId}/add-medical-record/{patientId}")
    public ResponseEntity<MedicalRecord> addMedicalRecord(@PathVariable Long doctorId,
                                          @PathVariable Long patientId,
                                          @RequestBody MedicalRecord medicalRecord) {
        return  new ResponseEntity<>(doctorService.addMedicalRecordForSelectedPatient(doctorId, patientId, medicalRecord),HttpStatus.CREATED);
    }

    @GetMapping("/get-medical-record/{patientId}")
    public ResponseEntity<List<MedicalRecord>> getAllMedicalRecord(@PathVariable Long patientId) {
        return new ResponseEntity<>(doctorService.getAllMedicalRecordForSelectedPatient(patientId),HttpStatus.OK);
    }
}
