package com.lalitha.hospitalmanagement.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(nullable = false)
    private String doctorName;
    @Column(nullable = false)
    private String specialist;
    @Column(nullable = false)
    private String qualification;
    @Column(nullable = false)
    private int experience;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false)
    private Long contactNo;

}
