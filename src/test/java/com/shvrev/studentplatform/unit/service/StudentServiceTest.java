package com.shvrev.studentplatform.unit.service;

import com.shvrev.studentplatform.converter.StudentConverter;
import com.shvrev.studentplatform.entity.db.Student;
import com.shvrev.studentplatform.entity.dto.StudentDTO;
import com.shvrev.studentplatform.mapper.StudentMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class) //looks for Mockito annotations below
class StudentServiceTest {
    @InjectMocks
    private StudentService studentService;
    @Mock
    private StudentMapper studentMapper;
    @Spy
    private StudentConverter studentConverter;

    @BeforeEach
    public void setUp(){
        List<Student> students=new ArrayList<>();
        students.add(new Student(1L, "Alex", "Shvyrev", LocalDate.parse("1987-01-01"), LocalDateTime.now()));
        students.add(new Student(2L, "Alex2", "Shvyrev", LocalDate.parse("2010-01-01"), LocalDateTime.now()));
        students.add(new Student(3L, "Alex3", "Shvyrev", LocalDate.parse("2018-01-01"), LocalDateTime.now()));
        students.add(new Student(4L, "Alex4", "Shvyrev", LocalDate.parse("2012-01-01"), LocalDateTime.now()));
        students.add(new Student(5L, "Alex5", "Shvyrev", LocalDate.parse("1990-01-01"), LocalDateTime.now()));

        Mockito.when(studentMapper.getAllStudents()).thenReturn(students);
    }

    @Test
    void getAdults() {
        List<StudentDTO> result = studentService.getAdults();
        assertEquals(2, result.size());
    }
}