package dao;

import models.TestList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class TestListDao implements Dao<TestList> {
    public TestListDao(){}

    @Override
    public TestList findById(int id){
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(TestList.class, id);
    }


    @Override
    public List<TestList> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return (List<TestList>) session.createQuery("From TestList").list();
    }

    @Override
    public void save(TestList test){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(test);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(TestList test) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(test);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(TestList test){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(test);
        tx1.commit();
        session.close();
    }
}
