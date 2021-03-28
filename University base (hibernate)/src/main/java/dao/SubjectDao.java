package dao;

import models.Subject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class SubjectDao implements Dao<Subject> {
    public SubjectDao(){}

    @Override
    public Subject findById(int id){
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Subject.class, id);
    }


    @Override
    public List<Subject> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return (List<Subject>) session.createQuery("From Subject").list();
    }

    @Override
    public void save(Subject subject){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(subject);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Subject subject) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(subject);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Subject subject){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(subject);
        tx1.commit();
        session.close();
    }
}
