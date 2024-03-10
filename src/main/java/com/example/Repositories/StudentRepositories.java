package com.example.Repositories;

import com.example.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepositories extends JpaRepository<Student,Long> {
    @Query("select s from Student s where s.name = ?1 and s.password = ?2")
    Student login(String ten, String pass);


}
