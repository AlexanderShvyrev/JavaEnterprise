package com.shvrev.studentplatform.converter;

import com.shvrev.studentplatform.entity.db.Student;
import com.shvrev.studentplatform.entity.dto.StudentDTO;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper
public interface StudentConverter {
    List<StudentDTO> convertedStudentToDTO(List<Student> students);
    Student convertedStudent(StudentDTO student);
    StudentDTO convertedStudentDTO(Student student);
}
