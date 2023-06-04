package com.miu.se.Eclincian.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "medicalrecord")
@Data
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
}
