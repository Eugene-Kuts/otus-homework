package ru.otus.hw15.config;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.hw15.db.cache.Cache;
import ru.otus.hw15.db.cache.CacheImpl;
import ru.otus.hw15.db.domain.User;
import ru.otus.hw15.db.executor.DBExecutorHibernate;
import ru.otus.hw15.db.executor.UserDBExecutorHibernateImpl;
import ru.otus.hw15.db.utils.SessionFactories;

@Configuration
@ComponentScan
@RequiredArgsConstructor
public class DBExecutorHibernateConfig {
    private final ApplicationContext applicationContext;

    @Bean
    public DBExecutorHibernate<User> userDbService() {
        return new UserDBExecutorHibernateImpl(sessionFactory(), userCache());
    }

    @Bean
    public Cache<Long, User> userCache() {
        return new CacheImpl<>();
    }

    @Bean
    public SessionFactory sessionFactory() {
        return SessionFactories.get();
    }
}
