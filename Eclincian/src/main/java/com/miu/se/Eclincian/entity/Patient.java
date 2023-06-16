package com.miu.se.Eclincian.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "patient")
@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Patient.class)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "dateofbirth")
    private LocalDate dateOfBirth;
    @Column(name = "emergencycontact")
    private String emergencyContact;
    @Column(name = "contactnumber")
    private String contactNumber;


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "patient_id")
    private List<Bill> bills;

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Appointment> appointments;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "medicalrecord_id")
    private MedicalRecord medicalRecord;

    @ManyToMany(mappedBy = "patients",cascade = CascadeType.ALL)
    private List<Doctor> doctors;
}
