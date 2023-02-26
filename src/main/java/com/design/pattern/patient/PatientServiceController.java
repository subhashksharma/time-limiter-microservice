package com.design.pattern.patient;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletionStage;

@RestController
@RequestMapping("patient")
public class PatientServiceController {

    @Autowired
    private PatientService patientService;


    @GetMapping("{patientId}")
    public CompletionStage<PatientDto> getPatient(@PathVariable int patientId) {

        return this.patientService.getPatientDto(patientId);
    }
}
