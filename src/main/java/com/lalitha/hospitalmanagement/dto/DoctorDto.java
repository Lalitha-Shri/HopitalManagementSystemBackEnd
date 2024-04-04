package com.lalitha.hospitalmanagement.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {
    private Long Id;

    private String doctorName;

    private String specialist;

    private String qualification;

    private int experience;

        private int age;

        private Long contactNo;


}
