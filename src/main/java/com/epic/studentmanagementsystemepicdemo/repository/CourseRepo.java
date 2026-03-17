package com.epic.studentmanagementsystemepicdemo.repository;

import com.epic.studentmanagementsystemepicdemo.model.Course;

import java.util.List;

public interface CourseRepo {

    int saveCourse(Course course);

    List<Course> getAllCourses();

    List<Course> getAllCourses(String sortBy, String sortOrder);

    Course findById(int id);

    int updateCourse(Course course);

    int deleteCourse(int id);



}