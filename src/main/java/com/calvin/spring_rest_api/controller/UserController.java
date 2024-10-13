package com.calvin.spring_rest_api.controller;

import java.util.ArrayList;
import java.util.List   ;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.calvin.spring_rest_api.model.User;;

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

    // Add user
    @PostMapping
    public User createUser(@RequestBody User user) {
        users.add(user);
        return user;
    }

    // Delete user
    @DeleteMapping("/{id}")
	public String deleteUser(@PathVariable Long id) {
        users.removeIf(user -> user.getId().equals(id));
        return "User with id " + id + " is deleted.";
    }
}
