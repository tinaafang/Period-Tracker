package com.example.recipewebsite.controller;

import com.example.recipewebsite.entity.User;
//import com.example.recipewebsite.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RestController()
//@RequestMapping(path="/user")
@CrossOrigin(origins="*",maxAge = 4800,  allowedHeaders = "*",exposedHeaders = {})
public class LogInController {
//    @Autowired
//    private UserService userService;
//
//    @GetMapping(value="/login")
//    public String login() {
//        return "login";
//    }
//
//    @GetMapping("/register")
//    public String registerForm(Model model) {
//        model.addAttribute("user", new User());
//        return "register";
//    }
//
//    @PostMapping("/register/save")
//    public String register(@Valid @ModelAttribute("user") User user,Model model) {
//        model.addAttribute("user", user);
//        String token = null;
//        try {
//            token = userService.register(user);;
//        } catch (IllegalStateException e) {
//            model.addAttribute("message", "An account for that email already exists.");
//            return "redirect:/register";
//        }
//        try {
//            userService.sendValidationEmail(user.getEmail(), token);
//        } catch (Exception e) {
//            model.addAttribute("message", "Something went wrong when sending validation email.");
//            return "redirect:/register";
//        }
//
//        return "redirect:/register";
//    }
//
//    @GetMapping(path = "/user/confirm")
//    public String confirm(@RequestParam String token) {
//
//        try {
//            userService.confirmRegistration(token);
//        } catch (Exception e) {
//            return "redirect:/badUser";
//        }
//        return "redirect:/login" ;
//    }
//
//    @GetMapping(path = "/user/logout")
//    public String logout() {
//        return "redirect:/logout" ;
//    }
}
