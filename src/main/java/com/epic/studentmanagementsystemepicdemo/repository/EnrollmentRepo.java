package com.epic.studentmanagementsystemepicdemo.repository;

import com.epic.studentmanagementsystemepicdemo.model.Course;
import com.epic.studentmanagementsystemepicdemo.model.Student;

import java.util.List;

public interface EnrollmentRepo {

    void enrollStudent(int studentId, int courseId);

    void removeEnrollment(int studentId, int courseId);

    List<Course> getCoursesByStudent(int studentId);

    List<Student> getStudentsByCourse(int courseId);

    boolean existsEnrollment(int studentId, int courseId);

    void removeEnrollmentsByStudent(int studentId);

}