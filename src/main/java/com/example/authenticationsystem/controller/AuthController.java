package com.example.authenticationsystem.controller;


import com.example.authenticationsystem.dto.LoginResponse;
import com.example.authenticationsystem.dto.ResetPasswordRequest;
import com.example.authenticationsystem.dto.UserDto;
import com.example.authenticationsystem.entity.User;
import com.example.authenticationsystem.security.JwtUtil;
import com.example.authenticationsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;


    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private JwtUtil jwtTokenProvider;

    @GetMapping(path = "/{token}")
    public User getUserByToken(@PathVariable String token) {
        return userService.getUserByEmail(jwtTokenProvider.getUsername(token));
    }


    @PostMapping("/login")
    public LoginResponse login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );
        User acutalUser = userService.getUserByEmail(user.getEmail());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.createToken(authentication);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwt);
        loginResponse.setUser(acutalUser);
        return loginResponse;
    }


    @PostMapping("/register")
    public void  registrationBeforeConfirm(@RequestBody UserDto userDto){
        userService.registrationBeforeConfirm(userDto);
    }


    @GetMapping(path = "/register/confirm")
    public void confirm(@RequestParam String token) {
        userService.confirmRegistration(token);
    }

//    @GetMapping(path = "/register/resend")
//    public void resendValidationEmail(@RequestParam String email) {
//        userService.resendValidationEmail(email);
//    }

    @GetMapping(path = "/forgot-password")
    public void forgotPassword(@RequestParam String email) {
        userService.sendResetPasswordEmail(email);
    }

    @GetMapping(path = "/forgot-password/confirm")
    public void resetPasswordConfirm(@RequestParam String token) {
        userService.resetPasswordConfirm(token);
    }

    @PostMapping(path = "/reset-password")
    public void resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        userService.resetPassword(resetPasswordRequest);
    }
}
