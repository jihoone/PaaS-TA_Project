package com.example.demo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table
public class User {

    @Id
    @Column(updatable=false)
    private String id;

    @Column(nullable = false,unique = true)
    private String email;

    @Column
    private String social;

    @Column
    private String phone;

}
