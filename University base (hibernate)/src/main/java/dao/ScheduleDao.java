package dao;

import models.Group;
import models.Schedule;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class ScheduleDao implements Dao<Schedule> {
    public ScheduleDao(){}

    @Override
    public Schedule findById(int id){
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Schedule.class, id);
    }


    @Override
    public List<Schedule> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return (List<Schedule>) session.createQuery("From Schedule").list();
    }

    public List<Schedule> findByGroup(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Schedule> list = (List<Schedule>) session.createQuery("From Schedule as schedule where schedule.group.name='"+name+"'").list();
        return list;
    }
    @Override
    public void save(Schedule schedule){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(schedule);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Schedule schedule) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(schedule);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Schedule schedule){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(schedule);
        tx1.commit();
        session.close();
    }
}
