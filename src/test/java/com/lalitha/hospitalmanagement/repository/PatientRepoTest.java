package com.lalitha.hospitalmanagement.repository;

import com.lalitha.hospitalmanagement.dto.DoctorDto;
import com.lalitha.hospitalmanagement.dto.PatientDto;
import com.lalitha.hospitalmanagement.entity.Doctor;
import com.lalitha.hospitalmanagement.entity.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class PatientRepoTest {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private ModelMapper modelMapper;
    private Patient patient;

    @BeforeEach
    public void setup() {

        patient = Patient.builder()
                .patientName("john")
                .email("john@gmail.com")
                .contactNo(987654321L)
                .problem("fever")
                .age(28)
                .build();

    }
    @DisplayName("Test an Patient getting saved in DB")
    @Test
    public void givenPatient_whenSaved_returnSavedPatient(){

        Patient saved=patientRepository.save(patient);
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isGreaterThan(0);
    }
    @DisplayName("Test an Patient getting converted to DTO class")
    @Test
    public void testEntityToDtoConversion() {
        // Create an Order entity


        // Convert the entity to DTO
        PatientDto patientDto = modelMapper.map(patient,PatientDto.class);

        // Assert that the DTO contains the expected values
        assertNotNull(patientDto);
        assertEquals("john",patientDto.getPatientName());
        assertEquals(28,patientDto.getAge());
    }
    @DisplayName("Test when given an list of Patient return the List of Patient")
    @Test
    public void givenPatientList_whenFindAll_returnSavedPatientList(){

        Patient patient1 = Patient.builder()
                .patientName("peter")
                .email("peter@gmail.com")
                .contactNo(987654321L)
                .problem("fever")
                .age(28)
                .build();


        patientRepository.save(patient1);
        patientRepository.save(patient);
        List<Patient> patientList=patientRepository.findAll();
        assertThat(patientList).isNotNull();
        assertThat(patientList.size()).isEqualTo(2);

    }
    @DisplayName("Test when given Patient details to delete removes info from database")
    @Test
    public void givenPatient_WhenDeletePatient_ThenRemoveFromDb() {
        patientRepository.save(patient);
        patientRepository.deleteById(patient.getId());
        Optional<Patient> optionalPatient=patientRepository.findById(patient.getId());
        assertThat(optionalPatient).isEmpty();
    }
    @DisplayName("Test when given Patient ID  update the Patient details")
    @Test
    public void givenPatient_WhenUpdatePatient_ThenReturnUpdatedPatient(){
        patientRepository.save(patient);
        Patient savedPatient=patientRepository.findById(patient.getId()).get();
        savedPatient.setAge(40);
        Patient updatedP=patientRepository.save(savedPatient);
        assertThat(updatedP.getAge()).isEqualTo(40);
    }
}
