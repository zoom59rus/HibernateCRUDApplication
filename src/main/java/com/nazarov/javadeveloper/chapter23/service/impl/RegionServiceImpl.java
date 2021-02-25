package com.nazarov.javadeveloper.chapter23.service.impl;

import com.nazarov.javadeveloper.chapter23.ApplicationContext;
import com.nazarov.javadeveloper.chapter23.entity.Region;
import com.nazarov.javadeveloper.chapter23.repository.RegionRepository;
import com.nazarov.javadeveloper.chapter23.repository.impl.RegionRepositoryImpl;
import com.nazarov.javadeveloper.chapter23.service.RegionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegionServiceImpl implements RegionService {
    private final Logger log = LoggerFactory.getLogger(RegionRepositoryImpl.class);

    private final RegionRepository regionRepository;

    public RegionServiceImpl() {
        this.regionRepository = (RegionRepositoryImpl) ApplicationContext.init().getBean("RegionRepositoryImpl");
    }

    /**
     * This constructor is test only.
     *
     * @param regionRepository is Mock.
     */
    @Deprecated
    private RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public Region save(Region region) {
        if (region == null) {
            log.warn("IN - save - region is null.");
            return null;
        }

        Region find = getByName(region.getName());
        if (find == null) {
            return regionRepository.save(region);
        } else return find;
    }

    @Override
    public Region update(Region region) {
        if (region == null) {
            log.warn("IN - update - region is null.");
            return null;
        }

        Region find = regionRepository.getById(region.getId());
        if(find == null){
            log.warn("IN - update - region on id:{} not found", region.getId());
            return null;
        }else if(find.equals(region)){
            return find;
        }

        return regionRepository.update(region);
    }

    @Override
    public Region getById(Long id) {
        if (id == null) {
            log.info("IN - getById - id is null.");
            return null;
        }

        Region find = regionRepository.getById(id);
        if (find == null) {
            log.warn("IN - getById - region on id:{} not found.", id);
        }

        return find;
    }

    @Override
    public Region getByName(String name) {
        if (name == null) {
            log.warn("IN - getByName - name is null.");
            return null;
        }
        Region find = regionRepository.getByName(name);
        if (find == null) {
            log.warn("IN - getByName - region on name:\'{}\' not found.", name);
        }

        return find;
    }

    @Override
    public boolean remove(Region region) {
        if (region == null) {
            log.warn("IN - remove - region is null.");
            return false;
        }

        Region find = regionRepository.getById(region.getId());
        if (find != null) {
            regionRepository.remove(region);
            return true;
        } else return false;
    }
}
