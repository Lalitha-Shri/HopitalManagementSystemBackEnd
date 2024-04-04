package com.lalitha.hospitalmanagement.repository;

import com.lalitha.hospitalmanagement.dto.DoctorDto;
import com.lalitha.hospitalmanagement.dto.MedicationDto;
import com.lalitha.hospitalmanagement.entity.Appointment;
import com.lalitha.hospitalmanagement.entity.Doctor;
import com.lalitha.hospitalmanagement.entity.Medication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class MedicationRepoTest {
    @Autowired
    private MedicationRepository medicationRepository;
    @Autowired
    private ModelMapper modelMapper;
    private Medication medication;

    @BeforeEach
    public void setup() {

        medication = Medication.builder()
                .patientName("john")
                .medicationName("Paracetomol")
                .appoinmentDate(LocalDate.parse("2024-03-01"))
                .morning(1)
                .afternoon(2)
                .night(1)
                .build();

    }
    @DisplayName("Test an Medication getting saved in DB")
    @Test
    public void givenMedication_whenSaved_returnSavedMedication(){

        Medication saved=medicationRepository.save(medication);
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isGreaterThan(0);
    }
    @DisplayName("Test an Medication getting converted to DTO class")
    @Test
    public void testEntityToDtoConversion() {
        // Create an Order entity


        // Convert the entity to DTO
        MedicationDto medicationDto = modelMapper.map(medication,MedicationDto.class);

        // Assert that the DTO contains the expected values
        assertNotNull(medicationDto);
        assertEquals("john",medicationDto.getPatientName());
        assertEquals("Paracetomol",medicationDto.getMedicationName());
    }
    @DisplayName("Test when given an list of Medication return the List of Medication")
    @Test
    public void givenDoctorMedication_whenFindAll_returnSavedMedicationList(){

        Medication medication1 = Medication.builder()
                .patientName("john")
                .medicationName("Paracetomol")
                .appoinmentDate(LocalDate.parse("2024-03-01"))
                .morning(1)
                .afternoon(2)
                .night(1)
                .build();


        medicationRepository.save(medication1);
        medicationRepository.save(medication);
        List<Medication> medications=medicationRepository.findAll();
        assertThat(medications).isNotNull();
        assertThat(medications.size()).isEqualTo(2);

    }
}
