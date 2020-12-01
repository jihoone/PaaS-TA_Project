package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@ToString(exclude = {"user"})
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable=false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 위도
    @Column
    private double latitude;

    // 경도
    @Column
    private double longitude;

    @Column
    private Timestamp created_at;


}
