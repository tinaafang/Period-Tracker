package com.example.recipewebsite.controller;


import com.example.recipewebsite.dto.AuthResponseDto;
import com.example.recipewebsite.dto.UserDto;
import com.example.recipewebsite.security.JwtUtil;
import com.example.recipewebsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins="*",maxAge = 4800,  allowedHeaders = "*",exposedHeaders = {})
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenProvider;



//    @GetMapping("index")
//    public String home(){
//        return "index";
//    }
//
//    @GetMapping("/login")
//    public String loginForm() {
//        return "login";
//    }
//
//    // handler method to handle user registration request
//    @GetMapping("register")
//    public String showRegistrationForm(Model model){
//        UserDto user = new UserDto();
//        model.addAttribute("user", user);
//        return "register";
//    }


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
    public UserDto registration(@RequestBody UserDto userDto){
        String token = userService.register(userDto);
        userService.sendValidationEmail(userDto.getEmail(), token);
        return userDto;
    }

//    @GetMapping("/users")
//    public String listRegisteredUsers(Model model){
//        List<UserDto> users = userService.findAllUsers();
//        model.addAttribute("users", users);
//        return "users";
//    }

    @GetMapping(path = "/register/confirm")
    public void confirm(@RequestParam String token) {
        userService.confirmRegistration(token);
    }

    @GetMapping(path = "/api/test")
    public UserDto test() {
        UserDto testUser = new UserDto();
        testUser.setUserName("worked!");
        return testUser;
    }

}
