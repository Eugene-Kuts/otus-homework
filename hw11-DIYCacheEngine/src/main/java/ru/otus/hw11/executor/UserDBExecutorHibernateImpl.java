package ru.otus.hw11.executor;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.otus.hw11.cache.Cache;
import ru.otus.hw11.dataClasses.User;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class UserDBExecutorHibernateImpl implements DBExecutorHibernate<User> {

    private final SessionFactory sessionFactory;
    private final Cache<Long, User> cache;

    /** {@inheritDoc} */
    @Override
    public void create(User objectData){
        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(objectData);
            session.getTransaction().commit();
            //не добавляем в кэш для тестов. в кеш добавляем при update'e и ищем при load'е
            //cache.put(objectData.getId(), objectData);
            session.close();
        }
    }

    /** {@inheritDoc} */
    @Override
    public void update(User objectData){
        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(objectData);
            session.getTransaction().commit();
            cache.put(objectData.getId(), objectData);
            session.close();
        }
    }

    /** {@inheritDoc} */
    @Override
    public User load(long id) {
        final User resultFromCache = cache.get(id);
        if (resultFromCache != null) {
            return resultFromCache;
        }

        //SLOW DATA
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            final User result = session.get(User.class, id);
            session.getTransaction().commit();
            session.close();
            return result;
        }
    }

}
