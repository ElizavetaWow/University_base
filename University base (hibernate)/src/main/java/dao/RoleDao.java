package dao;

import models.Role;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class RoleDao implements Dao<Role> {
    public RoleDao(){}

    @Override
    public Role findById(int id){
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Role.class, id);
    }

    public Role findByName(String name){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Role> list = (List<Role>) session.createQuery("From Role as role where role.name='"+name+"'").list();
        return list.get(0);
    }

    @Override
    public List<Role> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return (List<Role>) session.createQuery("From Role").list();
    }

    @Override
    public void save(Role role){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(role);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Role role) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(role);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Role role){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(role);
        tx1.commit();
        session.close();
    }


}
