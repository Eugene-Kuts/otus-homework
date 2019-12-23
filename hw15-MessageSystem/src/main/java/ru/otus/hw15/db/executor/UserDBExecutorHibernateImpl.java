package ru.otus.hw15.db.executor;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.otus.hw15.db.cache.Cache;
import ru.otus.hw15.db.domain.User;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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
            cache.put(objectData.getId(), objectData);
            session.close();
        }
    }

    @Override
    public long createWithId(User objectData) {
        long id = objectData.getId();
        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(objectData);
            session.getTransaction().commit();
            cache.put(id, objectData);
            session.close();
        }
        return id;
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

    /** {@inheritDoc}
     * @return*/
    @Override
    public User load(long id) {
        final User resultFromCache = cache.get(id);
        if (resultFromCache != null) {
            return resultFromCache;
        }
        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            final User result = session.get(User.class, id);
            session.getTransaction().commit();
            session.close();
            return result;
        }
    }

    /** {@inheritDoc} */
    @Override
    public List<User> loadAll() {
        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            final CriteriaBuilder criteriaBuilder= session.getCriteriaBuilder();
            final CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            final Root<User> rootEntry = criteriaQuery.from(User.class);
            final CriteriaQuery<User> getAll = criteriaQuery.select(rootEntry);
            final TypedQuery<User> getAllQuery = session.createQuery(getAll);
            final List<User> result = getAllQuery.getResultList();
            session.getTransaction().commit();
            session.close();
            return result;
        }
    }

}
