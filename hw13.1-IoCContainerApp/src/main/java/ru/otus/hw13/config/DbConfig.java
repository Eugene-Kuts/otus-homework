package ru.otus.hw13.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.hw13.db.cache.Cache;
import ru.otus.hw13.db.cache.CacheImpl;
import ru.otus.hw13.db.domain.User;
import ru.otus.hw13.db.executor.DBExecutorHibernate;
import ru.otus.hw13.db.executor.UserDBExecutorHibernateImpl;
import ru.otus.hw13.db.utils.SessionFactories;

@Configuration
public class DbConfig {

    @Bean
    public DBExecutorHibernate<User> userDbService() {
        return new UserDBExecutorHibernateImpl(sessionFactory(), userCache());
    }

    public Cache<Long, User> userCache() {
        return new CacheImpl<>();
    }

    public SessionFactory sessionFactory() {
        return SessionFactories.get();
    }
}
