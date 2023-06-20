package com.miu.se.Eclincian.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    //when creating an account here is how it should go...once the user fills their field,
    //the system should take them to the next page based on their role and fill the rest with the field.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fname")
    private String fName;
    @Column(name = "lname")
    private String lName;
    private String email;

    private String address; // due to the complexity of my project, it would be nice if address will be attribute instead of entity by itself.
    private String username;
    private String password;
    //    @Enumerated(EnumType.STRING)
//    private Roles role;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Role> role;
}
