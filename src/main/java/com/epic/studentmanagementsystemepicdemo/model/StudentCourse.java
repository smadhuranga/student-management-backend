package com.epic.studentmanagementsystemepicdemo.model;

import lombok.Data;

import java.time.LocalDate;


@Data
public class StudentCourse {

    private Integer id;
    private Integer studentId;
    private Integer courseId;
    private LocalDate enrollmentDate;

}