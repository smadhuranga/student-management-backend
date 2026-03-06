/**
 * @author supunmadhuranga
 * @created 2026-02-19
 * @project StudentManagementSystemEpicdemo
 */

package com.epic.studentmanagementsystemepicdemo.repository.impl;

import com.epic.studentmanagementsystemepicdemo.exception.ResourceNotFoundException;
import com.epic.studentmanagementsystemepicdemo.model.Student;
import com.epic.studentmanagementsystemepicdemo.repository.StudentRepo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository implements StudentRepo {
    private final JdbcTemplate jdbcTemplate;

    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // row mapper for student table (its using for mapping the result set to student object)

    public RowMapper<Student> studentRowMapper = (rs, rowNum) -> {
        Student s = new Student();
        s.setId(rs.getInt("id"));
        s.setFirstName(rs.getString("firstName"));
        s.setLastName(rs.getString("lastName"));
        s.setEmail(rs.getString("email"));
        s.setDateOfBirth(rs.getDate("dateOfBirth"));
        s.setEnrollmentDate(rs.getDate("enrollmentDate"));

        return s;
    };

    // save student
    @Override
    public int saveStudent(Student s) {
        String sql = """
                    INSERT INTO Student
                    (firstName, lastName, email, dateOfBirth, enrollmentDate)
                    VALUES (?, ?, ?, ?, ?)
                """;

        jdbcTemplate.update(sql,
                s.getFirstName(),
                s.getLastName(),
                s.getEmail(),
                s.getDateOfBirth(),
                s.getEnrollmentDate()
        );


        Integer id = jdbcTemplate.queryForObject(
                "SELECT LAST_INSERT_ID()", Integer.class);

        return id;
    }

    // get all students
    @Override
    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM Student ORDER BY Id DESC";
        return jdbcTemplate.query(sql, studentRowMapper);
    }

    public Student findById(int id) {
        String sql = "SELECT * FROM Student WHERE Id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, studentRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Student not found");
        }
    }

    // update student by id
    @Override
    public int update(Student s) {
        String sql = """
                UPDATE Student
                SET firstName=?,
                    lastName=?,
                    email=?,
                    dateOfBirth=?,
                    enrollmentDate=?
                WHERE Id=?
                """;

        int rows = jdbcTemplate.update(sql,
                s.getFirstName(),
                s.getLastName(),
                s.getEmail(),
                s.getDateOfBirth(),
                s.getEnrollmentDate(),
                s.getId()
        );
        if (rows == 0) {
            throw new ResourceNotFoundException("Student not found");
        }
        return rows;
    }

    // delete student by id
    @Override
    public int delete(int id) {
        String sql = "DELETE FROM Student WHERE Id=?";
        int rows = jdbcTemplate.update(sql, id);

        if (rows == 0) {
            throw new ResourceNotFoundException("Student not found");
        }
        return rows;

    }

    @Override
    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM Student WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }

    @Override
    public boolean existsByEmailAndIdNot(String email, int id) {
        String sql = "SELECT COUNT(*) FROM Student WHERE email = ? AND id != ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email, id);
        return count != null && count > 0;
    }


    public void removeAllEnrollments(int studentId) {
        String sql = "DELETE FROM Student_Course WHERE studentId=?";
        jdbcTemplate.update(sql, studentId);
    }
}
