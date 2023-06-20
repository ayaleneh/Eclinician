package com.miu.se.Eclincian.controller;

import com.miu.se.Eclincian.entity.Bill;
import com.miu.se.Eclincian.entity.Doctor;
import com.miu.se.Eclincian.entity.Patient;
import com.miu.se.Eclincian.exceptions.CustomErrorType;
import com.miu.se.Eclincian.service.AppointmentService;
import com.miu.se.Eclincian.service.BillService;
import com.miu.se.Eclincian.service.DoctorService;
import com.miu.se.Eclincian.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final BillService billService;

    public AdminController(AppointmentService appointmentService,
                           DoctorService doctorService,
                           PatientService patientService,
                           BillService billService) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.billService = billService;
    }

    @GetMapping("/appointments")
    public ResponseEntity<?> getAllAppointments() {
        try {
            return new ResponseEntity<>(appointmentService.getAllAppointment(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomErrorType(e.getMessage()), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/bill/patient/{patientId}")
    public ResponseEntity<?> addNewBill(@PathVariable Long patientId, Bill bill) {
        try {
            return new ResponseEntity<>(billService.createBill(patientId, bill), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomErrorType(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/doctor/{doctorId}")
    public Doctor getDoctorById(@PathVariable Long doctorId) {
        return doctorService.getDoctorById(doctorId);
    }

    @PostMapping("/doctor")
    public Doctor addNewDoctor(@RequestBody Doctor doctor) {
        return doctorService.createDoctor(doctor);
    }

    @PutMapping("/doctor/{doctorId}")
    public Doctor updateExistingDoctor(@PathVariable Long doctorId, @RequestBody Doctor doctor) {
        return doctorService.updateDoctor(doctorId, doctor);
    }

    @DeleteMapping("/doctor/{doctorId}")
    public void deleteDoctorById(@PathVariable Long doctorId) {
        doctorService.deleteDoctor(doctorId);
    }
    //Patient CRUD

    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/patients/{patientId}")
    public Patient getPatientsById(@PathVariable Long patientId) {
        return patientService.getPatientById(patientId);
    }

    @PostMapping("/patients")
    public Patient addNewPatient(@RequestBody Patient patient) {
        return patientService.createPatient(patient);
    }

    @PutMapping("/patient/{patientId}")
    public Patient updateExisingPatient(@PathVariable Long patientId, @RequestBody Patient patient) {
        return patientService.updatePatient(patientId, patient);
    }

    @DeleteMapping("/patient/{patientId}")
    public void deletePatientById(@PathVariable Long patientId) {
        patientService.deletePatient(patientId);
    }

}
