package com.CyberMallBackEnd.CyberMallBackEnd.controller;


import com.CyberMallBackEnd.CyberMallBackEnd.Entity.User;
import com.CyberMallBackEnd.CyberMallBackEnd.dto.LoginRequest;
import com.CyberMallBackEnd.CyberMallBackEnd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        try {
            userService.signup(user);
            return ResponseEntity.ok("User Registered Successfully!");
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace(); // Log exception for debugging
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Email or Username already exists. Please try again.");
        } catch (Exception e) {
            e.printStackTrace(); // Log unexpected errors
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: " + e.getMessage());
        }
    }

    // Endpoint for user login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest.getEmail(), loginRequest.getPassword())
                .map(user -> ResponseEntity.ok("Login Successful!"))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials"));
    }


    @GetMapping("/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        System.out.println("Looking for user with email: " + email); // Debugging log
        return userService.getUserByEmail(email)
                .<ResponseEntity<?>>map(user -> {
                    System.out.println("User found: " + user);
                    return ResponseEntity.ok(user); // Return user object
                })
                .orElseGet(() -> {
                    System.out.println("User not found for email: " + email);
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(Map.of("status", "error", "message", "User not found"));
                });
    }

    //Endpoint for all user fetch
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(users);
    }

    //Endpoint for  user update
    @PutMapping("/{email}")
    public ResponseEntity<?> updateUser(@PathVariable String email, @RequestBody User updatedUser) {
        return userService.updateUser(email, updatedUser)
                .map(user -> ResponseEntity.ok("User updated successfully!"))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found"));
    }

    //Endpoint for user delete
    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        if (userService.deleteUserByEmail(email)) {
            return ResponseEntity.ok("User deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

}
