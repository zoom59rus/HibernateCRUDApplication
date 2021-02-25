package com.nazarov.javadeveloper.chapter23.controllers;

import com.nazarov.javadeveloper.chapter23.entity.Region;
import com.nazarov.javadeveloper.chapter23.service.RegionService;
import com.nazarov.javadeveloper.chapter23.service.impl.RegionServiceImpl;

public class RegionController {
    private final RegionService regionService;

    public RegionController() {
        this.regionService = new RegionServiceImpl();
    }

    public Region save(Region region){
        return regionService.save(region);
    }
    public Region update(Region region){
        return regionService.update(region);
    }
    public Region getById(Long id){
        return regionService.getById(id);
    }
    public Region getByName(String name){
        return regionService.getByName(name);
    }
    public boolean remove(Region region){
        return regionService.remove(region);
    }
}
