package com.miu.se.Eclincian.controller;

import com.miu.se.Eclincian.entity.Appointment;
import com.miu.se.Eclincian.service.AppointmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
  private final AppointmentService appointmentService;

  public AdminController(AppointmentService appointmentService){
      this.appointmentService=appointmentService;
  }
  //get appointment by appointmentId
  //get appointment by patientId
  /*
  * Given that I am an admin and logged into my account,
    When I navigate to the users management page,
    Then I should be able to view a list of all registered users,
    And I should be able to add a new user,
    And I should be able to delete an existing user,
    And I should be able to edit an existing user's details.
  * */

  @GetMapping("/appointments")
    public List<Appointment> getAllAppointments(){
      return appointmentService.getAllAppointment();
  }
    /*    @PostMapping("/create")
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        Patient newPatient = patientService.createPatient(patient);
        if (newPatient == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(newPatient, HttpStatus.CREATED);
        }
    }
    * */
}
