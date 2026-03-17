package com.epic.studentmanagementsystemepicdemo.controller;

import com.epic.studentmanagementsystemepicdemo.model.Course;
import com.epic.studentmanagementsystemepicdemo.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public String createCourse(@RequestBody Course course) {
        courseService.createCourse(course);
        return "Course created successfully";
    }

    @GetMapping
    public List<Course> getAllCourses(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String sortOrder) {
        return courseService.getAllCourses(sortBy, sortOrder);
    }

    @GetMapping("/{id}")
    public Course getCourse(@PathVariable int id) {
        return courseService.getCourseById(id);
    }

    @PutMapping("/{id}")
    public String updateCourse(@PathVariable int id, @RequestBody Course course) {
        courseService.updateCourse(id, course);
        return "Course updated successfully";
    }

    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
        return "Course deleted successfully";
    }
}