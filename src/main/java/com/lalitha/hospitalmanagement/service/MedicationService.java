package com.lalitha.hospitalmanagement.service;

import com.lalitha.hospitalmanagement.dto.AppointmentDto;
import com.lalitha.hospitalmanagement.dto.DoctorDto;
import com.lalitha.hospitalmanagement.dto.MedicationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


public interface MedicationService {
    MedicationDto addMedication(MedicationDto medicationDto);

    List<MedicationDto> getAllMedication();
    List<MedicationDto> findByPatientName(String name);


}
