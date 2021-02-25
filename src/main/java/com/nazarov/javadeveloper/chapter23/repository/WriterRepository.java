package com.nazarov.javadeveloper.chapter23.repository;

import com.nazarov.javadeveloper.chapter23.entity.Writer;

public interface WriterRepository extends GenericRepository<Writer, Long> {

    Writer getByFirstName(String firstName);
    Writer getByLastName(String lastName);
}
