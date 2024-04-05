package com.lalitha.hospitalmanagement.controller;

import com.lalitha.hospitalmanagement.dto.AppointmentDto;
import com.lalitha.hospitalmanagement.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/appointment")
@AllArgsConstructor
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    //Post Mapping is to call post endpoints that saves the request body
    @PostMapping
    public ResponseEntity<AppointmentDto> saveAppointment(@RequestBody AppointmentDto appointmentDto)
    {
        AppointmentDto savedBus=appointmentService.add(appointmentDto);
        return new ResponseEntity<>(savedBus, HttpStatus.CREATED);
    }
    //Getmapping is to get all the appointment details
    @GetMapping
    public ResponseEntity<List<AppointmentDto>> getAllAppointment()
    {
        List<AppointmentDto> allAppointment=appointmentService.getAll();
        return new ResponseEntity<List<AppointmentDto>>(allAppointment,HttpStatus.OK);

    }
    //Getmapping is to get  the appointment details based on id
    @GetMapping("/patient/{id}")
    public ResponseEntity<AppointmentDto> getbypatient_id(@PathVariable("id") Long id)
    {
        AppointmentDto appointmentDto=appointmentService.getByPatientId(id);
        return new ResponseEntity<AppointmentDto>(appointmentDto,HttpStatus.OK);
    }
    //Putmapping is to update the appointment details when id is given
    @PutMapping("cancelAppointment/{id}")
    public ResponseEntity<AppointmentDto> cancelTicket(@PathVariable("id") Long id){
        AppointmentDto updatedAppointment=appointmentService.cancelTicket(id);
        return ResponseEntity.ok(updatedAppointment);

    }


}
