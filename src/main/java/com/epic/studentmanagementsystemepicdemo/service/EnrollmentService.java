package com.epic.studentmanagementsystemepicdemo.service;

import com.epic.studentmanagementsystemepicdemo.dto.CourseStudentDTO;
import com.epic.studentmanagementsystemepicdemo.dto.StudentCourseDTO;
import com.epic.studentmanagementsystemepicdemo.model.Course;
import com.epic.studentmanagementsystemepicdemo.model.Student;
import com.epic.studentmanagementsystemepicdemo.repository.EnrollmentRepo;
import com.epic.studentmanagementsystemepicdemo.repository.impl.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EnrollmentService {
    void enrollStudent(int studentId, int courseId);
    void removeStudent(int studentId, int courseId);

    List<Course> getCoursesByStudent(int studentId);
    List<Student> getStudentsByCourse(int courseId);

    List<StudentCourseDTO> getStudentCourseDetails(int studentId);
    List<CourseStudentDTO> getCourseStudentDetails(int courseId);
}
