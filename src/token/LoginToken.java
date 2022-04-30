package com.example.project.token;

import org.springframework.stereotype.Component;
@Component
public class LoginToken {
    private  long id;
    private  String name;
    private  String role;


    public LoginToken(long id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }
    public LoginToken() {}
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
