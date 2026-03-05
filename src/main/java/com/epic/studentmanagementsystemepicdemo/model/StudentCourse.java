package com.epic.studentmanagementsystemepicdemo.model;

import lombok.Data;

import java.util.Date;

@Data
public class StudentCourse {

    private Integer id;
    private Integer studentId;
    private Integer courseId;
    private Date enrollmentDate;

}