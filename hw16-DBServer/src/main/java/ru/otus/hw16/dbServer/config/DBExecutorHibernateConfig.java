package ru.otus.hw16.dbServer.config;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.hw16.dbServer.db.cache.Cache;
import ru.otus.hw16.dbServer.db.cache.CacheImpl;
import ru.otus.hw16.dbServer.db.domain.User;
import ru.otus.hw16.dbServer.db.executor.DBExecutorHibernate;
import ru.otus.hw16.dbServer.db.executor.UserDBExecutorHibernateImpl;
import ru.otus.hw16.dbServer.db.utils.SessionFactories;

@Configuration
@ComponentScan
@RequiredArgsConstructor
public class DBExecutorHibernateConfig {

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
