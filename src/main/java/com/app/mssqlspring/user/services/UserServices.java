package com.app.mssqlspring.user.services;

import com.app.mssqlspring.user.controllers.UserDTO;
import com.app.mssqlspring.user.models.User;
import com.app.mssqlspring.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserDTO user) {
        User newUser = new User(user.getName(), user.getAge());

        return userRepository.save(newUser);
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public boolean deleteUser(String id) {
        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return false;
        }

        userRepository.deleteById(id);

        return true;
    }

    public User updateUser(String id, UserDTO request) {
        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return null;
        }

        if (request.getName() != null) user.setName(request.getName());
        if (request.getAge() != 0) user.setAge(request.getAge());

        return userRepository.save(user);
    }
}
