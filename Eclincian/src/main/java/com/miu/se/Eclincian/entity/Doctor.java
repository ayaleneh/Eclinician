package com.miu.se.Eclincian.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "doctor")
@Data
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String specialization;
    private String qualifications;
    @Column(name = "workinghours")
    private String workingHours;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinColumn(name = "doctor_id")
    private List<Appointment> appointments;
}
