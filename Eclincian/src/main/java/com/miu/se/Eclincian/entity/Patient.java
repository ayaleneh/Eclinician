package com.miu.se.Eclincian.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "patient")
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "dateofbirth")
    private LocalDate dateOfBirth;
    @Column(name = "emergencycontact")
    private String emergencyContact;
    @Column(name = "contactnumber")
    private String contactNumber;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinColumn(name = "patient_id")
    private List<Bill> bills;

    @OneToMany
    @JoinColumn(name = "patient_id")
    private List<Appointment> appointments;

    @OneToOne
    @JoinColumn(name = "medicalrecord_id")
    private MedicalRecord medicalRecord;
}
