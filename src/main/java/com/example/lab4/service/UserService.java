package com.example.lab4.service;

import com.example.lab4.entity.User;
import com.example.lab4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService  {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;




    public User save(User user)  {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean getMatches(User user,String password){
            return bCryptPasswordEncoder.matches(user.getPassword(),password);
    }

    public User findByLogin(String login){
        return userRepository.findByLogin(login);
    }


}
