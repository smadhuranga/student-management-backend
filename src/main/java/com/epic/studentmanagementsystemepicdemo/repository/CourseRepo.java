package com.epic.studentmanagementsystemepicdemo.repository;

import com.epic.studentmanagementsystemepicdemo.model.Course;

import java.util.List;

public interface CourseRepo {

    int saveCourse(Course course);

    List<Course> getAllCourses();

    Course findById(int id);

    int updateCourse(Course course);

    int deleteCourse(int id);

}