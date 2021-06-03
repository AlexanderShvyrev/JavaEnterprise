package com.shvrev.studentplatform.controller;

import com.shvrev.studentplatform.entity.dto.StudentDTO;
import com.shvrev.studentplatform.unit.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @GetMapping("students")
    public List<StudentDTO> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping("students")
    @ResponseStatus(HttpStatus.CREATED)
    public void createStudent(@RequestBody @Valid StudentDTO student){
        studentService.addStudent(student);
    }

    @PutMapping("update/student/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateStudent(@RequestBody @Valid StudentDTO student, @PathVariable Long studentId){
        studentService.updateStudent(student, studentId);
    }

    @DeleteMapping("delete/student/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(@PathVariable Long studentId){
        studentService.deleteStudent(studentId);
    }

    @GetMapping("students/adults")
    public List<StudentDTO> getAdults(){
        return studentService.getAdults();
    }
}
