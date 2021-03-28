package dao;

import models.Schedule;
import models.StudentAnswer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class StudentAnswerDao implements Dao<StudentAnswer> {
    public StudentAnswerDao(){}

    @Override
    public StudentAnswer findById(int id){
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(StudentAnswer.class, id);
    }

    public List<StudentAnswer> findByUser(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<StudentAnswer> list = (List<StudentAnswer>) session.createQuery("From StudentAnswer as studentAnswer where studentAnswer.user.id="+id).list();
        return list;
    }

    @Override
    public List<StudentAnswer> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return (List<StudentAnswer>) session.createQuery("From StudentAnswer").list();
    }

    @Override
    public void save(StudentAnswer studentAnswer){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(studentAnswer);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(StudentAnswer studentAnswer) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(studentAnswer);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(StudentAnswer studentAnswer){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(studentAnswer);
        tx1.commit();
        session.close();
    }
}
