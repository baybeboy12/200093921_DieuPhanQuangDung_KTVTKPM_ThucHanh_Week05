package com.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long maSV;
    private String tenSV;
    private String matkhau;
    //    @OneToMany(mappedBy = "student")
//    private List<Teacher> teachers;
    @Override
    public String toString() {
        return "Student{" +
                "maSV=" + maSV +
                ", tenSV='" + tenSV + '\'' +
                '}';
    }
}
