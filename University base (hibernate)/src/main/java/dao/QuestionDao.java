package dao;

import models.Question;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class QuestionDao implements Dao<Question> {
    public QuestionDao(){}

    @Override
    public Question findById(int id){
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Question.class, id);
    }


    @Override
    public List<Question> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return (List<Question>) session.createQuery("From Question").list();
    }

    @Override
    public void save(Question question){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(question);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Question question) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(question);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Question question){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(question);
        tx1.commit();
        session.close();
    }
}
