package com.lalitha.hospitalmanagement.controller;

import com.lalitha.hospitalmanagement.dto.MedicationDto;
import com.lalitha.hospitalmanagement.dto.PatientDto;
import com.lalitha.hospitalmanagement.repository.MedicationRepository;
import com.lalitha.hospitalmanagement.repository.PatientRepository;
import com.lalitha.hospitalmanagement.service.MedicationService;
import com.lalitha.hospitalmanagement.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/medication")
@AllArgsConstructor
public class MedicationController {
    @Autowired
    private MedicationService medicationService;
    @Autowired
    private MedicationRepository medicationRepository;
    @PostMapping    //Postmapping is to add the new appointment details to database
    public ResponseEntity<MedicationDto> saveMedication(@RequestBody MedicationDto medicationDto)
    {
        MedicationDto saveMedication=medicationService.addMedication(medicationDto);
        return new ResponseEntity<>(saveMedication, HttpStatus.CREATED);
    }
    //Getmapping is to get all the doctor details
    @GetMapping
    public ResponseEntity<List<MedicationDto>> getAllMedication()
    {
        List<MedicationDto> allMedication=medicationService.getAllMedication();
        return new ResponseEntity<List<MedicationDto>>(allMedication,HttpStatus.OK);
    }
    @GetMapping("patient/{name}")
    public ResponseEntity<List<MedicationDto>> getpatientbyName(@PathVariable String name)
    {
       List<MedicationDto> medicationDto=medicationService.findByPatientName(name);
        return new ResponseEntity<List<MedicationDto>>(medicationDto,HttpStatus.OK);
    }

}
