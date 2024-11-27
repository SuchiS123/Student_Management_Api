package com.student.students.StudentDto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;

@Getter
@Setter
public class StudentDto {


    private Long id;

    @NotBlank
    @Size(min=3,max=100,message = "Must Be 3 Characters")
    private String name;


    @Min(value = 3, message = "Age must be at least 3 years")
    @Max(value = 18, message = "Age must not exceed 18 years")
    private Integer age;

    @NotBlank
    @Size(min=3,max=1000,message = "Must Be 3 Characters")
    private String address;

    @NotBlank
    @Size(min=1,max=100,message = "Must Be 1 Characters")
    private String className;

    private Date date;
}
