package com.miu.se.Eclincian.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "doctor")
@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Doctor.class)
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    private String specialization;
    private String qualifications;
    @Column(name = "workinghours")
    private String workingHours;

    @Column(name = "phonenumber")
    private String PhoneNumber;


    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Appointment> appointments;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "doctor_patient",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id"))
    private List<Patient> patients;

}
