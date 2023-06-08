package com.miu.se.Eclincian.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "dateofbirth")
    private LocalDate dateOfBirth;
    @Column(name = "emergencycontact")
    private String emergencyContact;
    @Column(name = "contactnumber")
    private String contactNumber;


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    private List<Bill> bills;

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Appointment> appointments;

    @OneToOne
    @JoinColumn(name = "medicalrecord_id")
    private MedicalRecord medicalRecord;
}
