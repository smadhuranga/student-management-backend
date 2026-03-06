package com.epic.studentmanagementsystemepicdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentCourseDTO {
    private Integer courseId;
    private String courseName;
    private String courseCode;
    private String description;
    private Date enrolledDate;
}
