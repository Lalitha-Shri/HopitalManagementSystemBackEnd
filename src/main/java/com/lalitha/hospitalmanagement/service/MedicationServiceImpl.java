package com.lalitha.hospitalmanagement.service;

import com.lalitha.hospitalmanagement.dto.DoctorDto;
import com.lalitha.hospitalmanagement.dto.MedicationDto;
import com.lalitha.hospitalmanagement.entity.Doctor;
import com.lalitha.hospitalmanagement.entity.Medication;
import com.lalitha.hospitalmanagement.repository.DoctorRepository;
import com.lalitha.hospitalmanagement.repository.MedicationRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MedicationServiceImpl implements MedicationService {
    private MedicationRepository medicationRepository;
    private ModelMapper modelMapper;
    @Override
    public MedicationDto addMedication(MedicationDto medicationDto) {
        Medication medication=modelMapper.map(medicationDto, Medication.class);
        Medication saveMed=medicationRepository.save(medication);
        return modelMapper.map(saveMed, MedicationDto.class);
    }

    @Override
    public List<MedicationDto> getAllMedication() {
        List<Medication> medicationList= medicationRepository.findAll();
        return medicationList.stream().map(med->modelMapper.map(med,MedicationDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<MedicationDto> findByPatientName(String name) {

        List<Medication> medication=medicationRepository.findByPatientName(name);
        return medication.stream().map(med->modelMapper.map(med,MedicationDto.class)).collect(Collectors.toList());
    }
}
