package com.example.onlineordering.controller;
import com.example.onlineordering.Student;
import com.example.onlineordering.repository.StudentRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins="*",maxAge = 4800,  allowedHeaders = "*",exposedHeaders = {})
@RequestMapping(path="/api/student")
public class StudentController {

//    @Autowired
//    private StudentRepository studentRepository;
//
//    // get all students
//    @GetMapping()
//    public List<Student> getAllStudents()
//    {
//        return studentRepository.findAll();
//    }
//
//    // create student rest API
//    @PostMapping()
//    public Object createStudent(@RequestBody Student student)  {
//        Student newStudent =  studentRepository.save(student);
//        return newStudent;
//    }

    // get student by id rest api
    @GetMapping()
//    @ResponseBody
    public Object findStudentById() {
        return "6";
//        Student student = studentRepository.findById(id).
//                orElseThrow(() -> new ResourceNotFoundException
//                        ("Student not exist with id :" + id));
//        return student;
    }

    // update student rest api
//    @PutMapping("/{id}")
//    public Student updateStudent(@PathVariable Long id,
//                                 @RequestBody Student studentDetails) {
//
//        Student student = studentRepository.findById(id).
//                orElseThrow(() -> new ResourceNotFoundException
//                        ("Student not exist with id :" + id));
//
//        student.setFirstName(studentDetails.getFirstName());
//        student.setLastName(studentDetails.getLastName());
//        student.setEmail(studentDetails.getEmail());
//
//        return studentRepository.save(student);
//    }
//
//    // delete student rest api
//    @DeleteMapping("/{id}")
//    public Map<String, Boolean> deleteStudent
//    (@PathVariable Long id) {
//
//        Student student = studentRepository.findById(id).
//                orElseThrow(() -> new ResourceNotFoundException
//                        ("Student not exist with id :" + id));
//
//
//        studentRepository.delete(student);
//
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return response;
//    }
}