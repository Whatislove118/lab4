package com.example.lab4.controllers;

import com.example.lab4.entity.User;
import com.example.lab4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin
    @RequestMapping("/register")
    public ResponseEntity<?> registr(@RequestBody User user) {
        if (userService.findByLogin(user.getLogin()) != null) {
            System.out.println("This login in use");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            System.out.println(user + " registered");
            return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
        }
    }

    @CrossOrigin
    @RequestMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            if (userService.getMatches(user, userService.findByLogin(user.getLogin()).getPassword())) {
                System.out.println("user is login");
                System.out.println("mbean count");
                return new ResponseEntity<>(userService.findByLogin(user.getLogin()), HttpStatus.ACCEPTED);
            } else {
                System.out.println("user is no login");
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } catch (NullPointerException e) {
            System.out.println("user is no login and he know it");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }
}
