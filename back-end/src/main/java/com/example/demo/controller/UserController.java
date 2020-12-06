package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.List;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getallUsers(NativeWebRequest webRequest)
    {
        String uid=webRequest.getAttribute("user_id", SCOPE_REQUEST).toString();
        List <User> users=userService.AllUsers();
        return users;
    }

    @PostMapping("/phoneregister/{id}")
    public void updatePhone(@PathVariable String id,@RequestBody HashMap<String,String> map)
    {
        String Phone=map.get("phoneNumber");
        User user=userService.updatePhone(id,Phone);
    }
}
