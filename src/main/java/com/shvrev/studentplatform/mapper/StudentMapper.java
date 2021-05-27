package com.shvrev.studentplatform.mapper;


import com.shvrev.studentplatform.entity.db.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Select("SELECT * FROM students")
    List<Student> getAllStudents();

    @Select("INSERT INTO students (first_name, last_name, birth_date, created_at) VALUES (#{firstName}, #{lastName}, #{birthDate}, #{createdAt}) returning student_id")
    Long addStudent(Student student);

    @Select("SELECT * FROM students WHERE student_id=#{studentId}")
    Student getStudentById(Long studentId);

    @Update("UPDATE students SET first_name=#{firstName}, last_name=#{lastName}, birth_date=#{birthDate} WHERE student_id=#{studentId}")
    void updateStudent(Student student);

    @Delete("DELETE FROM students WHERE student_id=#{studentId}")
    void deleteStudent(Long studentId);
}
