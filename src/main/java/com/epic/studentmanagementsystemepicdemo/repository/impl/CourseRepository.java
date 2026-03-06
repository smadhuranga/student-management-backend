package com.epic.studentmanagementsystemepicdemo.repository.impl;

import com.epic.studentmanagementsystemepicdemo.exception.ResourceNotFoundException;
import com.epic.studentmanagementsystemepicdemo.model.Course;
import com.epic.studentmanagementsystemepicdemo.repository.CourseRepo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseRepository implements CourseRepo {

    private final JdbcTemplate jdbcTemplate;
    RowMapper<Course> courseRowMapper = (rs, rowNum) -> {
        Course c = new Course();
        c.setId(rs.getInt("Id"));
        c.setCourseName(rs.getString("courseName"));
        c.setCourseCode(rs.getString("courseCode"));
        c.setDescription(rs.getString("description"));
        return c;
    };

    public CourseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int saveCourse(Course course) {

        String sql = """
                INSERT INTO Course
                (courseName, courseCode, description)
                VALUES (?, ?, ?)
                """;

        return jdbcTemplate.update(sql,
                course.getCourseName(),
                course.getCourseCode(),
                course.getDescription());
    }

    @Override
    public List<Course> getAllCourses() {

        String sql = "SELECT * FROM Course";

        return jdbcTemplate.query(sql, courseRowMapper);
    }

    @Override
    public Course findById(int id) {

        String sql = "SELECT * FROM Course WHERE Id=?";

        try {
            return jdbcTemplate.queryForObject(sql, courseRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Course not found");
        }
    }

    @Override
    public int updateCourse(Course course) {

        String sql = """
                UPDATE Course
                SET courseName=?, courseCode=?, description=?
                WHERE Id=?
                """;

        int rows = jdbcTemplate.update(sql,
                course.getCourseName(),
                course.getCourseCode(),
                course.getDescription(),
                course.getId());

        if (rows == 0) {
            throw new ResourceNotFoundException("Course not found");
        }

        return rows;
    }

    @Override
    public int deleteCourse(int id) {

        String sql = "DELETE FROM Course WHERE Id=?";

        int rows = jdbcTemplate.update(sql, id);

        if (rows == 0) {
            throw new ResourceNotFoundException("Course not found");
        }

        return rows;
    }

}