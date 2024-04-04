package com.lalitha.hospitalmanagement.service;

import com.lalitha.hospitalmanagement.dto.MedicationDto;
import com.lalitha.hospitalmanagement.dto.PatientDto;
import com.lalitha.hospitalmanagement.entity.Medication;
import com.lalitha.hospitalmanagement.entity.Patient;
import com.lalitha.hospitalmanagement.repository.MedicationRepository;
import com.lalitha.hospitalmanagement.repository.PatientRepository;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {
    private Patient patient;
    @Spy
    private ModelMapper modelMapper=new ModelMapper();
    @Mock
    private PatientRepository patientRepository;
    @InjectMocks
    private PatientServiceImpl patientService;

    @BeforeEach
    public  void setup(){
        patient = Patient.builder()
                .patientName("john")
                .email("john@gmail.com")
                .contactNo(987654321L)
                .problem("fever")
                .age(28)
                .build();


    }
    @DisplayName("Test when an List of Patient Given returns List Of Patient")
    @Test
    public void givePatient_whenGetAllPatient_thenReturnPatientList() {

        Patient patient1 = Patient.builder()
                .patientName("peter")
                .email("peter@gmail.com")
                .contactNo(987654321L)
                .problem("fever")
                .age(28)
                .build();

        given(patientRepository.findAll()).willReturn(List.of(patient1,patient));
        List<PatientDto> patientDtos=patientService.getAllPatient();
        assertThat(patientDtos).isNotNull();
        assertThat(patientDtos.size()).isGreaterThan(0);
    }
}
