package com.miu.se.Eclincian.controller;

import com.miu.se.Eclincian.entity.Appointment;
import com.miu.se.Eclincian.entity.Doctor;
import com.miu.se.Eclincian.entity.Patient;
import com.miu.se.Eclincian.service.AppointmentService;
import com.miu.se.Eclincian.service.DoctorService;
import com.miu.se.Eclincian.service.PatientService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
    private final PatientService patientService;

    public AdminController(AppointmentService appointmentService,
                           DoctorService doctorService,
                           PatientService patientService) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.patientService = patientService;
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
    * TODO
    *  admin can create bill for patient if there is already passed appointment related to the patient
  * */

    @GetMapping("/appointments")
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointment();
    }

    @GetMapping("/doctors")
    public  List<Doctor> getAllDoctors(){
        return  doctorService.getAllDoctors();
    }
    @GetMapping("/doctor/{doctorId}")
    public Doctor getDoctorById(@PathVariable Long doctorId){
        return doctorService.getDoctorById(doctorId);
    }
    @PostMapping("/doctor")
    public Doctor addNewDoctor(@RequestBody Doctor doctor){
        return doctorService.createDoctor(doctor);
    }
    @PutMapping("/doctor/{doctorId}")
    public Doctor updateExistingDoctor(@PathVariable Long doctorId, @RequestBody Doctor doctor){
        return doctorService.updateDoctor(doctorId,doctor);
    }
    @DeleteMapping("/doctor/{doctorId}")
    public void deleteDoctorById(@PathVariable Long doctorId){
        doctorService.deleteDoctor(doctorId);
    }
    //Patient CRUD

    @GetMapping("/patients")
    public List<Patient> getAllPatients(){
        return patientService.getAllPatients();
    }

    @GetMapping("/patients/{patientId}")
    public Patient getPatientsById(@PathVariable Long patientId){
        return patientService.getPatientById(patientId);
    }
    @PostMapping("/patients")
    public Patient addNewPatient(@RequestBody Patient patient){
        return patientService.createPatient(patient);
    }

    @PutMapping("/patient/{patientId}")
    public Patient updateExisingPatient(@PathVariable Long patientId,@RequestBody Patient patient){
        return patientService.updatePatient(patientId,patient);
    }
    @DeleteMapping("/patient/{patientId}")
    public void deletePatientById(@PathVariable Long patientId){
        patientService.deletePatient(patientId);
    }


}
