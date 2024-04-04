package com.lalitha.hospitalmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(nullable = false)
    private String patientName;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String problem;
    @Column(nullable = false)
    private Long contactNo;
    @Column(nullable = false)
    private int age;

}
