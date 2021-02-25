package com.nazarov.javadeveloper.chapter23.controllers;

import com.nazarov.javadeveloper.chapter23.entity.Writer;
import com.nazarov.javadeveloper.chapter23.service.UserService;
import com.nazarov.javadeveloper.chapter23.service.impl.UserServiceImpl;

public class UserController {
    private final UserService userService;

    public UserController() {
        this.userService = new UserServiceImpl();
    }

    public Writer save(Writer writer){
        return userService.save(writer);
    }

    public Writer update(Writer writer){
        return userService.update(writer);
    }

    public Writer get(Long id){
        return userService.get(id);
    }

    public Writer getByFirstName(String firstName){

        return userService.getByFirstName(firstName);
    }

    public Writer getByLastName(String lastName){
        return userService.getByLastName(lastName);
    }

    public boolean remove(Writer writer){
        return userService.remove(writer);
    }
}