package com.lalitha.hospitalmanagement.controller;

import com.lalitha.hospitalmanagement.dto.DoctorDto;
import com.lalitha.hospitalmanagement.dto.PatientDto;
import com.lalitha.hospitalmanagement.repository.DoctorRepository;
import com.lalitha.hospitalmanagement.repository.PatientRepository;
import com.lalitha.hospitalmanagement.service.DoctorService;
import com.lalitha.hospitalmanagement.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/patient")
@AllArgsConstructor
public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientRepository patientRepository;
    @PostMapping    //Postmapping is to add the new doctor details to database
    public ResponseEntity<PatientDto> saveDoctor(@RequestBody PatientDto patientDto)
    {
        PatientDto savePatient=patientService.addPatient(patientDto);
        return new ResponseEntity<>(savePatient, HttpStatus.CREATED);
    }
    //Getmapping is to get all the doctor details
    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatients()
    {
        List<PatientDto> allPatient=patientService.getAllPatient();
        return new ResponseEntity<List<PatientDto>>(allPatient,HttpStatus.OK);
    }
    @DeleteMapping("/{id}") //delete doctor by id
    public  String deletePatient(@PathVariable("id")Long patientId)
    {
        patientService.deletePatient(patientId);
        return "Patient is deleted";
    }
    @GetMapping("/{patientId}") //get doctor by id
    public ResponseEntity<PatientDto> getUserId(@PathVariable("patientId")Long userId)
    {
        PatientDto getPatient=patientService.getPatientById(userId);
        return new ResponseEntity<PatientDto>(getPatient,HttpStatus.OK);
    }
    @PutMapping("/{id}")//update doctor details by id
    public ResponseEntity<PatientDto> updateDoctor(@PathVariable("id") Long id, @RequestBody  PatientDto patientDto)
    {
        PatientDto updatedPatient=patientService.updatePatient(patientDto,id);
        return new ResponseEntity<>(updatedPatient,HttpStatus.OK);
    }
}
