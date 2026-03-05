package com.epic.studentmanagementsystemepicdemo.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class StudentRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private LocalDate enrollmentDate;
    private List<Integer> courseIds;
}