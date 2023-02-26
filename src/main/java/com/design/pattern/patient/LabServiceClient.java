package com.design.pattern.patient;


import io.github.resilience4j.fallback.FallbackDecorators;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Supplier;

@Service
public class LabServiceClient {

    private static final Logger log = LoggerFactory.getLogger(LabServiceClient.class);


    @Value("${lab.service.endpoint}")
    private String labServiceUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @Retry(name="labService")
    public CompletionStage<PatientLabHistoryDto> getLabHistoryForPatient( int patientId) {
        Supplier<PatientLabHistoryDto> supplier = ()->
            this.restTemplate.getForEntity(this.labServiceUrl+patientId, PatientLabHistoryDto.class).getBody();
        return CompletableFuture.supplyAsync(supplier);
    }

    private CompletionStage<PatientLabHistoryDto> getDefaultLabHistory(int patientId){
        return CompletableFuture.supplyAsync(()-> PatientLabHistoryDto.of(0, "name", 0));
    }

}
