package com.epic.studentmanagementsystemepicdemo.service;

import com.epic.studentmanagementsystemepicdemo.dto.StudentRequestDTO;
import com.epic.studentmanagementsystemepicdemo.exception.DuplicateEmailException;
import com.epic.studentmanagementsystemepicdemo.model.Student;
import com.epic.studentmanagementsystemepicdemo.repository.EnrollmentRepo;
import com.epic.studentmanagementsystemepicdemo.repository.StudentRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


public interface StudentService {
    void createStudent(StudentRequestDTO request);
    void updateStudent(Integer id, StudentRequestDTO request);
    Student findStudentById(int studentId);
    List<Student> findAllStudents();
    List<Student> findAllStudents(String sortBy, String sortOrder); // new
    void deleteStudentById(int studentId);
}