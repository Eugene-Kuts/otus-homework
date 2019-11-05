package ru.otus.hw12.server;

import lombok.SneakyThrows;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import ru.otus.hw12.db.cache.Cache;
import ru.otus.hw12.db.cache.CacheImpl;
import ru.otus.hw12.db.dataClasses.User;
import ru.otus.hw12.db.executor.DBExecutorHibernate;
import ru.otus.hw12.db.executor.UserDBExecutorHibernateImpl;
import ru.otus.hw12.db.utils.SessionFactories;
import ru.otus.hw12.server.servlets.AdminServlet;
import ru.otus.hw12.server.servlets.LoginServlet;
import ru.otus.hw12.server.utils.AuthorizationFilter;
import ru.otus.hw12.server.utils.TemplateProcessor;
import ru.otus.hw12.server.utils.UserAuthenticationService;
import ru.otus.hw12.server.utils.UserAuthenticationServiceImpl;

public class ServerStarter {

    private static final int PORT = 8080;
    private static final String RESOURCES_PATH = "/static";
    private static UserAuthenticationService USER_AUTHENTICATION_SERVICE = new UserAuthenticationServiceImpl();
    private DBExecutorHibernate<User> userDBExecutor;
    private Server server;
    private TemplateProcessor templateProcessor = new TemplateProcessor();

    @SneakyThrows
    public void start() {
        startDB();
        startJetty();
    }

    private void startJetty() throws Exception {
        ResourceHandler resourceHandler = new ResourceHandler();
        Resource resource = Resource.newClassPathResource(RESOURCES_PATH);
        resourceHandler.setBaseResource(resource);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new LoginServlet(USER_AUTHENTICATION_SERVICE, templateProcessor)), "/login");
        context.addServlet(new ServletHolder(new AdminServlet(userDBExecutor, templateProcessor)), "/admin");
        context.addFilter(new FilterHolder(new AuthorizationFilter()), "/admin", null);
        server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));
        server.start();
        server.join();
    }

    private void startDB() {
        Cache<Long,User> cache = new CacheImpl<>();
        userDBExecutor = new UserDBExecutorHibernateImpl(SessionFactories.get(),cache);
    }

    @SneakyThrows
    public void stop() {
        server.stop();
    }
}
