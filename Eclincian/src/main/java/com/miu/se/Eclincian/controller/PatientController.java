package com.miu.se.Eclincian.controller;

import com.miu.se.Eclincian.entity.Appointment;
import com.miu.se.Eclincian.entity.Bill;
import com.miu.se.Eclincian.entity.MedicalRecord;
import com.miu.se.Eclincian.service.AppointmentService;
import com.miu.se.Eclincian.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {
    //for UI give a way for a patient to select the date and time by providing interactive date/time
    //UI
    //make the doctor list available in dropbox and let the patient select their doctor.
    /*Given that I am a registered patient and logged into my account,
     When I navigate to my appointments page,
     Then I should be able to see all upcoming appointments,
     And I should be able to book a new appointment,
     And I should be able to reschedule an existing appointment,(@PatchMapping)
     And I should be able to cancel an existing appointment.
    * */

    private final PatientService patientService;//@Qualifier("PatientServiceImpl") if we have multiple implementation of PatientService
    private final AppointmentService appointmentService;


    public PatientController(PatientService patientService, AppointmentService appointmentService) {
        this.patientService = patientService;
        this.appointmentService = appointmentService;
    }

    @GetMapping("/{patientId}/appointments")
    //All Appointment
    public ResponseEntity<List<Appointment>> getAllAppointmentsForCurrentPatient(@PathVariable Long patientId) {
        return new ResponseEntity<>(patientService.getAllAppointments(patientId), HttpStatus.OK);
    }


    @GetMapping("/{patientId}/upcoming")
    public ResponseEntity<List<Appointment>> getAllUpcomingAppointmentsForCurrentPatient(@PathVariable Long patientId) {
        return new ResponseEntity<>(patientService.getAllUpComingAppointments(patientId), HttpStatus.OK);
    }

    @PostMapping("/{patientId}/appointments")//patient will have drop down doctor option
    public ResponseEntity<Appointment> createAppointment(@PathVariable Long patientId,
                                                         @RequestBody Appointment appointment) {
        Appointment newAppointment = appointmentService.createAppointment(patientId, appointment);
        if (newAppointment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(newAppointment, HttpStatus.CREATED);
        }
    }

    //@Valid---if we have validation in our entity
    @PutMapping("/{patientId}/update-appointments/{appointmentId}")
    public ResponseEntity<Appointment> updateExistingAppointment(@PathVariable Long patientId,
                                                                 @PathVariable Long appointmentId,
                                                                 @RequestBody Appointment appointment) {
        Appointment updatedAppointment = appointmentService.updateAppointment(patientId, appointmentId, appointment);
        if (updatedAppointment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(updatedAppointment, HttpStatus.OK); // Update from CREATED to OK
        }
    }


    @DeleteMapping("/{patientId}/appointments/{appointmentId}")//working
    public ResponseEntity<?> deleteAppointmentById(@PathVariable Long patientId,
                                                   @PathVariable Long appointmentId) {
        try {
            appointmentService.deleteAppointment(patientId, appointmentId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{patientId}/medical-record")
    public MedicalRecord getAllMedicalRecordForCurrentPatient(@PathVariable Long patientId) {
        return patientService.getMedicalRecord(patientId);
    }

    @GetMapping("/{patientId}/get-bills")
    public List<Bill> getAllBills(@PathVariable Long patientId) {
        return patientService.getAllBillsBelongsToCurrentPatient(patientId);
    }

}
