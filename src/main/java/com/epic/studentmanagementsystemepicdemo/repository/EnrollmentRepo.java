package com.epic.studentmanagementsystemepicdemo.repository;

import com.epic.studentmanagementsystemepicdemo.dto.CourseStudentDTO;
import com.epic.studentmanagementsystemepicdemo.dto.StudentCourseDTO;
import com.epic.studentmanagementsystemepicdemo.model.Course;
import com.epic.studentmanagementsystemepicdemo.model.Student;

import java.util.List;

public interface EnrollmentRepo {

    void enrollStudent(int studentId, int courseId);

    void removeEnrollment(int studentId, int courseId);

    List<Course> getCoursesByStudent(int studentId);

    List<Student> getStudentsByCourse(int courseId);

    List<StudentCourseDTO> getStudentCourseDetails(int studentId);

    List<CourseStudentDTO> getCourseStudentDetails(int courseId);

    void removeEnrollmentsByStudent(int studentId);

    boolean existsEnrollment(int studentId, int courseId);

}