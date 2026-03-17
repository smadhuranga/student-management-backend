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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository implements StudentRepo {
    private final JdbcTemplate jdbcTemplate;

    public RowMapper<Student> studentRowMapper = (rs, rowNum) -> {
        Student s = new Student();
        s.setId(rs.getInt("id"));
        s.setFirstName(rs.getString("firstName"));
        s.setLastName(rs.getString("lastName"));
        s.setEmail(rs.getString("email"));
        s.setDateOfBirth(rs.getObject("dateOfBirth", java.time.LocalDate.class));
        s.setEnrollmentDate(rs.getObject("enrollmentDate", java.time.LocalDate.class));
        return s;
    };

    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int saveStudent(Student s) {
        String sql = """
            INSERT INTO Student (firstName, lastName, email, dateOfBirth, enrollmentDate)
            VALUES (?, ?, ?, ?, ?)
        """;
        jdbcTemplate.update(sql,
                s.getFirstName(),
                s.getLastName(),
                s.getEmail(),
                s.getDateOfBirth(),
                s.getEnrollmentDate()
        );
        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        return id;
    }

    @Override
    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM Student ORDER BY Id DESC";
        return jdbcTemplate.query(sql, studentRowMapper);
    }

    @Override
    public List<Student> getAllStudents(String sortBy, String sortOrder) {

        Map<String, String> columnMap = new HashMap<>();
        columnMap.put("id", "Id");
        columnMap.put("name", null); // special handling
        columnMap.put("email", "email");
        columnMap.put("dateOfBirth", "dateOfBirth");
        columnMap.put("enrollmentDate", "enrollmentDate");


        List<String> allowedColumns = Arrays.asList("Id", "email", "dateOfBirth", "enrollmentDate");

        String dbColumn = columnMap.get(sortBy);
        String order = sortOrder.equalsIgnoreCase("asc") ? "ASC" : "DESC";
        String sql = "SELECT * FROM Student";

        if ("name".equals(sortBy)) {

            sql += " ORDER BY firstName " + order + ", lastName " + order;
        } else {
            if (dbColumn == null || !allowedColumns.contains(dbColumn)) {
                dbColumn = "Id"; // default fallback
            }
            sql += " ORDER BY " + dbColumn + " " + order;
        }

        return jdbcTemplate.query(sql, studentRowMapper);
    }

    @Override
    public Student findById(int id) {
        String sql = "SELECT * FROM Student WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, studentRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Student not found");
        }
    }

    @Override
    public int update(Student s) {
        String sql = """
            UPDATE Student
            SET firstName=?, lastName=?, email=?, dateOfBirth=?, enrollmentDate=?
            WHERE id=?
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

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM Student WHERE id=?";
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
}