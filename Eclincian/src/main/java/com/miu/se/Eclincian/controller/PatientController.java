package com.miu.se.Eclincian.controller;

import com.miu.se.Eclincian.entity.Appointment;
import com.miu.se.Eclincian.service.AppointmentService;
import com.miu.se.Eclincian.service.PatientService;
import java.util.Collections;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private final PatientService patientService;
    private final AppointmentService appointmentService;


    public PatientController(PatientService patientService, AppointmentService appointmentService) {
        this.patientService = patientService;
        this.appointmentService = appointmentService;
    }

    @GetMapping("/{patientId}")//All Appointment
    List<Appointment> getAllAppointmentForCurrentPatient(@PathVariable Long patientId) {
        return patientService.getAppointments(patientId);
    }

    @GetMapping("/upcoming/{patientId}")
    List<Appointment> getAllUpcomingAppointmentForCurrentPatient(@PathVariable Long patientId){
        return Collections.emptyList();
    }

}
