package com.example.lab4.repository;

import com.example.lab4.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByLogin(String login);
    User findById(long id);
}
