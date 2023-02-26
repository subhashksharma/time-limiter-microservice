package com.design.pattern.patient;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletionStage;

@Service
public class PatientService {

    private Logger log = LoggerFactory.getLogger(PatientService.class);

    private Map<Integer, Patient> patientMap;

    @Autowired
    private LabServiceClient labServiceClient;

    @PostConstruct
    private void init() {
        this.patientMap = new HashMap<>();
        this.patientMap.put(1, Patient.of(1, "testPatient1"));
        this.patientMap.put(2, Patient.of(2, "testPatient2"));
    }

    public CompletionStage<PatientDto> getPatientDto(int patientId) {

       return labServiceClient.getLabHistoryForPatient(patientId).thenApply(
                patientLabHistoryDto -> {
                    Patient patient = this.patientMap.get(patientId);
                    return  PatientDto.of(patient, patientLabHistoryDto);
                }
        );
    }
}
