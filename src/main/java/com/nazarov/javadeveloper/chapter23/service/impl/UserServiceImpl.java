package com.nazarov.javadeveloper.chapter23.service.impl;

import com.nazarov.javadeveloper.chapter23.entity.Region;
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
        Writer result = writerService.getById(id);
        result.setPosts(postService.getByWriterId(result.getId()));
        result.setRegion(regionService.getById(result.getRegion().getId()));

        return result;
    }

    public Writer getByFirstName(String firstName) {
        Writer result = writerService.getByFirstName(firstName);
        result.setPosts(postService.getByWriterId(result.getId()));
        result.setRegion(regionService.getById(result.getRegion().getId()));

        return result;
    }

    public Writer getByLastName(String lastName) {
        Writer result = writerService.getByLastName(lastName);
        result.setPosts(postService.getByWriterId(result.getId()));
        result.setRegion(regionService.getById(result.getRegion().getId()));

        return result;
    }

    @Override
    public boolean remove(Writer writer) {
        return writerService.remove(writer);
    }
}