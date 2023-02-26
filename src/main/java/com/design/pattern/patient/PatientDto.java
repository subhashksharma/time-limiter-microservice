package com.design.pattern.patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class PatientDto {

    private Patient patient;
    private PatientLabHistoryDto patientLabHistoryDto;

}
