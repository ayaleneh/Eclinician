package com.miu.se.Eclincian.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    private String specialization;
    private String qualifications;
    @Column(name = "workinghours")
    private String workingHours;

    @Column(name = "phonenumber")
    private String PhoneNumber;


    @OneToMany(mappedBy = "doctor")
    @JsonBackReference
    private List<Appointment> appointments;
}
