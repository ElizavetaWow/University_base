package utils;

import models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil(){}

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            try{
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Group.class);
                configuration.addAnnotatedClass(Test.class);
                configuration.addAnnotatedClass(TestList.class);
                configuration.addAnnotatedClass(Schedule.class);
                configuration.addAnnotatedClass(Question.class);
                configuration.addAnnotatedClass(Answer.class);
                configuration.addAnnotatedClass(StudentAnswer.class);
                configuration.addAnnotatedClass(Role.class);
                configuration.addAnnotatedClass(Subject.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return sessionFactory;

    }
}