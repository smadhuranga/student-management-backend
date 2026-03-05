package com.epic.studentmanagementsystemepicdemo.service;

import com.epic.studentmanagementsystemepicdemo.model.Course;
import com.epic.studentmanagementsystemepicdemo.repository.CourseRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepo courseRepo;

    public CourseService(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    public void createCourse(Course course) {
        courseRepo.saveCourse(course);
    }

    public List<Course> getAllCourses() {
        return courseRepo.getAllCourses();
    }

    public Course getCourseById(int id) {
        return courseRepo.findById(id);
    }

    public void updateCourse(int id, Course course) {
        course.setId(id);
        courseRepo.updateCourse(course);
    }

    public void deleteCourse(int id) {
        courseRepo.deleteCourse(id);
    }

}
