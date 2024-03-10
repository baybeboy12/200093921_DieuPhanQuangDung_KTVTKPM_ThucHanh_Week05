package com.example;

import com.example.Repositories.StudentRepositories;
import com.example.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class StudentServiceApplication {
    @Autowired
    public StudentRepositories studentRepositories;

    public static void main(String[] args) {
        SpringApplication.run(StudentServiceApplication.class, args);
    }

//        @Bean
        CommandLineRunner commandLineRunner() {
            return args -> {
                Student s = studentRepositories.login("dung","123");
                System.out.printf(s.toString());
            };
        }
}
