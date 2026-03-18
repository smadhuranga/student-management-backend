package com.epic.studentmanagementsystemepicdemo.repository.impl;

import com.epic.studentmanagementsystemepicdemo.model.Course;
import com.epic.studentmanagementsystemepicdemo.repository.CourseRepo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class CourseRepository implements CourseRepo {

    private final JdbcTemplate jdbcTemplate;

    public CourseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int saveCourse(Course course) {
        String sql = "INSERT INTO Course (courseName, courseCode, description) VALUES (?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                course.getCourseName(),
                course.getCourseCode(),
                course.getDescription()
        );
    }

    @Override
    public List<Course> getAllCourses() {
        return getAllCourses("id", "desc");
    }

    @Override
    public List<Course> getAllCourses(String sortBy, String sortOrder) {
        Set<String> allowedSortColumns = Set.of("id", "courseName", "courseCode", "description");
        if (sortBy == null || !allowedSortColumns.contains(sortBy)) {
            sortBy = "id";
        }
        if (sortOrder == null ||
                (!sortOrder.equalsIgnoreCase("asc") && !sortOrder.equalsIgnoreCase("desc"))) {
            sortOrder = "desc";
        }
        String dbSortColumn;
        switch (sortBy) {
            case "courseName":
                dbSortColumn = "c.courseName";
                break;
            case "courseCode":
                dbSortColumn = "c.courseCode";
                break;
            case "description":
                dbSortColumn = "c.description";
                break;
            case "id":
            default:
                dbSortColumn = "c.Id";
                break;
        }

        String sql =
                "SELECT c.Id, c.courseName, c.courseCode, c.description, " +
                        "       COUNT(sc.studentId) AS studentCount " +
                        "FROM Course c " +
                        "LEFT JOIN Student_Course sc ON c.Id = sc.courseId " +
                        "GROUP BY c.Id, c.courseName, c.courseCode, c.description " +
                        "ORDER BY " + dbSortColumn + " " + sortOrder;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Course course = new Course();
            course.setId(rs.getInt("Id"));
            course.setCourseName(rs.getString("courseName"));
            course.setCourseCode(rs.getString("courseCode"));
            course.setDescription(rs.getString("description"));
            course.setStudentCount(rs.getInt("studentCount"));
            return course;
        });
    }

    @Override
    public Course findById(int id) {
        String sql =
                "SELECT c.Id, c.courseName, c.courseCode, c.description, " +
                        "       COUNT(sc.studentId) AS studentCount " +
                        "FROM Course c " +
                        "LEFT JOIN Student_Course sc ON c.Id = sc.courseId " +
                        "WHERE c.Id = ? " +
                        "GROUP BY c.Id, c.courseName, c.courseCode, c.description";

        List<Course> courses = jdbcTemplate.query(sql, new Object[]{id}, (rs, rowNum) -> {
            Course course = new Course();
            course.setId(rs.getInt("Id"));
            course.setCourseName(rs.getString("courseName"));
            course.setCourseCode(rs.getString("courseCode"));
            course.setDescription(rs.getString("description"));
            course.setStudentCount(rs.getInt("studentCount"));
            return course;
        });

        return courses.isEmpty() ? null : courses.get(0);
    }

    @Override
    public int updateCourse(Course course) {
        String sql = "UPDATE Course SET courseName = ?, courseCode = ?, description = ? WHERE Id = ?";
        return jdbcTemplate.update(
                sql,
                course.getCourseName(),
                course.getCourseCode(),
                course.getDescription(),
                course.getId()
        );
    }

    @Override
    public int deleteCourse(int id) {
        jdbcTemplate.update("DELETE FROM Student_Course WHERE courseId = ?", id);
        return jdbcTemplate.update("DELETE FROM Course WHERE Id = ?", id);
    }
}