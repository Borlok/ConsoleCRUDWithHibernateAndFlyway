package com.borlok.repository.hibernate;

import com.borlok.model.Customer;
import com.borlok.model.Specialty;
import com.borlok.repository.SpecialtyRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public class JpaSpecialtyRepository implements SpecialtyRepository {

    @Override
    public Specialty create(Specialty specialty) {
        Session session = JpaUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Serializable id = session.save(specialty);
        Specialty specialty1 = (Specialty) session.get(Specialty.class, id);

        transaction.commit();
        session.close();
        return specialty1;
    }

    @Override
    public Specialty getById(Integer id) {
        Session session = JpaUtil.getSession();

        Specialty specialty = (Specialty) session.get(Specialty.class, id);

        session.close();
        return specialty;
    }

    @Override
    public Specialty update(Specialty specialty) {
        Session session = JpaUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Specialty specialty1 = (Specialty) session.get(Specialty.class, specialty.getId());

        specialty1.setName(specialty.getName());
        session.update(specialty1);

        transaction.commit();
        session.close();
        return specialty1;
    }

    @Override
    public List<Specialty> getAll() {
        Session session = JpaUtil.getSession();

        List<Specialty> specialties = session.createQuery("from Specialty").list();

        session.close();
        return specialties;
    }

    @Override
    public void delete(Integer id) {
        Session session = JpaUtil.getSession();
        Transaction transaction = session.beginTransaction();

        session.delete(session.get(Specialty.class, id));

        transaction.commit();
        session.close();
    }
}
