package com.example.Digital_Receipt_Collector.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Digital_Receipt_Collector.entity.User;
import com.example.Digital_Receipt_Collector.service.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersApiController {

    private final UserService userService;

    @Autowired
    public UsersApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        User created = userService.createUser(user);
        return ResponseEntity.created(URI.create("/api/users/" + created.getId())).body(created);
    }

    @GetMapping
    public List<User> list() {
        return userService.getAllUsers();
    }
}
