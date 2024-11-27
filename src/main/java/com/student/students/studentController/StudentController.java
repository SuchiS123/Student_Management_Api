package com.student.students.studentController;

import com.student.students.ResourceNotFound.ResourceNotFound;
import com.student.students.StudentDto.StudentDto;
import com.student.students.StudentService.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService)
    {
        this.studentService = studentService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@Valid @RequestBody StudentDto dto, BindingResult result)
    {

        if(result.hasErrors())
        {
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.BAD_REQUEST);
        }

        StudentDto studentDto=studentService.addStudent(dto);
        studentDto.setDate(new Date());
        return new ResponseEntity<>(studentDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/studentId/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long studentId)
    {
        studentService.deleteStudentById(studentId);
        return new ResponseEntity<>("Student with id: "+studentId+" deleted successfully", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<StudentDto> updateSytudent(@RequestParam Long studentId,@RequestBody StudentDto stdto)
    {
        StudentDto studentDto=studentService.updateStudent(studentId,stdto);
        studentDto.setDate(new Date());
        


        return new ResponseEntity<>(studentDto,HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getStudent(
            @RequestParam(name="pageSize",required = false,defaultValue ="2")int pageSize,
            @RequestParam(name="pageNo",required=false,defaultValue ="0")int pageNo,
            @RequestParam(name="sortBy",required = false, defaultValue ="name")String sortBy,
            @RequestParam(name="sortDir",required = false, defaultValue ="asc")String sortDir
    )
    {
        List<StudentDto> studentDto1=studentService.getStudent(pageSize, pageNo,sortBy,sortDir);
        studentDto1.forEach(s -> s.setDate(new Date()));
        return new ResponseEntity<>(studentDto1,HttpStatus.OK);
    }

    @GetMapping ("/StudentId/{studentId}")
    public ResponseEntity<StudentDto> getStudentByID(@PathVariable Long studentId)
    {
       StudentDto studentDto= studentService.getStudentById(studentId);
       studentDto.setDate(new Date());
       return  new ResponseEntity<>(studentDto,HttpStatus.OK);
    }

}
