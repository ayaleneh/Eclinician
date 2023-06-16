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
    //see a list of appointment for specfic doctor

    /*Given that I am a doctor and logged into my account,
     When I navigate to a patient's profile,
     Then I should be able to view all past medical records of the patient,
     And I should be able to add new medical records
    * */
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/{doctorId}/appointments")
    public List<Appointment> getAllAppointments(@PathVariable Long doctorId){
        return doctorService.getAllAppointmentsForCurrentDoctor(doctorId);
    }
    @GetMapping("/{doctorId}/upcoming")
    public List<Appointment> getAllUpComingAppointment(@PathVariable Long doctorId){
        return doctorService.getAllUpComingAppointments(doctorId);
    }

    @GetMapping("/{doctorId}")
    public List<Patient> getAllPatients(@PathVariable Long doctorId){
        return doctorService.getAllPatientBelongsToCurrentDoctor(doctorId);
    }
    @PostMapping("/{doctorId}/add-medical-record/{patientId}")
    public MedicalRecord addMedicalRecord(@PathVariable Long doctorId,
                                          @PathVariable Long patientId,
                                          @RequestBody MedicalRecord medicalRecord){
        return doctorService.addMedicalRecordForSelectedPatient(doctorId,patientId,medicalRecord);
    }

    @GetMapping("/{doctorId}/get-medical-record/{patientId}")
    public List<MedicalRecord> getAllMedicalRecord(@PathVariable Long doctorId, @PathVariable Long patientId){
        return doctorService.getAllMedicalRecordForSelectedPatient(patientId,doctorId);
    }
}
