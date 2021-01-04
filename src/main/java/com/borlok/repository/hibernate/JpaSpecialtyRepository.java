package com.borlok.repository.hibernate;

import com.borlok.model.Specialty;
import com.borlok.repository.SpecialtyRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public class JpaSpecialtyRepository implements SpecialtyRepository {
    private SessionFactory sessionFactory;

    public JpaSpecialtyRepository() {
    }

    public JpaSpecialtyRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Specialty create(Specialty specialty) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Serializable id = session.save(specialty);
        Specialty specialty1 = (Specialty) session.get(Specialty.class, id);

        transaction.commit();
        session.close();
        return specialty1;
    }
    @Override
    public Specialty update(Specialty specialty, Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Specialty specialty1 = (Specialty) session.get(Specialty.class, id);

        specialty1.setName(specialty.getName());
        session.update(specialty1);

        transaction.commit();
        session.close();
        return specialty1;
    }

    @Override
    public List<Specialty> getAll() {
        Session session = sessionFactory.openSession();

        List<Specialty> specialties = session.createQuery("from Specialty").list();

        session.close();
        return specialties;    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(session.get(Specialty.class, id));

        transaction.commit();
        session.close();
    }

    @Override
    public String toString() {
        return "JpaSpecialtyRepository{}";
    }
}
