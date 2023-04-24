package com.example.onlineordering.controller;

import com.example.onlineordering.User;
import com.example.onlineordering.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/registration")
@CrossOrigin(origins="*",maxAge = 4800,  allowedHeaders = "*",exposedHeaders = {})
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping(path = "/register")
    public String register(@RequestBody User user) {
        return registrationService.register(user);
    }

    @GetMapping(path = "/confirm")
    public void confirm(@RequestParam String token) {
        registrationService.confirmToken(token);
    }


}
