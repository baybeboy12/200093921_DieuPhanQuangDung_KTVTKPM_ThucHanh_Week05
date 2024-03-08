package com.example.controller;

import com.example.Repositories.StudentRepositories;
import com.example.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class StudentController {
    @Autowired
    private StudentRepositories studentRepositories;
    @PostMapping("/create")
    public Student createStudent(@RequestBody Student student) {
        return studentRepositories.save(student);
    }
    @PostMapping(value = "/login",consumes = "application/json")
    public Student login(String tenSV,String matkhau){
        return studentRepositories.login(tenSV,matkhau);
    }



}
