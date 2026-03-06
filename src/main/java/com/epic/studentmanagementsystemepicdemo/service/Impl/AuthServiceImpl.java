package com.epic.studentmanagementsystemepicdemo.service.Impl;

import com.epic.studentmanagementsystemepicdemo.exception.ResourceNotFoundException;
import com.epic.studentmanagementsystemepicdemo.model.User;
import com.epic.studentmanagementsystemepicdemo.repository.UserRepo;
import com.epic.studentmanagementsystemepicdemo.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepo userRepository;

    public AuthServiceImpl(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void registerUser(User user) {
        userRepository.saveUser(user);
    }

    @Override
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
