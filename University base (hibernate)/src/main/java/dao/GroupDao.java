package dao;

import models.Group;
import models.Role;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class GroupDao implements Dao<Group> {
    public GroupDao(){}

    @Override
    public Group findById(int id){
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Group.class, id);
    }

    public Group findByName(String name){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Group> list = (List<Group>) session.createQuery("From Group as group where group.name='"+name+"'").list();
        return list.get(0);
    }

    @Override
    public List<Group> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return (List<Group>) session.createQuery("From Group").list();
    }

    @Override
    public void save(Group group){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(group);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Group group) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(group);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Group group){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(group);
        tx1.commit();
        session.close();
    }
}
