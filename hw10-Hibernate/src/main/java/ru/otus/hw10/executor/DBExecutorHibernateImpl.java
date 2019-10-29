package ru.otus.hw10.executor;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@RequiredArgsConstructor
public class DBExecutorHibernateImpl<T> implements DBExecutorHibernate<T> {

    private final SessionFactory sessionFactory;

    /** {@inheritDoc} */
    @Override
    public void create(T objectData){
        System.out.println("Create");
        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(objectData);
            session.getTransaction().commit();
            session.close();
        }
    }

    /** {@inheritDoc} */
    @Override
    public void update(T objectData){
        System.out.println("Update");
        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(objectData);
            session.getTransaction().commit();
            session.close();
        }
    }

    /** {@inheritDoc} */
    @Override
    public <T> T load(long id, Class<T> clazz){
        System.out.println("Load");
        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            T result = session.get(clazz, id);
            session.getTransaction().commit();
            session.close();
            return result;
        }
    }
}
