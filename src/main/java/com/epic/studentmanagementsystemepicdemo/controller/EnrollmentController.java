package com.epic.studentmanagementsystemepicdemo.controller;

import com.epic.studentmanagementsystemepicdemo.dto.CourseStudentDTO;
import com.epic.studentmanagementsystemepicdemo.dto.EnrollmentRequestDTO;
import com.epic.studentmanagementsystemepicdemo.dto.StudentCourseDTO;
import com.epic.studentmanagementsystemepicdemo.model.Course;
import com.epic.studentmanagementsystemepicdemo.model.Student;
import com.epic.studentmanagementsystemepicdemo.service.EnrollmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/enrollments")
    public String enroll(@RequestBody EnrollmentRequestDTO request) {
        enrollmentService.enrollStudent(request.getStudentId(), request.getCourseId());
        return "Student enrolled successfully";
    }

    @DeleteMapping("/enrollments")
    public String remove(@RequestBody EnrollmentRequestDTO request) {
        enrollmentService.removeStudent(request.getStudentId(), request.getCourseId());
        return "Enrollment removed";
    }

    @GetMapping("/students/{studentId}/courses")
    public List<Course> getCoursesByStudent(@PathVariable int studentId) {
        return enrollmentService.getCoursesByStudent(studentId);
    }

    @GetMapping("/courses/{courseId}/students/basic")
    public List<Student> getStudentsByCourse(@PathVariable int courseId) {
        return enrollmentService.getStudentsByCourse(courseId);
    }

    @GetMapping("/students/{studentId}/courses/details")
    public List<StudentCourseDTO> getStudentCourseDetails(@PathVariable int studentId) {
        return enrollmentService.getStudentCourseDetails(studentId);
    }

    @GetMapping("/courses/{courseId}/students")
    public List<CourseStudentDTO> getCourseStudentDetails(@PathVariable int courseId) {
        return enrollmentService.getCourseStudentDetails(courseId);
    }
}