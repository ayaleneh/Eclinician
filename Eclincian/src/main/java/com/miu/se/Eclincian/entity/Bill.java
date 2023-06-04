package com.miu.se.Eclincian.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    @Column(name = "issuedate")
    private LocalDate issueDate;
}
