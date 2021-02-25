package com.nazarov.javadeveloper.chapter23.repository.impl;

import com.nazarov.javadeveloper.chapter23.ApplicationContext;
import com.nazarov.javadeveloper.chapter23.Context;
import com.nazarov.javadeveloper.chapter23.entity.Region;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RegionRepositoryImplTest {

    @InjectMocks
    private RegionRepositoryImpl regionRepository;

    private Region testRegion;

    @BeforeEach
    void setUp() {
        Context context = ApplicationContext.init();
        testRegion = new Region(null, "Регион для теста");
        testRegion = regionRepository.save(testRegion);
    }

    @Test
    void findById() {
        Region truRegion = regionRepository.getById(testRegion.getId());

        assertNotNull(truRegion);
        assertNotNull(truRegion.getId());
        assertNotNull(truRegion.getName());
        assertEquals(testRegion.getId(), truRegion.getId());

        Region falseRegion = regionRepository.getById(101L);

        assertNull(falseRegion);
    }

    @Test
    void findByName() {
        Region truRegion = regionRepository.getByName(testRegion.getName());

        assertNotNull(truRegion);
        assertNotNull(truRegion.getId());
        assertNotNull(truRegion.getName());
        assertEquals(testRegion.getName(), truRegion.getName());

        Region falseRegion = regionRepository.getByName("Some_Name");

        assertNull(falseRegion);
    }

    @Test
    void findAll() {
    }

    @Test
    void save() {
        Region region = new Region("Тест регион");
        region = regionRepository.save(region);

        assertNotNull(region);
        assertNotNull(region.getId());
        assertNotNull(region.getName());
        assertEquals("Тест регион", region.getName());
    }

    @Test
    void update() {

        Region updated = new Region(testRegion.getId(), "Регион изменен");
        try {
            updated = regionRepository.update(updated);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull(updated);
        assertEquals(testRegion.getId(), updated.getId());
        assertNotEquals(testRegion.getName(), updated.getName());
        assertNotEquals(testRegion, updated);

        try {
            updated = regionRepository.update(testRegion);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull(updated);
        assertEquals(testRegion, updated);
        assertEquals(testRegion.getId(), updated.getId());
        assertEquals(testRegion.getName(), updated.getName());
    }

    @Test
    void remove() {
        regionRepository.remove(testRegion);

        Region region = regionRepository.getById(testRegion.getId());

        assertNull(region);
    }
}