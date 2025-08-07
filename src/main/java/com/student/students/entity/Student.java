package com.student.students.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="StudentDb")
public class Student {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Long id;

    @Column(name="name",length=100,nullable=false)
    private String name;

    @Column(name="age",nullable=false)
    private Integer age;

    @Column(name="address",nullable=false)
    private String address;

    @Column(name="class_name",nullable=false)
    private String className;

    @Column(name ="Blood Group",nullable = false)
    private String bloodGroup;

    @Column(name = "Parents" , nullable = false)
    private String Parents;


}
