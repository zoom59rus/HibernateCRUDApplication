package com.nazarov.javadeveloper.chapter23.repository.impl;

import com.nazarov.javadeveloper.chapter23.annotations.MyRepository;
import com.nazarov.javadeveloper.chapter23.entity.Region;
import com.nazarov.javadeveloper.chapter23.repository.RegionRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@MyRepository
public class RegionRepositoryImpl extends GenericRepositoryImp<Region, Long> implements RegionRepository {

    public RegionRepositoryImpl() {
        super(Region.class);
    }

    @Override
    public Region findById(Long id) {
        Session session = super.getSession();
        Region find = session.get(Region.class, id);

        return find;
    }

    @Override
    public Region findByName(String name) {
        Session session = super.getSession();

        String sqlQuery = String.format("SELECT * FROM regions WHERE name='%s'", name);
        Region find = (Region) session.createSQLQuery(sqlQuery)
                .addEntity(Region.class)
                .uniqueResult();
        return find;
    }

    @Override
    public List<Region> findAll() {
        return null;
    }

    @Override
    public Region save(Region region) {
        Session session = null;

        try {
            session = super.getSession();
            session.getTransaction().begin();
            session.save(region);
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

        return region;
    }

    @Override
    public Region update(Region region) {
        Session session = null;
        String sqlQuery = String.format("UPDATE regions SET name='%s' WHERE id=%d", region.getName(), region.getId());
        try {
            session = super.getSession();
            session.getTransaction().begin();
            int row = session.createSQLQuery(sqlQuery).addEntity(Region.class).executeUpdate();
            if (row == 0 || row > 1) {
                //Some Warn
            }
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

        return region;
    }

    @Override
    public void remove(Region region) {
        Session session = null;
        Transaction tx = null;

        try {
            session = super.getSession();
            tx = session.getTransaction();
            tx.begin();

            session.delete(region);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }
}
