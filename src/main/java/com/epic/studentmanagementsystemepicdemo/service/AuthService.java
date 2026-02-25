/**
 * @author supunmadhuranga
 * @created 2026-02-19
 * @project StudentManagementSystemEpicdemo
 */

package com.epic.studentmanagementsystemepicdemo.service;

import com.epic.studentmanagementsystemepicdemo.model.User;
import com.epic.studentmanagementsystemepicdemo.repository.impl.UserRepository;
import org.springframework.stereotype.Service;

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
        User dbUser = userRepository.findByUsername(user.getName());
        if (!dbUser.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Username or password is incurrect");
        }
        return "User Login successfully";


    }


}
