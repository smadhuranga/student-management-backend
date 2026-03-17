package com.epic.studentmanagementsystemepicdemo.service;

import com.epic.studentmanagementsystemepicdemo.model.Course;
import com.epic.studentmanagementsystemepicdemo.repository.CourseRepo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CourseService {
    void createCourse(Course course);

    List<Course> getAllCourses();

    List<Course> getAllCourses(String sortBy, String sortOrder);

    Course getCourseById(int id);

    void updateCourse(int id, Course course);

    void deleteCourse(int id);

}
