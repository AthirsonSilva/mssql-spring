package com.app.mssqlspring.user.controllers;

import com.app.mssqlspring.user.models.User;
import com.app.mssqlspring.user.repositories.UserRepository;
import com.app.mssqlspring.user.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserServices userServices;

    @PostMapping
    public ResponseEntity<Map<String, User>> createUser(@RequestBody UserDTO request) {
        return ResponseEntity.ok(Map.of(
                "user created!",
                userServices.createUser(request)
        ));
    }

    @GetMapping
    public ResponseEntity<Map<String, Iterable<User>>> getUsers() {
        return ResponseEntity.ok(Map.of(
                "users",
                userServices.getUsers()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUser(@PathVariable String id) {
        return ResponseEntity.ok(Map.of(
                "user",
                userServices.getUser(id) == null ? "user not found" : userServices.getUser(id)
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable String id) {
        return ResponseEntity.ok(Map.of(
                "user",
                userServices.deleteUser(id) ? "user deleted" : "user not found"
        ));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable String id, @RequestBody UserDTO request) {
        User user = userServices.getUser(id);

        if (user == null) {
            return ResponseEntity.ok(Map.of(
                    "user",
                    "user not found"
            ));
        }

        return ResponseEntity.ok(Map.of(
                "user",
                userServices.updateUser(id, request)
        ));
    }
}
