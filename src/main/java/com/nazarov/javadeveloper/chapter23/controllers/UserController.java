package com.nazarov.javadeveloper.chapter23.controllers;

import com.nazarov.javadeveloper.chapter23.entity.Writer;
import com.nazarov.javadeveloper.chapter23.service.UserService;
import com.nazarov.javadeveloper.chapter23.service.impl.UserServiceImpl;

public class UserController {
    private final UserService userService;

    public UserController() {
        this.userService = new UserServiceImpl();
    }

    public Writer save(Writer writerDto){
        return userService.save(writerDto);
    }

    public Writer update(Writer writerDto){
        return userService.update(writerDto);
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