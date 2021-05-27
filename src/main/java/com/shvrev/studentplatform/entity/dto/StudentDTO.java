package com.shvrev.studentplatform.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class StudentDTO {
    @NotBlank
    @Size(min=1, max=50)
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    private LocalDate birthDate;
}
