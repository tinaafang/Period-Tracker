package com.example.onlineordering.service;

import com.example.onlineordering.User;
import com.example.onlineordering.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Object create(User student) {
        User persistStudent = userRepository.save(student);
        return persistStudent;
    }
}
