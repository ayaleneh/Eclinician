package com.miu.se.Eclincian.controller;

import com.miu.se.Eclincian.entity.Appointment;
import com.miu.se.Eclincian.service.AppointmentService;
import com.miu.se.Eclincian.service.PatientService;
import java.util.Collections;

import com.miu.se.Eclincian.service.implmentation.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @GetMapping("/{patientId}/appointments")//All Appointment
    List<Appointment> getAllAppointmentForCurrentPatient(@PathVariable Long patientId) {
        return patientService.getAppointments(patientId);
    }


    @GetMapping("/{patientId}/upcoming")
    List<Appointment> getAllUpcomingAppointmentForCurrentPatient(@PathVariable Long patientId){
        return Collections.emptyList();
    }

    @PostMapping("/{patientId}/appointments")
    public ResponseEntity<Appointment> createAppointment(@PathVariable Long patientId,
                                                         @RequestBody Appointment appointment) {
        Appointment newAppointment = appointmentService.createAppointment(patientId, appointment);
        if (newAppointment == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(newAppointment, HttpStatus.CREATED);
        }
    }

}
