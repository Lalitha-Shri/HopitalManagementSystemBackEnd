package com.lalitha.hospitalmanagement.service;

import com.lalitha.hospitalmanagement.dto.AppointmentDto;
import com.lalitha.hospitalmanagement.entity.Appointment;
import com.lalitha.hospitalmanagement.entity.Patient;
import com.lalitha.hospitalmanagement.repository.AppointmentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService{
    private ModelMapper modelMapper;
    private AppointmentRepository appointmentRepository;
    @Override
    public AppointmentDto add(AppointmentDto appointmentDto) {
        Appointment appointment=modelMapper.map(appointmentDto, Appointment.class);
        Appointment saved=appointmentRepository.save(appointment);
        return modelMapper.map(saved, AppointmentDto.class);

    }

    @Override
    public AppointmentDto getById(Long id) {
        return null;
    }

    @Override
    public List<AppointmentDto> getAll() {
        List<Appointment> all= appointmentRepository.findAll();
        return all.stream().map(appointment->modelMapper.map(appointment, AppointmentDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDto> getByPatient(Patient patient) {
        return null;
    }

    @Override
    public AppointmentDto getByPatientId(Long id) {
        Appointment appointment=appointmentRepository.findByPatient_id(id);
        return modelMapper.map(appointment,AppointmentDto.class);
    }

    @Override
    public AppointmentDto cancelTicket(Long id) {
        Appointment appointment=appointmentRepository.findById(id)
                .get();
        if(!appointment.getCancelStatus()) {
            appointment.setCancelStatus(Boolean.TRUE);
        }
        Appointment updatedAppointment=appointmentRepository.save(appointment);
        return modelMapper.map(updatedAppointment,AppointmentDto.class);
    }
}
