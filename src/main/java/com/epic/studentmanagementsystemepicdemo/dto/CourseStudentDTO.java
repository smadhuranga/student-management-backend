package com.epic.studentmanagementsystemepicdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseStudentDTO {
    private Integer studentId;
    private String studentName;
    private String email;
    private Date enrolledDate;
}
