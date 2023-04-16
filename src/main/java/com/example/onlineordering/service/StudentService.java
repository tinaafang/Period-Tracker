package com.example.onlineordering.service;

import com.example.onlineordering.Student;
import com.example.onlineordering.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student create(Student student) {
        Student persistStudent = studentRepository.save(student);
        return persistStudent;
    }
}
