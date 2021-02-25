package com.nazarov.javadeveloper.chapter23.repository.impl;

import com.nazarov.javadeveloper.chapter23.annotations.MyRepository;
import com.nazarov.javadeveloper.chapter23.entity.Writer;
import com.nazarov.javadeveloper.chapter23.repository.WriterRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

@MyRepository
public class WriterRepositoryImpl extends GenericRepositoryImp<Writer, Long> implements WriterRepository {

    public WriterRepositoryImpl() {
        super(Writer.class);
    }

    @Override
    public Writer getByFirstName(String firstName) {
        Writer writer = null;
        try (
                Session session = super.getSession();
        ) {
            writer = (Writer) session.createQuery(String.format("FROM Writer W WHERE W.firstName='%s'", firstName))
                    .list()
                    .get(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return writer;
    }

    @Override
    public Writer getByLastName(String lastName) {
        Writer writer = null;
        try (
                Session session = super.getSession();
        ) {
            writer = (Writer) session.createQuery(String.format("FROM Writer W WHERE W.lastName='%s'", lastName))
                    .list()
                    .get(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return writer;
    }

    @Override
    public Writer getById(Long id) {
        Writer writer = null;
        try (
                Session session = super.getSession();
        ) {
            writer = session.find(Writer.class, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return writer;
    }

    @Override
    public Writer getByName(String firstName) {
        return getByFirstName(firstName);

    }

    @Override
    public List<Writer> getAll() {
        List<Writer> writers = new ArrayList<>();
        try (
                Session session = super.getSession();
        ) {
            writers = session.createSQLQuery("SELECT * FROM writers")
                    .addEntity(Writer.class)
                    .getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return writers;
    }

    @Override
    public Writer save(Writer writer) {
        Transaction tx = null;
        try (
                Session session = super.getSession()
        ) {
            tx = session.getTransaction();
            tx.begin();

            session.save(writer);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e.getMessage());
        }

        return writer;
    }

    @Override
    public Writer update(Writer writer) {
        Transaction tx = null;

        try (
                Session session = super.getSession()
        ) {
            tx = session.getTransaction();
            tx.begin();
            session.update(writer);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e.getMessage());
        }

        return writer;
    }

    @Override
    public void remove(Writer writer) {
        Transaction tx = null;

        try (
                Session session = super.getSession()
        ) {
            tx = session.getTransaction();
            tx.begin();
            session.delete(writer);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e.getMessage());
        }
    }
}
