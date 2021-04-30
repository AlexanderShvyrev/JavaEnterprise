package com.shvrev.studentplatform.entity.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Long studentId;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private LocalDateTime createdAt;
}
