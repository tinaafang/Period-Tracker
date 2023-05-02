package com.example.onlineordering.controller;

import com.example.onlineordering.entity.User;
import com.example.onlineordering.repository.UserRepository;
import com.example.onlineordering.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
//@RestController()
//@RequestMapping(path="/user")
//@CrossOrigin(origins="*",maxAge = 4800,  allowedHeaders = "*",exposedHeaders = {})
public class LogInController {
    @Autowired
    private UserService userService;

    @GetMapping(value="/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(Model model,@ModelAttribute("user") @Valid User user) {
        model.addAttribute("user", user);
        String token = null;
        try {
            token = userService.register(user);;
        } catch (IllegalStateException e) {
            model.addAttribute("message", "An account for that email already exists.");
            return "register";
        }
        try {
            userService.sendValidationEmail(user.getEmail(), token);
        } catch (Exception e) {
            model.addAttribute("message", "Something went wrong when sending validation email.");
        }

        return "login";
    }

    @GetMapping(path = "/user/confirm")
    public String confirm(@RequestParam String token) {

        try {
            userService.confirmRegistration(token);
        } catch (Exception e) {
            return "redirect:/badUser";
        }
        return "redirect:/login" ;
    }

    @GetMapping(path = "/user/logout")
    public String logout() {
        return "redirect:/logout" ;
    }
}
