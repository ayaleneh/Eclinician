package com.miu.se.Eclincian.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "appointment")
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "appointmenttime")
    private LocalTime appointmentTime;
    @Column(name = "appointmentdate")
    private LocalDate appointmentDate;
}
