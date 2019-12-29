package ru.otus.hw13.config;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import ru.otus.hw13.db.cache.Cache;
import ru.otus.hw13.db.cache.CacheImpl;
import ru.otus.hw13.db.domain.User;
import ru.otus.hw13.db.executor.DBExecutorHibernate;
import ru.otus.hw13.db.executor.UserDBExecutorHibernateImpl;
import ru.otus.hw13.db.utils.SessionFactories;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "ru.otus.hw13")
@RequiredArgsConstructor
public class WebConfig {

    private final ApplicationContext applicationContext;

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(true);
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setOrder(1);
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }

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