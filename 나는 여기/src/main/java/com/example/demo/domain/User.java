package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@ToString(exclude = {"locationList"})
public class User {

    @Id
    @Column(updatable=false)
    private String id;

    @Column(nullable = false,unique = true)
    private String email;

    @Column
    private String social;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Location> locationList;
//    @Column
//    private String user_id;
//    User(String id, String email){
//        this.id = id;
//        this.email = email;
//    }

    @Column
    private String phone_number;


//    @OneToMany(mappedBy = "location")
//    private List<Location> locations=new ArrayList<>();

//    @Column
//    private boolean is_kakao=false;
//
//    @Column
//    private boolean is_naver=false;
}
