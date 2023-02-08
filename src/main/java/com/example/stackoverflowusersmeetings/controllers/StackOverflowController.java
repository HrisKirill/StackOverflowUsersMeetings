package com.example.stackoverflowusersmeetings.controllers;

import com.example.stackoverflowusersmeetings.service.UserService;
import edu.princeton.cs.introcs.StdOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StackOverflowController {

    private final UserService userService;

    @Autowired
    public StackOverflowController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("api/stack-overflow/users")
     void getUsers(){
        StdOut.println(userService.findAll());
    }
}
