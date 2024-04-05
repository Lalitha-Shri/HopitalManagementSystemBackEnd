package com.lalitha.hospitalmanagement.service;

import com.lalitha.hospitalmanagement.dto.DoctorDto;
import com.lalitha.hospitalmanagement.entity.Doctor;
import com.lalitha.hospitalmanagement.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@AllArgsConstructor
@Service
public class DoctorServiceImpl implements DoctorService{
    private DoctorRepository doctorRepository;
    private ModelMapper modelMapper;
    @Override//add the doctor to db by converting from dto to entity using model mapper
    public DoctorDto addDoctor(DoctorDto doctorDto) {
        Doctor doctor=modelMapper.map(doctorDto, Doctor.class);
        Doctor saveDoctor=doctorRepository.save(doctor);
        return modelMapper.map(saveDoctor, DoctorDto.class);
    }

    @Override//get the doctor by id from db and converting from entity to dto using model mapper
    public DoctorDto getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id).get();
        return modelMapper.map(doctor,DoctorDto.class);
    }

    @Override//get all the doctor  from db and converting from entity to dto using model mapper
    public List<DoctorDto> getAllDoctor() {
        List<Doctor> doctorList= doctorRepository.findAll();
        return doctorList.stream().map(doctor->modelMapper.map(doctor,DoctorDto.class)).collect(Collectors.toList());
    }

    @Override//update the doctor by id from db and converting from entity to dto using model mapper
    public DoctorDto updateDoctor(DoctorDto doctorDto, Long id) {
        Doctor doctor= doctorRepository.findById(id).get();
        doctor.setDoctorName(doctorDto.getDoctorName());
        doctor.setQualification(doctorDto.getQualification());
        doctor.setExperience(doctorDto.getExperience());
        doctor.setAge(doctorDto.getAge());
        doctor.setContactNo(doctorDto.getContactNo());
        doctor.setSpecialist(doctorDto.getSpecialist());
        Doctor updatedDoctor=doctorRepository.save(doctor);
        return modelMapper.map(updatedDoctor,DoctorDto.class);
    }

    @Override//delete the appoinment by id from db 
    public void deleteDoctor(Long id) {
        Doctor doctor=doctorRepository.findById(id).get();
        doctorRepository.delete(doctor);

    }
}
