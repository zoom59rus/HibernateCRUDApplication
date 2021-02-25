package com.nazarov.javadeveloper.chapter23.controllers;

import com.nazarov.javadeveloper.chapter23.entity.Writer;
import com.nazarov.javadeveloper.chapter23.service.WriterService;
import com.nazarov.javadeveloper.chapter23.service.impl.WriterServiceImpl;

import java.util.List;

public class WriterController {
    private final WriterService writerService;

    public WriterController() {
        this.writerService = new WriterServiceImpl();
    }

    public Writer save(Writer writer){
        return writerService.save(writer);
    }

    public Writer update(Writer writer){
        return writerService.update(writer);
    }

    public Writer getById(Long id){
        return writerService.getById(id);
    }

    public Writer getByFirstName(String firstName){
        return writerService.getByFirstName(firstName);
    }

    public Writer getByLastName(String lastName){
        return writerService.getByLastName(lastName);
    }

    public List<Writer> getAll(){
        return writerService.getAll();
    }

    public boolean remove(Writer writer){
        return writerService.remove(writer);
    }
}
