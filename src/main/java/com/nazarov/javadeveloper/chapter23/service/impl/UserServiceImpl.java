package com.nazarov.javadeveloper.chapter23.service.impl;

import com.nazarov.javadeveloper.chapter23.entity.Writer;
import com.nazarov.javadeveloper.chapter23.service.PostService;
import com.nazarov.javadeveloper.chapter23.service.RegionService;
import com.nazarov.javadeveloper.chapter23.service.UserService;
import com.nazarov.javadeveloper.chapter23.service.WriterService;

public class UserServiceImpl implements UserService {
    private final RegionService regionService;
    private final PostService postService;
    private final WriterService writerService;

    public UserServiceImpl() {
        this.regionService = new RegionServiceImpl();
        this.postService = new PostServiceImpl();
        this.writerService = new WriterServiceImpl();
    }

    @Override
    public Writer save(Writer writer) {
        return writerService.save(writer);
    }

    @Override
    public Writer update(Writer writer) {
        return writerService.update(writer);
    }

    @Override
    public Writer get(Long id) {
        return writerService.getById(id);
    }

    public Writer getByFirstName(String firstName) {
        return writerService.getByFirstName(firstName);
    }

    public Writer getByLastName(String lastName) {
        return writerService.getByLastName(lastName);
    }

    @Override
    public boolean remove(Writer writer) {
        return writerService.remove(writer);
    }
}