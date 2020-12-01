package com.example.demo.repository;


import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;


@Repository
public interface UserRepository extends JpaRepository<User,String>{

    public User getUserById(String id);

    public User getUserByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "update User SET phone_number=?1 where id=?2")
    public void updatePhoneNumber(String phoneNumber, String uId);

}

