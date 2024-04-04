package com.lalitha.hospitalmanagement.service;

import com.lalitha.hospitalmanagement.dto.DoctorDto;
import com.lalitha.hospitalmanagement.dto.PatientDto;

import java.util.List;

public interface PatientService {
    PatientDto addPatient(PatientDto patientDto);

    PatientDto getPatientById(Long id);

    List<PatientDto> getAllPatient();

    PatientDto updatePatient(PatientDto patientDto, Long id);

    void deletePatient(Long id);
}
