package com.calvin.spring_rest_api.controller;

import java.util.ArrayList;
import java.util.List   ;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calvin.spring_rest_api.modal.User;;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private List<User> users = new ArrayList<>();

    public UserController() {
        users.add(new User(1L, "Sam Doe", "sam@example.com"));
        users.add(new User(2L, "Vincent Dex", "sam@example.com"));
    }

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    // Get user by id
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return users.stream().filter(
            user -> user.getId().equals(id))
            .findFirst()
            .map(user -> ResponseEntity.ok(user))
            .orElse(ResponseEntity.notFound().build());
    }
	
}
