package com.lalitha.hospitalmanagement.service;

import com.lalitha.hospitalmanagement.dto.DoctorDto;
import com.lalitha.hospitalmanagement.entity.Doctor;
import com.lalitha.hospitalmanagement.repository.DoctorRepository;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {
    private Doctor doctor;
    @Spy
    private ModelMapper modelMapper=new ModelMapper();
    @Mock
    private DoctorRepository doctorRepository;
    @InjectMocks
    private DoctorServiceImpl doctorService;

    @BeforeEach
    public  void setup(){
       doctor = Doctor.builder()
               .doctorName("peter")
               .specialist("Immunology")
               .contactNo(987654321L)
               .experience(5)
               .age(28)
               .qualification("MBBS")
               .build();


    }
    @DisplayName("Test when an List of Doctor Given returns List Of Doctor")
    @Test
    public void giveBusDoctor_whenGetAllDoctor_thenReturnDoctorList() {

      Doctor  doctor1 = Doctor.builder()
                .doctorName("john")
                .specialist("Immunology")
                .contactNo(987654321L)
                .experience(5)
                .age(28)
                .qualification("MBBS")
                .build();

        given(doctorRepository.findAll()).willReturn(List.of(doctor1,doctor));
        List<DoctorDto> doctorDtoList=doctorService.getAllDoctor();
        assertThat(doctorDtoList).isNotNull();
        assertThat(doctorDtoList.size()).isGreaterThan(0);
    }
    @DisplayName("Test when an Doctor Id Given returns Updated Doctor")
    @Test
    public void givenDoctorId_whenUpdateDoctor_thenReturnUpdatedDoctor(){
        given(doctorRepository.save(doctor)).willReturn(doctor);
        given(doctorRepository.findById(doctor.getId())).willReturn(Optional.of(doctor));
        doctor.setAge(50);
        DoctorDto doctorDto = modelMapper.map(doctor, DoctorDto.class);
        DoctorDto savedDto = doctorService.updateDoctor(doctorDto,doctor.getId());
        Doctor saved = modelMapper.map(savedDto, Doctor.class);
        assertThat(saved.getAge()).isEqualTo(50);
    }

}
