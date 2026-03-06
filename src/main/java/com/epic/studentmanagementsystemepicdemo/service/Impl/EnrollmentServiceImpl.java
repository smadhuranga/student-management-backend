package com.epic.studentmanagementsystemepicdemo.service.Impl;

import com.epic.studentmanagementsystemepicdemo.dto.CourseStudentDTO;
import com.epic.studentmanagementsystemepicdemo.dto.StudentCourseDTO;
import com.epic.studentmanagementsystemepicdemo.model.Course;
import com.epic.studentmanagementsystemepicdemo.model.Student;
import com.epic.studentmanagementsystemepicdemo.repository.EnrollmentRepo;
import com.epic.studentmanagementsystemepicdemo.service.EnrollmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepo enrollmentRepo;

    public EnrollmentServiceImpl(EnrollmentRepo enrollmentRepo) {
        this.enrollmentRepo = enrollmentRepo;
    }

    @Override
    public void enrollStudent(int studentId, int courseId) {
        if (!enrollmentRepo.existsEnrollment(studentId, courseId)) {
            enrollmentRepo.enrollStudent(studentId, courseId);
        }
    }

    @Override
    public void removeStudent(int studentId, int courseId) {
        enrollmentRepo.removeEnrollment(studentId, courseId);
    }

    @Override
    public List<Course> getCoursesByStudent(int studentId) {
        return enrollmentRepo.getCoursesByStudent(studentId);
    }

    @Override
    public List<Student> getStudentsByCourse(int courseId) {
        return enrollmentRepo.getStudentsByCourse(courseId);
    }

    @Override
    public List<StudentCourseDTO> getStudentCourseDetails(int studentId) {
        return enrollmentRepo.getStudentCourseDetails(studentId);
    }

    @Override
    public List<CourseStudentDTO> getCourseStudentDetails(int courseId) {
        return enrollmentRepo.getCourseStudentDetails(courseId);
    }
}