package com.lalitha.hospitalmanagement.service;

import com.lalitha.hospitalmanagement.dto.DoctorDto;
import com.lalitha.hospitalmanagement.dto.PatientDto;
import com.lalitha.hospitalmanagement.entity.Doctor;
import com.lalitha.hospitalmanagement.entity.Patient;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DoctorService {
    DoctorDto addDoctor(DoctorDto doctorDto);

    DoctorDto getDoctorById(Long id);

    List<DoctorDto> getAllDoctor();

    DoctorDto updateDoctor(DoctorDto doctorDto, Long id);

    void deleteDoctor(Long id);


}
