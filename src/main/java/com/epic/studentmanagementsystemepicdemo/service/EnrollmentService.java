package com.epic.studentmanagementsystemepicdemo.service;

import com.epic.studentmanagementsystemepicdemo.model.Course;
import com.epic.studentmanagementsystemepicdemo.model.Student;
import com.epic.studentmanagementsystemepicdemo.repository.EnrollmentRepo;
import com.epic.studentmanagementsystemepicdemo.repository.impl.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {
    private final EnrollmentRepo enrollmentRepo;

    public EnrollmentService(EnrollmentRepo enrollmentRepo) {
        this.enrollmentRepo = enrollmentRepo;
    }

    public void enrollStudent(int studentId, int courseId) {
        if (enrollmentRepo.existsEnrollment(studentId, courseId)) {
            throw new RuntimeException("Student already enrolled in this course");
        }
        enrollmentRepo.enrollStudent(studentId, courseId);
    }

    public void removeStudent(int studentId, int courseId) {
        enrollmentRepo.removeEnrollment(studentId, courseId);
    }

    public List<Course> getCoursesByStudent(int studentId){
        return enrollmentRepo.getCoursesByStudent(studentId);
    }
    public  List<Student> getStudentsByCourse(int courseId){
        return enrollmentRepo.getStudentsByCourse(courseId);
    }
}
