/**
 * @author supunmadhuranga
 * @created 2026-02-19
 * @project StudentManagementSystemEpicdemo
 */

package com.epic.studentmanagementsystemepicdemo.service;

import com.epic.studentmanagementsystemepicdemo.exception.ResourceNotFoundException;
import com.epic.studentmanagementsystemepicdemo.model.User;
import com.epic.studentmanagementsystemepicdemo.repository.impl.UserRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

//this class is using for authentication (user register and login)
@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void registerUser(User user) {
        userRepository.saveUser(user);
    }

    public String login(User user) {
        if (user.getName() == null || user.getPassword() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username or password cannot be empty");
        }

        try {
            User dbUser = userRepository.findByUsername(user.getName());

            if (!dbUser.getPassword().equals(user.getPassword())) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password is incorrect");
            }

            return "User Login successfully";
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password is incorrect");
        }


    }


}
