package com.lalitha.hospitalmanagement.service;

import com.lalitha.hospitalmanagement.dto.AppointmentDto;
import com.lalitha.hospitalmanagement.entity.Patient;

import java.util.List;

public interface AppointmentService {
    AppointmentDto add(AppointmentDto appointmentDto);
    AppointmentDto getById(Long id);
    List<AppointmentDto> getAll();
    List<AppointmentDto> getByPatient(Patient patient);

    AppointmentDto getByPatientId(Long id);
    AppointmentDto cancelTicket(Long id);
}
