package com.epic.studentmanagementsystemepicdemo.controller;

import com.epic.studentmanagementsystemepicdemo.dto.CourseStudentDTO;
import com.epic.studentmanagementsystemepicdemo.dto.StudentCourseDTO;
import com.epic.studentmanagementsystemepicdemo.model.Course;
import com.epic.studentmanagementsystemepicdemo.model.Student;
import com.epic.studentmanagementsystemepicdemo.service.EnrollmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/enrollment")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/enroll")
    public String enroll(@RequestParam int studentId, @RequestParam int courseId) {
        enrollmentService.enrollStudent(studentId, courseId);
        return "Student enrolled successfully";
    }

    @DeleteMapping("/remove")
    public String remove(@RequestParam int studentId, @RequestParam int courseId) {
        enrollmentService.removeStudent(studentId, courseId);
        return "Enrollment removed";
    }

    @GetMapping("/student/{studentId}")
    public List<Course> getCourses(@PathVariable int studentId) {
        return enrollmentService.getCoursesByStudent(studentId);
    }

    @GetMapping("/course/{courseId}")
    public List<Student> getStudents(@PathVariable int courseId) {
        return enrollmentService.getStudentsByCourse(courseId);
    }


    @GetMapping("/student/{studentId}/details")
    public List<StudentCourseDTO> getStudentCourseDetails(@PathVariable int studentId) {
        return enrollmentService.getStudentCourseDetails(studentId);
    }


    @GetMapping("/course/{courseId}/details")
    public List<CourseStudentDTO> getCourseStudentDetails(@PathVariable int courseId) {
        return enrollmentService.getCourseStudentDetails(courseId);
    }
}

