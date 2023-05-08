package com.example.authenticationsystem.controller;


import com.example.authenticationsystem.dto.AuthResponseDto;
import com.example.authenticationsystem.dto.ResetPasswordRequest;
import com.example.authenticationsystem.dto.UserDto;
import com.example.authenticationsystem.security.JwtUtil;
import com.example.authenticationsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@CrossOrigin(origins="*",maxAge = 4800,  allowedHeaders = "*",exposedHeaders = {})
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserDto userDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDto.getEmail(),
                        userDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponseDto(jwt));
    }


    @PostMapping("/register")
    public LinkedHashMap<String,String>  registrationBeforeConfirm(@RequestBody UserDto userDto){
        String token = userService.registrationBeforeConfirm(userDto);
        LinkedHashMap<String,String> tokenResponse = new LinkedHashMap<>();
        tokenResponse.put("token",token);
        return tokenResponse;
    }


    @GetMapping(path = "/register/confirm")
    public void confirm(@RequestParam String token) {
        userService.confirmRegistration(token);
    }

    @GetMapping(path = "/register/resend")
    public void resendValidationEmail(@RequestParam String email) {
        userService.resendValidationEmail(email);
    }

    @GetMapping(path = "/reset-password")
    public LinkedHashMap<String,String> ResetPasswordBeforeConfirm(@RequestParam String email) {
        String token = userService.sendResetPasswordEmail(email);
        LinkedHashMap<String,String> tokenResponse = new LinkedHashMap<>();
        tokenResponse.put("token",token);
        return tokenResponse;
    }

    @GetMapping(path = "/reset-password/confirm")
    public void resetPasswordConfirm(@RequestParam String token) {
        userService.resetPasswordConfirm(token);
    }

    @PostMapping(path = "/reset-password/reset")
    public void resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        userService.resetPassword(resetPasswordRequest);
    }



}
