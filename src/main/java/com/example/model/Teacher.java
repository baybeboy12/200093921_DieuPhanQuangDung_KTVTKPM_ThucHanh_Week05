package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "teacher")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long maGV;
    private String tenGV;
    @ManyToOne
    @JoinColumn(name = "maSV")
    private Student student;

    public Teacher(long maGV, String tenGV) {
        this.maGV = maGV;
        this.tenGV = tenGV;
    }
}
