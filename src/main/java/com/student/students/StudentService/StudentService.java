package com.student.students.StudentService;

import com.student.students.ResourceNotFound.ResourceNotFound;
import com.student.students.StudentDto.StudentDto;
import com.student.students.StudentRepository.StudentRepository;
import com.student.students.entity.Student;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class StudentService {

    private ModelMapper modelMapper;
    private StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository,ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    public StudentDto addStudent(StudentDto dto) {

        Student student=mapToEntity(dto);
        Student student1=studentRepository.save(student);
        StudentDto studentDto=mapToDto(student1);
        return studentDto;
    }

    public Student mapToEntity(StudentDto dto)
    {
        Student student= modelMapper.map(dto,Student.class);
        return student;
    }

    public StudentDto mapToDto(Student student)
    {
        StudentDto studentDto= modelMapper.map(student,StudentDto.class);
        return studentDto;
    }

    public void deleteStudentById(Long studentId) {
        studentRepository.deleteById(studentId);

    }

    public StudentDto updateStudent(Long studentId, StudentDto stdto) {

       Student student= mapToEntity(stdto);
       Optional<Student> student1= studentRepository.findById(studentId);
      Student student2= student1.get();
        student2.setId(studentId);
        Student saveStudent = studentRepository.save(student2);

      StudentDto studentDto=mapToDto(saveStudent);
      return studentDto;



    }

    public List<StudentDto> getStudent(int pageSize, int pageNo, String sortBy, String sortDir) {

        Sort sort= sortDir.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending():Sort.by(sortDir).descending();
        Pageable page= PageRequest.of(pageNo,pageSize,sort);


        Page<Student> students=studentRepository.findAll(page);
        List<Student> student1=students.getContent();
        List<StudentDto> studentDtoListList=student1.stream().map(s->mapToDto(s)).collect(Collectors.toList());
        return studentDtoListList;
    }


    public StudentDto getStudentById(Long studentId) {

        Optional<Student> student=Optional.ofNullable(studentRepository.findById(studentId).orElseThrow
                (()->new ResourceNotFound("Student Details Not Found for id = "+ studentId)));
        Student student1=student.get();
        StudentDto studentDto=mapToDto(student1);
        return studentDto;
    }



}
