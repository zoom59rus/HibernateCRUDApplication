package com.nazarov.javadeveloper.chapter23.repository.impl;

import com.nazarov.javadeveloper.chapter23.annotations.MyRepository;
import com.nazarov.javadeveloper.chapter23.entity.Writer;
import com.nazarov.javadeveloper.chapter23.repository.WriterRepository;
import org.hibernate.Session;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@MyRepository
public class WriterRepositoryImpl extends GenericRepositoryImp<Writer, Long> implements WriterRepository {

    public WriterRepositoryImpl() {
        super(Writer.class);
    }

    @Override
    public Writer findByFirstName(String firstName) {
        return findByName(firstName);
    }

    @Override
    public Writer findByLastName(String lastName) {
        Session session = null;
        Writer writer = null;
        String sqlQuery = String.format("SELECT * FROM writers WHERE last_name='%s'", lastName);
        try {
            session = super.getSession();
            writer = (Writer) session.createSQLQuery(sqlQuery).addEntity(Writer.class).getSingleResult();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return writer;
    }

    @Override
    public Writer findById(Long id) {
        Session session = null;
        Writer writer = null;
        try {
            session = super.getSession();
            writer = session.find(Writer.class, id);
            writer.setPosts(writer.getPosts());
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return writer;
    }

    @Override
    public Writer findByName(String firstName) {
        Session session = null;
        Writer writer = null;
        String sqlQuery = String.format("SELECT * FROM writers WHERE first_name='%s'", firstName);
        try {
            session = super.getSession();
            writer = (Writer) session.createSQLQuery(sqlQuery).addEntity(Writer.class).getSingleResult();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return writer;
    }

    @Override
    public List<Writer> findAll() {
        Session session = null;
        List<Writer> writers = new ArrayList<>();
        try {
            session = super.getSession();
            writers = session.createSQLQuery("SELECT * FROM writers")
                    .addEntity(Writer.class)
                    .getResultList();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return writers;
    }

    @Override
    public Writer save(Writer writer) {
        Session session = null;
        try {
            session = super.getSession();
            session.getTransaction().begin();

            session.save(writer);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return writer;
    }

    @Override
    public Writer update(Writer writer) {
        Session session = null;
        try {
            session = super.getSession();
            session.getTransaction().begin();
            session.update(writer);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return writer;
    }

    @Override
    public void remove(Writer writer) {
        Session session = null;
        try {
            session = super.getSession();
            session.getTransaction().begin();
            session.delete(writer);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
