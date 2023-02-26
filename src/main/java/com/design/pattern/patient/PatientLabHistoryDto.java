package com.design.pattern.patient;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class PatientLabHistoryDto {

    private int patientId;
    private String labName;
    private int labId;

}
