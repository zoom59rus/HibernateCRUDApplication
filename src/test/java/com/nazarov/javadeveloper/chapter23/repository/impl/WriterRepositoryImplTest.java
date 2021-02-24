package com.nazarov.javadeveloper.chapter23.repository.impl;

import com.nazarov.javadeveloper.chapter23.entity.Post;
import com.nazarov.javadeveloper.chapter23.entity.Region;
import com.nazarov.javadeveloper.chapter23.entity.Writer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WriterRepositoryImplTest {

    @InjectMocks
    private WriterRepositoryImpl writerRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByFirstName() {
        Writer result = writerRepository.findByFirstName("FirstName");

        assertNotNull(result);
        assertNotNull(result.getPosts());
        assertNotNull(result.getRegion());

        assertEquals(8L, result.getId());
        assertEquals("FirstName", result.getFirstName());
        assertEquals("LastName", result.getLastName());
    }

    @Test
    void findByLastName() {
        Writer result = writerRepository.findByLastName("LastName");

        assertNotNull(result);
        assertNotNull(result.getPosts());
        assertNotNull(result.getRegion());

        assertEquals(8L, result.getId());
        assertEquals("FirstName", result.getFirstName());
        assertEquals("LastName", result.getLastName());
    }

    @Test
    void findById() {
        Writer result = writerRepository.findById(8L);

        assertNotNull(result);
        assertNotNull(result.getPosts());
        assertNotNull(result.getRegion());

        assertEquals(8L, result.getId());
        assertEquals("FirstName", result.getFirstName());
        assertEquals("LastName", result.getLastName());
    }

    @Test
    void findByName_also_findByFirstName() {
    }

    @Test
    void findAll() {
        List<Writer> results = writerRepository.findAll();

        assertNotNull(results);
        assertEquals(2, results.size());
    }

    @Test
    void save() {
        Writer writer = new Writer("FirstName", "LastName", new Region("Oklahoma"));

        writer = writerRepository.save(writer);

        assertNotNull(writer);
        assertNotNull(writer.getId());
        assertNotNull(writer.getFirstName());
        assertNotNull(writer.getLastName());
        assertNotNull(writer.getRegion());

        assertEquals("FirstName", writer.getFirstName());
        assertEquals("LastName", writer.getLastName());
        assertEquals("Oklahoma", writer.getRegion().getName());


    }

    @Test
    void update() {
        Writer update = new Writer();
        update.setId(8L);
        update.setFirstName("Anton");
        update.setLastName("Nazarov");
        update.setRegion(new Region("Permsky kray"));

        Writer isUpdated = writerRepository.update(update);

        assertNotNull(isUpdated);

        assertEquals(8L, isUpdated.getId());
        assertEquals("Anton", isUpdated.getFirstName());
        assertEquals("Nazarov", isUpdated.getLastName());
        assertEquals("Permsky kray", isUpdated.getRegion().getName());

        Writer oldWriter = new Writer("FirstName", "LastName", new Region("Oklahoma"));
        oldWriter.setId(8L);
        writerRepository.update(oldWriter);
    }

    @Test
    void remove() {
        Writer writer = new Writer();
        writer.setId(8L);
        writer.setFirstName("Anton");
        writer.setLastName("Nazarov");
        writer.setRegion(new Region("Permsky kray"));
        writerRepository.remove(writer);
    }
}