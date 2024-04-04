package com.lalitha.hospitalmanagement.service;

import com.lalitha.hospitalmanagement.dto.AppointmentDto;
import com.lalitha.hospitalmanagement.entity.Appointment;
import com.lalitha.hospitalmanagement.entity.Patient;
import com.lalitha.hospitalmanagement.repository.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceTest {
    private Appointment appointment;
    @Spy
    private ModelMapper modelMapper=new ModelMapper();
    @Mock
    private AppointmentRepository appointmentRepository;
    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    @BeforeEach
    public  void setup(){
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
    @DisplayName("Test when an List of Appointments Given returns List Of Appointments")
    @Test
    public void giveBusAppointments_whenGetAllAppointments_thenReturnAppointmentsList() {

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

        given(appointmentRepository.findAll()).willReturn(List.of(appointment1,appointment));
        List<AppointmentDto> appointmentDtos=appointmentService.getAll();
        assertThat(appointmentDtos).isNotNull();
        assertThat(appointmentDtos.size()).isGreaterThan(0);
    }

}
