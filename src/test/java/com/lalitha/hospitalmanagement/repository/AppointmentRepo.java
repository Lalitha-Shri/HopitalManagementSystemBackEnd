package com.lalitha.hospitalmanagement.repository;

import com.lalitha.hospitalmanagement.dto.AppointmentDto;
import com.lalitha.hospitalmanagement.dto.DoctorDto;
import com.lalitha.hospitalmanagement.entity.Appointment;
import com.lalitha.hospitalmanagement.entity.Doctor;
import com.lalitha.hospitalmanagement.entity.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class AppointmentRepo {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ModelMapper modelMapper;
    private Appointment appointment;

    @BeforeEach
    public void setup() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse("2024-03-30", dateFormat);
        Patient patient1=Patient.builder()
                .patientName("lali")
                .email("lali@gmail.com")
                .contactNo(9087645L)
                .problem("fever")
                .age(28).build();
        appointment = Appointment.builder()
                .bookingId("hs1")
                .doctorName("Peter")
                .prescription("5-6")
                .patient(patient1)
                .bookingDate(LocalDate.parse("2024-03-30"))
                .fee(300)
                .cancelStatus(false)
                .build();

    }
    @DisplayName("Test an Appointment getting saved in DB")
    @Test
    public void givenAppointment_whenSaved_returnSavedAppointment(){

        Appointment saved=appointmentRepository.save(appointment);
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isGreaterThan(0);
    }
    @DisplayName("Test an Appointment getting converted to DTO class")
    @Test
    public void testEntityToDtoConversion() {
        // Create an Order entity


        // Convert the entity to DTO
        AppointmentDto appointmentDto = modelMapper.map(appointment,AppointmentDto.class);

        // Assert that the DTO contains the expected values
        assertNotNull(appointmentDto);
        assertEquals("Peter",appointmentDto.getDoctorName());
        assertEquals("hs1",appointmentDto.getBookingId());
    }
    @DisplayName("Test when given an list of Appointment return the List of Appointment")
    @Test
    public void givenAppointmentList_whenFindAll_returnSavedAppointmentList(){

        Patient patient2=Patient.builder()
                .patientName("vicky")
                .email("vicky@gmail.com")
                .contactNo(9097645L)
                .problem("fever")
                .age(28).build();
       Appointment appointment1 = Appointment.builder()
                .bookingId("hs2")
                .doctorName("Peter")
                .prescription("5-6")
                .patient(patient2)
                .bookingDate(LocalDate.parse("2024-03-30"))
                .fee(300)
                .cancelStatus(false)
                .build();


        appointmentRepository.save(appointment1);
        appointmentRepository.save(appointment);
        List<Appointment> appointmentList=appointmentRepository.findAll();
        assertThat(appointmentList).isNotNull();
        assertThat(appointmentList.size()).isEqualTo(2);

    }
    @DisplayName("Test when given Appointmnet ID Get appointmnet by id")
    @Test
    public void givenId_WhenFindById_ThenReturnAppointmnet(){
        appointmentRepository.save(appointment);
        Optional<Appointment> savedAppo=appointmentRepository.findById(appointment.getId());

        assertThat(savedAppo).isNotNull();
        assertThat(appointment.getId()).isEqualTo(savedAppo.get().getId());
    }
}
