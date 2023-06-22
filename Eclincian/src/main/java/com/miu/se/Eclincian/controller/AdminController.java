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
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return new ResponseEntity<>(doctorService.getAllDoctors(), HttpStatus.OK);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long doctorId) {
        return new ResponseEntity<>(doctorService.getDoctorById(doctorId), HttpStatus.OK);
    }

    @PostMapping("/doctor")
    public ResponseEntity<Doctor> addNewDoctor(@RequestBody Doctor doctor) {
        return new ResponseEntity<>(doctorService.createDoctor(doctor), HttpStatus.CREATED);
    }

    @PutMapping("/doctor/{doctorId}")
    public ResponseEntity<Doctor> updateExistingDoctor(@PathVariable Long doctorId, @RequestBody Doctor doctor) {
        return new ResponseEntity<>(doctorService.updateDoctor(doctorId, doctor), HttpStatus.OK);
    }

    @DeleteMapping("/doctor/{doctorId}")
    public ResponseEntity<?> deleteDoctorById(@PathVariable Long doctorId) {
        doctorService.deleteDoctor(doctorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Patient CRUD

    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> getAllPatients() {
        return new ResponseEntity<>(patientService.getAllPatients(),HttpStatus.OK);
    }

    @GetMapping("/patients/{patientId}")
    public ResponseEntity<Patient> getPatientsById(@PathVariable Long patientId) {
        return new ResponseEntity<>(patientService.getPatientById(patientId),HttpStatus.OK);
    }

    @PostMapping("/patients")
    public ResponseEntity<Patient> addNewPatient(@RequestBody Patient patient) {
        return new ResponseEntity<>(patientService.createPatient(patient),HttpStatus.CREATED);
    }

    @PutMapping("/patient/{patientId}")
    public ResponseEntity<Patient> updateExisingPatient(@PathVariable Long patientId, @RequestBody Patient patient) {
        return new ResponseEntity<>(patientService.updatePatient(patientId, patient),HttpStatus.OK);
    }

    @DeleteMapping("/patient/{patientId}")
    public ResponseEntity<?> deletePatientById(@PathVariable Long patientId) {
        patientService.deletePatient(patientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
