package com.lalitha.hospitalmanagement.service;

import com.lalitha.hospitalmanagement.dto.DoctorDto;
import com.lalitha.hospitalmanagement.dto.PatientDto;
import com.lalitha.hospitalmanagement.entity.Doctor;
import com.lalitha.hospitalmanagement.entity.Patient;
import com.lalitha.hospitalmanagement.repository.DoctorRepository;
import com.lalitha.hospitalmanagement.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor

public class PatientServiceImpl implements PatientService {
    private PatientRepository patientRepository;
    private ModelMapper modelMapper;

    @Override
    public PatientDto addPatient(PatientDto patientDto) {
        Patient patient=modelMapper.map(patientDto, Patient.class);
        Patient savePatient=patientRepository.save(patient);
        return modelMapper.map(savePatient, PatientDto.class);
    }

    @Override
    public PatientDto getPatientById(Long id) {
        Patient patient = patientRepository.findById(id).get();
        return modelMapper.map(patient,PatientDto.class);
    }

    @Override
    public List<PatientDto> getAllPatient() {
        List<Patient> patientList= patientRepository.findAll();
        return patientList.stream().map(patient->modelMapper.map(patient,PatientDto.class)).collect(Collectors.toList());
    }

    @Override
    public PatientDto updatePatient(PatientDto patientDto, Long id) {
        Patient patient= patientRepository.findById(id).get();
        patient.setPatientName(patientDto.getPatientName());
        patient.setAge(patientDto.getAge());
        patient.setProblem(patientDto.getProblem());
        patient.setContactNo(patientDto.getContactNo());
        patient.setEmail(patientDto.getEmail());

        Patient updatedPatient=patientRepository.save(patient);
        return modelMapper.map(updatedPatient,PatientDto.class);
    }

    @Override
    public void deletePatient(Long id) {
        Patient patient=patientRepository.findById(id).get();
        patientRepository.delete(patient);

    }
}
