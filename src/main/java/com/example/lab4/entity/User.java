package com.example.lab4.entity;

import javax.persistence.*;

@Entity
@Table(name = "tableOfUsers")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String login;
    private String password;


    public User(){}

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString(){
        return "User: login = "+ login + "; password = "+ password;
    }
}
