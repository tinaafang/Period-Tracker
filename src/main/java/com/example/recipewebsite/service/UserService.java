package com.example.recipewebsite.service;

import com.example.recipewebsite.dto.UserDto;
import com.example.recipewebsite.entity.ConfirmationToken;
import com.example.recipewebsite.entity.Role;
import com.example.recipewebsite.entity.User;
import com.example.recipewebsite.repository.RoleRepository;
import com.example.recipewebsite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
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
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private EmailService emailService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword(),
                    mapRolesToAuthorities(user.getRoles()));
        }else{
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Collection <Role> roles) {
        Collection < ? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
        return mapRoles;
    }

    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> convertEntityToDto(user))
                .collect(Collectors.toList());
    }

    private UserDto convertEntityToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setRole("ROLE_ADMIN");
        return roleRepository.save(role);
    }


    public String register(UserDto userDto) {

        User existingUser = userRepository.findByEmail(userDto.getEmail());

        if (existingUser != null) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException("An account for that email already exists.");
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

        userRepository.save(user);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setCreatedAt(LocalDateTime.now());
        confirmationToken.setExpiredAt(LocalDateTime.now().plusMinutes(15));
        confirmationToken.setToken(token);
        confirmationToken.setUser(user);

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);
        //        boolean isValidEmail = emailValidator.
//                test(request.getEmail());
//
//        if (!isValidEmail) {
//            throw new IllegalStateException("email not valid");
//        }
        return token;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void sendValidationEmail(String email, String token) {
        String link = "http://localhost:8090/register/confirm?token=" + token;

        emailService.sendEmail("tinafang114@gmail.com","surprise!","<p>Hi there :D,<p><br><p>Click the link below to activate your account:<p><br><a href="+link+">activate</a><br><p>link will expire in 15 minutes");

    }


    public void confirmRegistration(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiredAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        enableAppUser(confirmationToken.getUser().getEmail());
    }

    private int enableAppUser(String email) {
        return userRepository.enableUser(email);
    }

}
