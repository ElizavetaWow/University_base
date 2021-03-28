package dao;

import models.Test;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class TestDao implements Dao<Test> {
    public TestDao(){}

    @Override
    public Test findById(int id){
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Test.class, id);
    }


    @Override
    public List<Test> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return (List<Test>) session.createQuery("From Test").list();
    }

    @Override
    public void save(Test test){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(test);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Test test) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(test);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Test test){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(test);
        tx1.commit();
        session.close();
    }
}
