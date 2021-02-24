package com.nazarov.javadeveloper.chapter23.repository;

import com.nazarov.javadeveloper.chapter23.entity.Writer;

public interface WriterRepository extends GenericRepository<Writer, Long> {

    Writer findByFirstName(String firstName);
    Writer findByLastName(String lastName);
}
