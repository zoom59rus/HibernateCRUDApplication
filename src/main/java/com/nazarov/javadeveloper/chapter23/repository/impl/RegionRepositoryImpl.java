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
    public Region getById(Long id) {
        Region find = null;
        try (
                Session session = super.getSession()
        ) {
            find = session.get(Region.class, id);
        }

        return find;
    }

    @Override
    public Region getByName(String name) {
        Region find = null;
        try (
                Session session = super.getSession()
        ) {
            String sqlQuery = String.format("SELECT * FROM regions WHERE name='%s'", name);
            find = (Region) session.createSQLQuery(sqlQuery)
                    .addEntity(Region.class)
                    .uniqueResult();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return find;
    }

    @Override
    public List<Region> getAll() {
        return null;
    }

    @Override
    public Region save(Region region) {
        Transaction tx = null;

        try (Session session = super.getSession()
        ) {
            tx = session.getTransaction();
            tx.begin();
            session.save(region);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e.getMessage());
        }

        return region;
    }

    @Override
    public Region update(Region region) {
        Transaction tx = null;
        String sqlQuery = String.format("UPDATE regions SET name='%s' WHERE id=%d", region.getName(), region.getId());
        try (
                Session session = super.getSession()
        ) {
            tx = session.getTransaction();
            int row = session.createSQLQuery(sqlQuery).addEntity(Region.class).executeUpdate();
            if (row == 0 || row > 1) {
                //Some Warn
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e.getMessage());
        }

        return region;
    }

    @Override
    public void remove(Region region) {
        Transaction tx = null;

        try (
                Session session = super.getSession()
        ) {
            tx = session.getTransaction();
            tx.begin();

            session.delete(region);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e.getMessage());
        }
    }
}
