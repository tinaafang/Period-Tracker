package com.example.onlineordering.controller;
import com.example.onlineordering.User;
import com.example.onlineordering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="*",maxAge = 4800,  allowedHeaders = "*",exposedHeaders = {})
@RequestMapping(path="/api/user")
public class UserController {

    @Autowired
    private UserService userService;

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
    @PostMapping(value = "/sign-up")
    public Object createUser(@RequestBody User user)  {

        return  userService.signUpUser(user);
    }

    // get student by id rest api
    @GetMapping(value = "/get")
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