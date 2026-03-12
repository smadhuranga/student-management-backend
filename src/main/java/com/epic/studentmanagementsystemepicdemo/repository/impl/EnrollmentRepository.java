package com.epic.studentmanagementsystemepicdemo.repository.impl;

import com.epic.studentmanagementsystemepicdemo.dto.CourseStudentDTO;
import com.epic.studentmanagementsystemepicdemo.dto.StudentCourseDTO;
import com.epic.studentmanagementsystemepicdemo.model.Course;
import com.epic.studentmanagementsystemepicdemo.model.Student;
import com.epic.studentmanagementsystemepicdemo.repository.EnrollmentRepo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EnrollmentRepository implements EnrollmentRepo {
    private final JdbcTemplate jdbcTemplate;

    public EnrollmentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void enrollStudent(int studentId, int courseId) {
        String sql = """
            INSERT INTO Student_Course
            (studentId, courseId, enrollmentDate)
            VALUES (?, ?, CURDATE())
            """;
        jdbcTemplate.update(sql, studentId, courseId);
    }

    @Override
    public void removeEnrollment(int studentId, int courseId) {
        String sql = """
                DELETE FROM Student_Course
                WHERE studentId=? AND courseId=?
                """;
        jdbcTemplate.update(sql, studentId, courseId);
    }

    @Override
    public List<Course> getCoursesByStudent(int studentId) {
        String sql = """
                SELECT c.*
                FROM Course c
                JOIN Student_Course sc ON c.Id = sc.courseId
                WHERE sc.studentId = ?
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Course c = new Course();
            c.setId(rs.getInt("Id"));
            c.setCourseName(rs.getString("courseName"));
            c.setCourseCode(rs.getString("courseCode"));
            c.setDescription(rs.getString("description"));
            return c;
        }, studentId);
    }

    @Override
    public List<Student> getStudentsByCourse(int courseId) {
        String sql = """
                SELECT s.*
                FROM Student s
                JOIN Student_Course sc ON s.Id = sc.studentId
                WHERE sc.courseId = ?
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Student s = new Student();
            s.setId(rs.getInt("Id"));
            s.setFirstName(rs.getString("firstName"));
            s.setLastName(rs.getString("lastName"));
            s.setEmail(rs.getString("email"));
            s.setDateOfBirth(rs.getObject("dateOfBirth", java.time.LocalDate.class));
            s.setEnrollmentDate(rs.getObject("enrollmentDate", java.time.LocalDate.class));
            return s;
        }, courseId);
    }

    @Override
    public List<StudentCourseDTO> getStudentCourseDetails(int studentId) {
        String sql = """
                SELECT
                    c.Id AS courseId,
                    c.courseName,
                    c.courseCode,
                    c.description,
                    sc.enrollmentDate AS enrolledDate
                FROM Student_Course sc
                JOIN Course c ON c.Id = sc.courseId
                WHERE sc.studentId = ?
                ORDER BY sc.enrollmentDate DESC
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new StudentCourseDTO(
                        rs.getInt("courseId"),
                        rs.getString("courseName"),
                        rs.getString("courseCode"),
                        rs.getString("description"),
                        rs.getTimestamp("enrolledDate")
                ), studentId);
    }

    @Override
    public List<CourseStudentDTO> getCourseStudentDetails(int courseId) {
        String sql = """
                SELECT
                    s.Id AS studentId,
                    CONCAT(s.firstName, ' ', s.lastName) AS studentName,
                    s.email,
                    sc.enrollmentDate AS enrolledDate
                FROM Student_Course sc
                JOIN Student s ON s.Id = sc.studentId
                WHERE sc.courseId = ?
                ORDER BY sc.enrollmentDate DESC
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new CourseStudentDTO(
                        rs.getInt("studentId"),
                        rs.getString("studentName"),
                        rs.getString("email"),
                        rs.getTimestamp("enrolledDate")
                ), courseId);
    }

    @Override
    public void removeEnrollmentsByStudent(int studentId) {
        String sql = "DELETE FROM Student_Course WHERE studentId=?";
        jdbcTemplate.update(sql, studentId);
    }

    @Override
    public boolean existsEnrollment(int studentId, int courseId) {
        String sql = """
                SELECT COUNT(*)
                FROM Student_Course
                WHERE studentId=? AND courseId=?
                """;

        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, studentId, courseId);
        return count != null && count > 0;
    }
}
