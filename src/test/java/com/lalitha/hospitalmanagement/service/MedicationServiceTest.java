package com.lalitha.hospitalmanagement.service;

import com.lalitha.hospitalmanagement.dto.DoctorDto;
import com.lalitha.hospitalmanagement.dto.MedicationDto;
import com.lalitha.hospitalmanagement.entity.Doctor;
import com.lalitha.hospitalmanagement.entity.Medication;
import com.lalitha.hospitalmanagement.repository.DoctorRepository;
import com.lalitha.hospitalmanagement.repository.MedicationRepository;
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
public class MedicationServiceTest {
    private Medication medication;
    @Spy
    private ModelMapper modelMapper=new ModelMapper();
    @Mock
    private MedicationRepository medicationRepository;
    @InjectMocks
    private MedicationServiceImpl medicationService;

    @BeforeEach
    public  void setup(){
        medication  = Medication.builder()
                .patientName("john")
                .medicationName("Paracetomol")
                .appoinmentDate(LocalDate.parse("2024-03-01"))
                .morning(1)
                .afternoon(2)
                .night(1)
                .build();


    }
    @DisplayName("Test when an List of Medication Given returns List Of Medication")
    @Test
    public void giveMedication_whenGetAllMedication_thenReturnMedicationrList() {

        Medication   medication1 = Medication.builder()
                .patientName("john")
                .medicationName("Paracetomol")
                .appoinmentDate(LocalDate.parse("2024-03-01"))
                .morning(1)
                .afternoon(2)
                .night(1)
                .build();

        given(medicationRepository.findAll()).willReturn(List.of(medication,medication1));
        List<MedicationDto> medicationDtoList=medicationService.getAllMedication();
        assertThat(medicationDtoList).isNotNull();
        assertThat(medicationDtoList.size()).isGreaterThan(0);
    }
}
