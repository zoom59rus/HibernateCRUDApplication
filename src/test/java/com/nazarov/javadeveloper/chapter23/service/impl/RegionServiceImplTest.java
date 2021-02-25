package com.nazarov.javadeveloper.chapter23.service.impl;

import com.nazarov.javadeveloper.chapter23.entity.Region;
import com.nazarov.javadeveloper.chapter23.repository.impl.RegionRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RegionServiceImplTest {

    @Mock
    private RegionRepositoryImpl regionRepository;

    @InjectMocks
    private RegionServiceImpl regionService;

    private Region testRegion = new Region(8L, "Tested region");
    private Region testRegion2 = new Region(null, "Saved test region");
    private Region updateTestRegion = new Region(testRegion.getId(), "Updated region");

    @Test
    void save() {
        Mockito.when(regionRepository.getByName(testRegion2.getName())).thenReturn(null);
        Mockito.when(regionRepository.save(testRegion2)).thenAnswer(invocation -> {
            testRegion2.setId(77L);
            return testRegion2;
        });
        Mockito.when(regionRepository.getByName(testRegion.getName())).thenReturn(testRegion);

        Region region = regionService.save(testRegion);
        assertNotNull(region);
        assertNotNull(region.getId());
        assertNotNull(region.getName());
        assertEquals(8L, testRegion.getId());
        assertEquals("Tested region", testRegion.getName());

        Region region2 = regionService.save(testRegion2);
        assertNotNull(region2);
        assertNotNull(region2.getId());
        assertNotNull(region2.getName());
        assertEquals(77L, testRegion2.getId());
        assertEquals("Saved test region", testRegion2.getName());

        regionService.save(null);

        Mockito.verify(regionRepository, Mockito.times(1)).save(Mockito.any(Region.class));
        Mockito.verify(regionRepository, Mockito.times(2)).getByName(Mockito.anyString());

    }

    @Test
    void update() {
        Mockito.when(regionRepository.update(updateTestRegion)).thenReturn(updateTestRegion);
        Mockito.when(regionRepository.getById(updateTestRegion.getId())).thenReturn(testRegion);
        Mockito.when(regionRepository.getById(99L)).thenReturn(null);

        Region result = regionService.update(updateTestRegion);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getName());
        assertEquals(result.getName(), updateTestRegion.getName());
        assertEquals(result.getId(), updateTestRegion.getId());

        Region result2 = regionService.update(updateTestRegion);
        assertEquals(updateTestRegion, result2);

        Region result3 = regionService.update(new Region(99L, "Some region"));
        assertNull(result3);

        Region result4 = regionService.update(null);
        assertNull(result4);

        Mockito.verify(regionRepository, Mockito.times(2)).update(Mockito.any(Region.class));
        Mockito.verify(regionRepository, Mockito.times(3)).getById(Mockito.anyLong());
    }

    @Test
    void getById() {
        Mockito.when(regionRepository.getById(Mockito.anyLong())).thenReturn(Mockito.mock(Region.class));
        Mockito.when(regionRepository.getById(8L)).thenReturn(testRegion);
        Mockito.when(regionRepository.getById(9L)).thenReturn(null);

        Region region = regionService.getById(7L);
        assertNotNull(region);

        Region region2 = regionService.getById(8L);
        assertNotNull(region2);
        assertNotNull(region2.getId());
        assertNotNull(region2.getName());
        assertEquals(testRegion.getId(), region2.getId());
        assertEquals(testRegion.getName(), region2.getName());

        Region region3 = regionService.getById(null);
        assertNull(region3);

        Region region4 = regionService.getById(9L);
        assertNull(region4);

        Mockito.verify(regionRepository, Mockito.times(3)).getById(Mockito.anyLong());

    }

    @Test
    void getByName() {
        Mockito.when(regionRepository.getByName(Mockito.anyString())).thenReturn(Mockito.mock(Region.class));
        Mockito.when(regionRepository.getByName(testRegion.getName())).thenReturn(testRegion);
        Mockito.when(regionRepository.getByName("Some name")).thenReturn(null);

        Region region = regionService.getByName("Some region");
        assertNotNull(region);

        Region region2 = regionService.getByName(testRegion.getName());
        assertNotNull(region2);
        assertNotNull(region2.getId());
        assertNotNull(region2.getName());
        assertEquals(testRegion.getId(), region2.getId());
        assertEquals(testRegion.getName(), region2.getName());

        Region region3 = regionService.getByName(null);
        assertNull(region3);

        Region region4 = regionService.getByName("Some name");
        assertNull(region4);

        Mockito.verify(regionRepository, Mockito.times(3)).getByName(Mockito.anyString());
    }

    @Test
    void remove() {
        Mockito.when(regionRepository.getById(testRegion.getId())).thenReturn(testRegion);

        boolean result = regionService.remove(null);
        assertEquals(false, result);

        boolean result2 = regionService.remove(testRegion);
        assertEquals(true, result2);

        boolean result3 = regionService.remove(new Region(10L, "Some region"));
        assertEquals(false, result3);

        Mockito.verify(regionRepository, Mockito.times(1)).remove(Mockito.any(Region.class));
        Mockito.verify(regionRepository, Mockito.times(2)).getById(Mockito.any(Long.class));
    }

}