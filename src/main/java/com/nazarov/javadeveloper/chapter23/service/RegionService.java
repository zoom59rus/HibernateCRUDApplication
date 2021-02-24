package com.nazarov.javadeveloper.chapter23.service;

import com.nazarov.javadeveloper.chapter23.entity.Region;

public interface RegionService {

    Region save(Region region);
    Region update(Region region);
    Region getById(Long id);
    Region getByName(String name);
    boolean remove(Region region);
}
