package com.lalitha.hospitalmanagement.controller;

import com.lalitha.hospitalmanagement.dto.DoctorDto;
import com.lalitha.hospitalmanagement.repository.DoctorRepository;
import com.lalitha.hospitalmanagement.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/doctor")
@AllArgsConstructor
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private DoctorRepository doctorRepository;
    @PostMapping    //Postmapping is to add the new doctor details to database
    public ResponseEntity<DoctorDto> saveDoctor(@RequestBody  DoctorDto doctorDto)
    {
        DoctorDto saveDoctor=doctorService.addDoctor(doctorDto);
        return new ResponseEntity<>(saveDoctor, HttpStatus.CREATED);
    }
    //Getmapping is to get all the doctor details
    @GetMapping
    public ResponseEntity<List<DoctorDto>> getAllDocters()
    {
        List<DoctorDto> allDoctor=doctorService.getAllDoctor();
        return new ResponseEntity<List<DoctorDto>>(allDoctor,HttpStatus.OK);
    }
    @DeleteMapping("/{id}") //delete doctor by id
    public  String deleteUser(@PathVariable("id")Long userId)
    {
        doctorService.deleteDoctor(userId);
        return "Doctor is deleted";
    }
    @GetMapping("/{doctorId}") //get doctor by id
    public ResponseEntity<DoctorDto> getUserId(@PathVariable("doctorId")Long userId)
    {
        DoctorDto getUser=doctorService.getDoctorById(userId);
        return new ResponseEntity<DoctorDto>(getUser,HttpStatus.OK);
    }
    @PutMapping("/{id}")//update doctor details by id
    public ResponseEntity<DoctorDto> updateDoctor(@PathVariable("id") Long id, @RequestBody  DoctorDto doctorDto)
    {
        DoctorDto updatedDoctor=doctorService.updateDoctor(doctorDto,id);
        return new ResponseEntity<>(updatedDoctor,HttpStatus.OK);
    }


}
