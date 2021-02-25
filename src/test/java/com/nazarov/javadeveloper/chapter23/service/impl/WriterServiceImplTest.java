package com.nazarov.javadeveloper.chapter23.service.impl;

import com.nazarov.javadeveloper.chapter23.entity.Region;
import com.nazarov.javadeveloper.chapter23.entity.Writer;
import com.nazarov.javadeveloper.chapter23.repository.impl.WriterRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WriterServiceImplTest {

    @Mock
    private WriterRepositoryImpl writerRepository;

    @InjectMocks
    private WriterServiceImpl writerService;

    private Writer writer;
    private Region region;

    @BeforeEach
    void setUp() {
        region = new Region();
        region.setId(10L);
        region.setName("Perm");

        writer = new Writer();
        writer.setId(5L);
        writer.setFirstName("Anton");
        writer.setLastName("Nazarov");
        writer.setRegion(region);
        writer.setPosts(new ArrayList<>());
    }

    @Test
    void save() {
        Mockito.when(writerRepository.save(writer)).thenAnswer(invocation -> {
            writer.setId(55L);
            return writer;
        });

        Writer result = writerService.save(writer);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getFirstName());
        assertNotNull(result.getLastName());
        assertNotNull(result.getRegion());
        assertNotNull(result.getPosts());

        assertEquals(55L, result.getId());

        Mockito.verify(writerRepository, Mockito.times(1)).save(Mockito.any(Writer.class));
    }

    @Test
    void update() {
        Writer update = new Writer("Test", "Testov", region);
        update.setId(5L);
        Mockito.when(writerRepository.update(update)).thenAnswer(invocation -> {
            writer.setFirstName(update.getFirstName());
            writer.setLastName(update.getLastName());
            return writer;
        });

        Writer result = writerService.update(update);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getFirstName());
        assertNotNull(result.getLastName());
        assertNotNull(result.getRegion());
        assertNotNull(result.getPosts());

        assertEquals(update.getId(), result.getId());
        assertEquals(update.getFirstName(), result.getFirstName());
        assertEquals(update.getLastName(), result.getLastName());

        Mockito.verify(writerRepository, Mockito.times(1)).update(Mockito.any(Writer.class));

    }

    @Test
    void getById() {
        Mockito.when(writerRepository.getById(writer.getId())).thenReturn(writer);

        Writer result = writerService.getById(writer.getId());

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getFirstName());
        assertNotNull(result.getLastName());
        assertNotNull(result.getRegion());
        assertNotNull(result.getPosts());

        assertEquals(writer.getId(), result.getId());

        Mockito.verify(writerRepository, Mockito.times(1)).getById(Mockito.anyLong());
    }

    @Test
    void getByFirstName() {
        Mockito.when(writerRepository.getByFirstName(Mockito.anyString())).thenReturn(writer);

        Writer result = writerService.getByFirstName("Anton");

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getFirstName());
        assertNotNull(result.getLastName());
        assertNotNull(result.getRegion());
        assertNotNull(result.getPosts());

        assertEquals("Anton", writer.getFirstName());

        Mockito.verify(writerRepository, Mockito.times(1)).getByFirstName(Mockito.anyString());
    }

    @Test
    void getByLastName() {
        Mockito.when(writerRepository.getByLastName("Nazarov")).thenReturn(writer);

        Writer result = writerService.getByLastName("Nazarov");

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getFirstName());
        assertNotNull(result.getLastName());
        assertNotNull(result.getRegion());
        assertNotNull(result.getPosts());

        assertEquals("Nazarov", writer.getLastName());

        Mockito.verify(writerRepository, Mockito.times(1)).getByLastName(Mockito.anyString());
    }

    @Test
    void getAll() {
        Mockito.when(writerRepository.getAll()).thenReturn(new ArrayList<>());

        List<Writer> results = writerService.getAll();

        assertNotNull(results);

        Mockito.verify(writerRepository, Mockito.times(1)).getAll();

    }

    @Test
    void remove() {
        Mockito.when(writerRepository.getById(writer.getId())).thenReturn(null);

        boolean result = writerService.remove(writer);

        assertEquals(true, result);
    }
}