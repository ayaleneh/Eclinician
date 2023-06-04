package com.miu.se.Eclincian.controller;

import com.miu.se.Eclincian.service.PatientService;
import com.miu.se.Eclincian.service.implmentation.PatientServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService){
        this.patientService=patientService;
    }
}
