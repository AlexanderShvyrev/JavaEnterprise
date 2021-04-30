package com.shvrev.studentplatform.controller;

import com.shvrev.studentplatform.entity.db.Student;
import com.shvrev.studentplatform.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @GetMapping("students")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping("students")
    @ResponseStatus(HttpStatus.CREATED)
    public void createStudent(@RequestBody Student student){
        studentService.addStudent(student);
    }

    @PutMapping("update/student")
    @ResponseStatus(HttpStatus.OK)
    public void updateStudent(@RequestBody Student student){
        studentService.updateStudent(student);
    }

    @DeleteMapping("delete/student/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(@PathVariable Long studentId){
        studentService.deleteStudent(studentId);
    }
}
