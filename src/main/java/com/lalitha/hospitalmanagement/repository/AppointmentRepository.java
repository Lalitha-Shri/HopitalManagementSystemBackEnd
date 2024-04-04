package com.lalitha.hospitalmanagement.repository;

import com.lalitha.hospitalmanagement.entity.Appointment;
import com.lalitha.hospitalmanagement.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    Appointment findByPatient_id(Long id);
}
