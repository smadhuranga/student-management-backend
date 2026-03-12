/**
 * @author supunmadhuranga
 * @created 2026-02-19
 * @project StudentManagementSystemEpicdemo
 */

package com.epic.studentmanagementsystemepicdemo.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class Student {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private LocalDate enrollmentDate;
}
