/**
 * @author supunmadhuranga
 * @created 2026-02-19
 * @project StudentManagementSystemEpicdemo
 */

package com.epic.studentmanagementsystemepicdemo.repository.impl;

import com.epic.studentmanagementsystemepicdemo.exception.ResourceNotFoundException;
import com.epic.studentmanagementsystemepicdemo.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    // row mapper for user table (its using for mapping the result set to user object)
    private final RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User u = new User();
        u.setId(rs.getInt("Id"));
        u.setName(rs.getString("Name"));
        u.setPassword(rs.getString("Password"));
        return u;
    };
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // find user by username
    public User findByUsername(String username) {
        String sql = "select * from User where Name = ?";
        try {
            return jdbcTemplate.queryForObject(sql, userRowMapper, username);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("User not found");
        }
    }

    // save user
    public int saveUser(User user) {
        String sql = "INSERT INTO User (Name, Password) VALUES (?, ?)";
        return jdbcTemplate.update(sql,
                user.getName(),
                user.getPassword());
    }

}
