package com.miu.se.Eclincian.controller;

import com.miu.se.Eclincian.service.DoctorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    //@PostMapping("/add-medical-records")

}
