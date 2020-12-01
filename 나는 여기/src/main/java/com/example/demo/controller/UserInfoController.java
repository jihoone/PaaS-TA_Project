package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.KakaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Optional;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

@Controller
public class UserInfoController extends KakaoService {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/createForm")
    public String createForm(){
        return "createForm";
    }

    @PostMapping("/user/info")
    public String createForm(NativeWebRequest webRequest, HttpServletRequest result, HttpSession session){

        String phoneNumber = result.getParameter("phoneNumber");
//        String uId = session.getAttribute("id").toString();

        User user =(User)webRequest.getAttribute("user", SCOPE_REQUEST);
        System.out.println("user id : " + user.getId());
        System.out.println("phoneNumber : " + phoneNumber);

        userRepository.updatePhoneNumber(phoneNumber,user.getId());

        return "kakaocallback";
    }

//    public String readForm(HttpSession session){
//
//    }

//    @PostMapping("/user/info")
//    public String create(){
//        return "redirect:/";
//    }


}
