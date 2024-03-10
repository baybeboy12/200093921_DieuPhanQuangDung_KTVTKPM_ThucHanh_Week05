package com.example.controller;

import com.example.Repositories.StudentRepositories;
import com.example.config.JwtService;
import com.example.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class StudentController {
    @Autowired
    private StudentRepositories studentRepositories;
    @Autowired
    private JwtService jwtService;
    @PostMapping("/create")
    public Student createStudent(@RequestBody Student student) {
        return studentRepositories.save(student);
    }
//    @PostMapping(value = "/login",consumes = "application/json")
//    public Student login(String name,String pass){
//        return studentRepositories.login(name,pass);
//    }
    @PostMapping(value = "/login", consumes = "application/json")
    public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) {
    String name = credentials.get("name");
    String password = credentials.get("password");
        System.out.println(name);
        System.out.println(password);
        Student student = studentRepositories.login(name, password);
        System.out.println(student);
        if (student != null) {
            String token = jwtService.generateToken(student);
            return ResponseEntity.ok(token);
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
}

}
