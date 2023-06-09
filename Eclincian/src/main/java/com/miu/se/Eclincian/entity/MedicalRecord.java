package com.miu.se.Eclincian.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "medicalrecord")
@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = MedicalRecord.class)
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    @Column(name = "diagnosedcondition")
    private String diagnosedCondition;
    @Column(name = "treatmentplan")
    private String treatmentPlan;
    @Column(name = "doctornotes")
    private String doctorNotes;

    @ManyToOne
    @JoinColumn(name = "doctorid")
    private Doctor doctor;

    @OneToOne(mappedBy = "medicalRecord") // bidirectional
    private  Patient patient;
}
