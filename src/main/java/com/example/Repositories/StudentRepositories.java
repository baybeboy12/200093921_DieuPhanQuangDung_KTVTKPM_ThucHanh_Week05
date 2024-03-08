package com.example.Repositories;

import com.example.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepositories extends JpaRepository<Student,Long> {
    @Query("select s from Student s where s.tenSV=: tensv and s.matkhau=: matkhau")
    Student login(String tensv,String matkhau);
}
