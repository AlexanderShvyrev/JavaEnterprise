package com.shvrev.studentplatform.service;

import com.shvrev.studentplatform.entity.db.Student;
import com.shvrev.studentplatform.mapper.StudentMapper;
import com.shvrev.studentplatform.mq.JMSProducer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentService {
    private final StudentMapper studentMapper;

    private final JMSProducer jmsProducer;

    public List<Student> getAllStudents(){
        return studentMapper.getAllStudents();
    }


    public void addStudent(Student student) {
        student.setCreatedAt(LocalDateTime.now());
//        if(!checkIfExists(student.getStudentId())){
            studentMapper.addStudent(student);
            jmsProducer.sendMessage(student);
//        }
    }

    public void updateStudent(Student student) {
        if(checkIfExists(student.getStudentId())){
            studentMapper.updateStudent(student);
        }
    }

    public void deleteStudent(Long studentId) {
        if(checkIfExists(studentId)){
            studentMapper.deleteStudent(studentId);
        }
    }

//    private Boolean checkIfStudentExists(Long studentId){
//        Student pulledStudent = studentMapper.getStudentById(studentId);
//        if(pulledStudent==null) {
//            System.out.println("Bad request. Not found");
//            return false;
//        }
//        return true;
//    }
//
//    private Boolean checkIfExists(Student student){
//        List<Student> students = getAllStudents();
//        for (Student student1 : students) {
//            if(student.getFirstName().equals(student1.getFirstName())) {
//                return true;
//            }
//        }
//        return false;
//    }

    private Boolean checkIfExists(@NonNull Long studentId){
//        if(studentId==null) {
//            System.out.println("Bad request. Not found");
//            return false;
//        }
        List<Student> students = getAllStudents();
//            for (Student student1 : students) {
//                if(studentId.equals(student1.getStudentId())) {
//                    return true;
//                }
//            }
        return students.stream().anyMatch(student -> student.getStudentId().equals(studentId));
    }
}
