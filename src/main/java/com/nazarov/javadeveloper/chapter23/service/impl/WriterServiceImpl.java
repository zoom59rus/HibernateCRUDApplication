package com.nazarov.javadeveloper.chapter23.service.impl;

import com.nazarov.javadeveloper.chapter23.ApplicationContext;
import com.nazarov.javadeveloper.chapter23.entity.Writer;
import com.nazarov.javadeveloper.chapter23.repository.WriterRepository;
import com.nazarov.javadeveloper.chapter23.repository.impl.WriterRepositoryImpl;
import com.nazarov.javadeveloper.chapter23.service.WriterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class WriterServiceImpl implements WriterService {
    private final Logger log = LoggerFactory.getLogger(WriterServiceImpl.class);

    private final WriterRepository writerRepository;

    public WriterServiceImpl() {
        this.writerRepository = (WriterRepositoryImpl) ApplicationContext.init().getBean("WriterRepositoryImpl");
    }

    /**
     * This constructor is test only
     * @param writerRepository is Mock
     */
    private WriterServiceImpl(WriterRepositoryImpl writerRepository) {
        this.writerRepository = writerRepository;
    }

    @Override
    public Writer save(Writer writer) {
        return writerRepository.save(writer);
    }

    @Override
    public Writer update(Writer writer) {
        return writerRepository.update(writer);
    }

    @Override
    public Writer getById(Long id) {
        return writerRepository.getById(id);
    }

    @Override
    public Writer getByFirstName(String firstName) {
        return writerRepository.getByFirstName(firstName);
    }

    @Override
    public Writer getByLastName(String lastName) {
        return writerRepository.getByLastName(lastName);
    }

    @Override
    public List<Writer> getAll() {
        return writerRepository.getAll();
    }

    @Override
    public boolean remove(Writer writer) {
        writerRepository.remove(writer);
        Writer result = writerRepository.getById(writer.getId());
        if(result == null){
            return true;
        }

        return false;
    }
}
