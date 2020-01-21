package com.example.lab4.controllers;

import com.example.lab4.UserDetailsServ;
import com.example.lab4.configuration.WebConfiguration;
import com.example.lab4.entity.User;
import com.example.lab4.service.UserService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;


@RestController
public class UserController {

    @Autowired
    private WebConfiguration webConfiguration;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserDetailsServ userDetailsServ;

    @Autowired
    private UserService userService;

    @CrossOrigin
    @RequestMapping("/register")
    public ResponseEntity<?> registr(@RequestBody User user) {
        if (userService.findByLogin(user.getLogin()) != null) {
            System.out.println("This login in use");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
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
                return new ResponseEntity<>(userService.findByLogin(user.getLogin()), HttpStatus.ACCEPTED);
            } else {
                System.out.println("user is no login");
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } catch (NullPointerException e) {
            System.out.println("user is no login and hi know it");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

//    @CrossOrigin
//    @RequestMapping(value = "/login")
//    public Principal user(Principal principal) {
//        System.out.println(principal.getName() + " signed in");
//        return principal;
//    }
    }
}
