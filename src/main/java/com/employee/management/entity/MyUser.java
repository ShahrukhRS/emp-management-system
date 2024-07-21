package com.employee.management.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String password;
    private String role;
}
