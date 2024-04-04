package com.lalitha.hospitalmanagement.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
    private Long Id;

    private String patientName;

    private String email;

    private String problem;

    private Long contactNo;

    private int age;
}
