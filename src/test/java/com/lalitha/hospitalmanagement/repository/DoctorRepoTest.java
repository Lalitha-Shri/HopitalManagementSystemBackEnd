package com.lalitha.hospitalmanagement.repository;

import com.lalitha.hospitalmanagement.dto.DoctorDto;
import com.lalitha.hospitalmanagement.entity.Doctor;
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
public class DoctorRepoTest {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ModelMapper modelMapper;
    private Doctor doctor;

    @BeforeEach
    public void setup() {

        doctor = Doctor.builder()
                .doctorName("john")
                .specialist("cardiology")
                .contactNo(987654321L)
                .experience(10)
                .age(28)
                .qualification("MBBS")
                .build();

    }
    @DisplayName("Test an Doctor getting saved in DB")
    @Test
    public void givenDoctor_whenSaved_returnSavedDoctor(){

        Doctor savedDoctor=doctorRepository.save(doctor);
        assertThat(savedDoctor).isNotNull();
        assertThat(savedDoctor.getId()).isGreaterThan(0);
    }
    @DisplayName("Test an Doctor getting converted to DTO class")
    @Test
    public void testEntityToDtoConversion() {
        // Create an Order entity


        // Convert the entity to DTO
        DoctorDto doctorDto = modelMapper.map(doctor,DoctorDto.class);

        // Assert that the DTO contains the expected values
        assertNotNull(doctorDto);
        assertEquals("cardiology",doctorDto.getSpecialist());
        assertEquals("john",doctorDto.getDoctorName());
    }
    @DisplayName("Test when given an list of Doctor return the List of doctor")
    @Test
    public void givenDoctorList_whenFindAll_returnSavedDoctorList(){

        Doctor doctor1 = Doctor.builder()
                .doctorName("peter")
                .specialist("Immunology")
                .contactNo(987654321L)
                .experience(5)
                .age(28)
                .qualification("MBBS")
                .build();


        doctorRepository.save(doctor);
        doctorRepository.save(doctor1);
        List<Doctor> doctorList=doctorRepository.findAll();
        assertThat(doctorList).isNotNull();
        assertThat(doctorList.size()).isEqualTo(2);

    }
    @DisplayName("Test when given Doctor ID  update the Doctor details")
    @Test
    public void givenDoctor_WhenUpdateDoctor_ThenReturnUpdatedDoctor(){
        doctorRepository.save(doctor);
        Doctor savedDoc=doctorRepository.findById(doctor.getId()).get();
        savedDoc.setAge(40);
        Doctor updatedDoc=doctorRepository.save(savedDoc);
        assertThat(updatedDoc.getAge()).isEqualTo(40);
    }
    @DisplayName("Test when given Doctor details to delete removes info from database")
    @Test
    public void givenDoctor_WhenDeleteDoctor_ThenRemoveFromDb() {
        doctorRepository.save(doctor);
        doctorRepository.deleteById(doctor.getId());
        Optional<Doctor> optionalDoctor=doctorRepository.findById(doctor.getId());
        assertThat(optionalDoctor).isEmpty();
    }

}