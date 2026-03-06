package com.epic.studentmanagementsystemepicdemo.service.Impl;

import com.epic.studentmanagementsystemepicdemo.model.Course;
import com.epic.studentmanagementsystemepicdemo.repository.CourseRepo;
import com.epic.studentmanagementsystemepicdemo.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepo courseRepo;

    public CourseServiceImpl(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    @Override
    public void createCourse(Course course) {
        courseRepo.saveCourse(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepo.getAllCourses();
    }

    @Override
    public Course getCourseById(int id) {
        return courseRepo.findById(id);
    }

    @Override
    public void updateCourse(int id, Course course) {
        course.setId(id);
        courseRepo.updateCourse(course);
    }

    @Override
    public void deleteCourse(int id) {
        courseRepo.deleteCourse(id);
    }
}