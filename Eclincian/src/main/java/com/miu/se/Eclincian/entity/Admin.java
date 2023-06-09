package com.miu.se.Eclincian.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}
