package com.example.authenticationsystem.service;

import com.example.authenticationsystem.dto.ResetPasswordRequest;
import com.example.authenticationsystem.dto.UserDto;
import com.example.authenticationsystem.entity.Token;
import com.example.authenticationsystem.entity.Role;
import com.example.authenticationsystem.entity.User;
import com.example.authenticationsystem.enums.TokenPurpose;
import com.example.authenticationsystem.exceptions.BadRequestException;
import com.example.authenticationsystem.repository.RoleRepository;
import com.example.authenticationsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmailService emailService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword(),
                    user.isEnabled(),true,true,true,
                    mapRolesToAuthorities(user.getRoles()));
        }else{
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setRole("ROLE_USER");
        return roleRepository.save(role);
    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Collection <Role> roles) {
        Collection < ? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
        return mapRoles;
    }

    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new BadRequestException("User not found");
        }
        return user;
    }



    public String registrationBeforeConfirm(UserDto userDto)  {

        User existingUser = userRepository.findByEmail(userDto.getEmail());

        if (existingUser != null) {
            throw new BadRequestException("An account for that email already exists.");
        }

        User user = new User();

        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByRole("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setEnabled(false);

        userRepository.save(user);


        String tokenStr = tokenService.generateToken();

        Token token = new Token();
        token.setCreatedAt(LocalDateTime.now());
        token.setExpiredAt(LocalDateTime.now().plusMinutes(15));
        token.setToken(tokenStr);
        token.setUser(user);
        token.setPurpose(TokenPurpose.ACCOUNT_ACTIVATION);

        tokenService.saveToken(token);

        sendValidationEmail(userDto.getEmail(),tokenStr);
        return tokenStr;
    }


    public void sendValidationEmail(String email, String token) {
        String link = "http://localhost:8090/register/confirm?token=" + token;
        Context context = new Context();
        context.setVariable("link", link);
        String htmlTemplate = emailService.buildTemplate("AccountValidationEmail", context);
        emailService.sendEmail(email,"Verify Your Account",htmlTemplate);
    }

    public void resendValidationEmail(String email)  {
        List<Token> tokens = tokenService.getTokensByEmail(email);
        if(!tokens.isEmpty()) {
            tokens.forEach(token -> {
                if(TokenPurpose.ACCOUNT_ACTIVATION.equals(token.getPurpose()) && token.getExpiredAt().isAfter(LocalDateTime.now())) {
                    token.setExpiredAt(LocalDateTime.now());
                }
            });
        }

        User user = getUserByEmail(email);
        Token token = new Token();
        token.setCreatedAt(LocalDateTime.now());
        token.setExpiredAt(LocalDateTime.now().plusMinutes(15));
        token.setUser(user);
        token.setPurpose(TokenPurpose.ACCOUNT_ACTIVATION);
        String tokenStr = tokenService.generateToken();
        token.setToken(tokenStr);
        tokenService.saveToken(token);

        sendValidationEmail(email,tokenStr);
    }

    private void enableAppUser(String email) {
        userRepository.enableUser(email);
    }

    public void confirmRegistration(String tokenStr) {
        Token token = tokenService.getToken(tokenStr);

        if (TokenPurpose.ACCOUNT_ACTIVATION.equals(token.getPurpose()) && token.getConfirmedAt() != null) {
            throw new BadRequestException("The account associated with this email is already confirmed");
        }


        if (token.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("token expired");
        }

        tokenService.setConfirmedAt(tokenStr);
        enableAppUser(token.getUser().getEmail());
    }


    public void sendResetPasswordEmail(String email, String token) {
        String link = "http://localhost:8090/reset-password/confirm?token=" + token;
        Context context = new Context();
        context.setVariable("link", link);
        String htmlTemplate = emailService.buildTemplate("PasswordResetEmail", context);
        emailService.sendEmail(email,"Password Reset",htmlTemplate);
    }

    public String sendResetPasswordEmail(String email)  {
        List<Token> tokens = tokenService.getTokensByEmail(email);
        if(!tokens.isEmpty()) {
            tokens.forEach(token -> {
                if(TokenPurpose.PASSWORD_RESET.equals(token.getPurpose()) && token.getExpiredAt().isAfter(LocalDateTime.now())) {
                    token.setExpiredAt(LocalDateTime.now());
                    tokenService.saveToken(token);
                }
            });
        }

        User user = getUserByEmail(email);
        Token token = new Token();
        token.setCreatedAt(LocalDateTime.now());
        token.setExpiredAt(LocalDateTime.now().plusMinutes(15));
        token.setUser(user);
        token.setPurpose(TokenPurpose.PASSWORD_RESET);
        String tokenStr = tokenService.generateToken();
        token.setToken(tokenStr);
        tokenService.saveToken(token);

        sendResetPasswordEmail(email,tokenStr);

        return tokenStr;
    }



    public void resetPasswordConfirm(String tokenStr) {
        Token token = tokenService.getToken(tokenStr);
        if (token.getExpiredAt() == null || token.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("invalid token");
        }
        token.setConfirmedAt(LocalDateTime.now());
        tokenService.saveToken(token);


    }

    public void resetPassword(ResetPasswordRequest resetPasswordRequest) {
        Token token = tokenService.getToken(resetPasswordRequest.getToken());
        if (token == null || token.getConfirmedAt() == null) {
            throw new BadRequestException("invalid token");
        }
        User user = getUserByEmail(resetPasswordRequest.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(resetPasswordRequest.getPassword()));
        userRepository.save(user);

    }






}
