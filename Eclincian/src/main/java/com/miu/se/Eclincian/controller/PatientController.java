package com.miu.se.Eclincian.controller;

import com.miu.se.Eclincian.entity.Appointment;
import com.miu.se.Eclincian.entity.Bill;
import com.miu.se.Eclincian.entity.MedicalRecord;
import com.miu.se.Eclincian.entity.dto.response.AppointmentResponseDTO;
import com.miu.se.Eclincian.service.AppointmentService;
import com.miu.se.Eclincian.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {

    private final PatientService patientService;
    private final AppointmentService appointmentService;


    public PatientController(PatientService patientService,
                             AppointmentService appointmentService) {
        this.patientService = patientService;
        this.appointmentService = appointmentService;
    }

    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentResponseDTO>> getAllAppointmentsForCurrentPatient() {
        List<AppointmentResponseDTO> appointments = patientService.getAllAppointments();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }


    @GetMapping("/upcoming")
    public ResponseEntity<List<AppointmentResponseDTO>> getAllUpcomingAppointmentsForCurrentPatient() {
        return new ResponseEntity<>(patientService.getAllUpComingAppointments(), HttpStatus.OK);
    }

    @PostMapping("/appointments")//patient will have drop down doctor option
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        Appointment newAppointment = appointmentService.createAppointment(appointment);
        if (newAppointment == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(newAppointment, HttpStatus.CREATED);
        }
    }

    //@Valid---if we have validation in our entity
    @PutMapping("/update-appointments/{appointmentId}")
    public ResponseEntity<Appointment> updateExistingAppointment(@PathVariable Long appointmentId,
                                                                 @RequestBody Appointment appointment) {
        Appointment updatedAppointment = appointmentService.updateAppointment(appointmentId, appointment);
        if (updatedAppointment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(updatedAppointment, HttpStatus.OK);
        }
    }


    @DeleteMapping("/appointments/{appointmentId}")//working
    public ResponseEntity<?> deleteAppointmentById(@PathVariable Long appointmentId) {
        try {
            appointmentService.deleteAppointment(appointmentId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/medical-record")
    public MedicalRecord getAllMedicalRecordForCurrentPatient() {
        return patientService.getMedicalRecord();
    }

    @GetMapping("/get-bills")
    public List<Bill> getAllBills() {
        return patientService.getAllBillsBelongsToCurrentPatient();
    }

}
