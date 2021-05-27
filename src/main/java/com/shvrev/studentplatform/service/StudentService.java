package com.shvrev.studentplatform.service;

import com.shvrev.studentplatform.converter.StudentConverter;
import com.shvrev.studentplatform.entity.db.Student;
import com.shvrev.studentplatform.entity.dto.StudentDTO;
import com.shvrev.studentplatform.mapper.StudentMapper;
import com.shvrev.studentplatform.mq.JMSProducer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentService {
    private final StudentMapper studentMapper;
    private final StudentConverter studentConverter;
    private final JMSProducer jmsProducer;

    public List<StudentDTO> getAllStudents(){
        return studentConverter.convertedStudentToDTO(studentMapper.getAllStudents());
    }

    public void addStudent(StudentDTO student) {
        Student newStudent = studentConverter.convertedStudent(student);
        newStudent.setCreatedAt(LocalDateTime.now());
       Long createdStudent =  studentMapper.addStudent(newStudent);
        newStudent.setStudentId(createdStudent);
        jmsProducer.sendMessage(newStudent);
    }

    public void updateStudent(StudentDTO student, Long studentId) {
        if(checkIfExists(studentId)){
            Student newStudent = studentConverter.convertedStudent(student);
            newStudent.setStudentId(studentId);
            studentMapper.updateStudent(newStudent);
        }
    }

    public void deleteStudent(Long studentId) {
        if(checkIfExists(studentId)){
            studentMapper.deleteStudent(studentId);
        }
    }

    private Boolean checkIfExists(@NonNull Long studentId){
        List<Student> students = studentMapper.getAllStudents();
        return students.stream().anyMatch(student -> student.getStudentId().equals(studentId));
    }

    public List<StudentDTO> getAdults(){
        List<Student> students = studentMapper.getAllStudents();
        List<StudentDTO> adults = new ArrayList<>();
        for (Student student : students) {
            Long age = ChronoUnit.YEARS.between(student.getBirthDate(), LocalDate.now());
            if(age>=18){
                adults.add(studentConverter.convertedStudentDTO(student));
            }
        }
        return adults;
    }
}
