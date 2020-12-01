package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.KakaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Optional;

@Controller
public class UserInfoController extends KakaoService {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/createForm")
    public String createForm(){
        return "createForm";
    }

    @GetMapping("/user/info")
    public String createForm(HttpServletRequest result, HttpSession session){

        String phoneNumber = result.getParameter("phoneNumber");
        String uId = session.getAttribute("id").toString();

        System.out.println("user id : " + uId);
        System.out.println("phoneNumber : " + phoneNumber);

        userRepository.updatePhoneNumber(phoneNumber,uId);

        return "kakaocallback";
    }

//    @PostMapping("/user/info")
//    public String create(){
//        return "redirect:/";
//    }


}
