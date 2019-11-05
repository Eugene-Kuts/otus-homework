package ru.otus.hw12.server.servlets;

import ru.otus.hw12.db.dataClasses.AddressDataSet;
import ru.otus.hw12.db.dataClasses.PhoneDataSet;
import ru.otus.hw12.db.dataClasses.User;
import ru.otus.hw12.db.executor.DBExecutorHibernate;
import ru.otus.hw12.server.utils.TemplateProcessor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AdminServlet extends HttpServlet {
    private static final String ADMIN_PAGE = "admin.html";
    private static final String CREATE_USER_METHOD_VAR = "createdUser";
    private static final String GET_USERS_LIST_METHOD_VAR = "usersList";
    private static final String REQ_PARAM_USER_NAME = "userName";
    private static final String REQ_PARAM_USER_AGE = "userAge";
    private static final String REQ_PARAM_USER_ADDRESS = "userAddress";
    private static final String REQ_PARAM_USER_PHONE = "userPhone";
    private static final String CREATE_USER_METHOD = "createUser";
    private static final String GET_USERS_LIST_METHOD = "getUsersList";
    private static final String EXIT_ADMIN_PANEL_METHOD = "exitAdminPanel";
    private static final String PAGE_FOR_REDIRECT = "/login";
    private static final String RESP_CONTENT_TYPE = "text/html;charset=utf-8";
    private final TemplateProcessor templateProcessor;
    private final DBExecutorHibernate<User> dbService;

    public AdminServlet(DBExecutorHibernate<User> dbService, TemplateProcessor templateProcessor) {
        this.dbService = dbService;
        this.templateProcessor = templateProcessor;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getParameter(CREATE_USER_METHOD) != null) {
            User userForSave = new User(request.getParameter(REQ_PARAM_USER_NAME),
                    Integer.parseInt(request.getParameter(REQ_PARAM_USER_AGE)),
                    new AddressDataSet(request.getParameter(REQ_PARAM_USER_ADDRESS)),
                    Collections.singletonList(new PhoneDataSet(request.getParameter(REQ_PARAM_USER_PHONE))));
            this.dbService.create(userForSave);
            Map<String, Object> pageVariables = createPageVariables(Collections.emptyList(),Collections.singletonList(userForSave));
            response.setContentType(RESP_CONTENT_TYPE);
            response.getWriter().println(this.templateProcessor.getPage(ADMIN_PAGE, pageVariables));
            response.setStatus(HttpServletResponse.SC_OK);
        } else if (request.getParameter(EXIT_ADMIN_PANEL_METHOD) != null) {
            request.getSession().invalidate();
            response.sendRedirect(PAGE_FOR_REDIRECT);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> pageVariables;
        if (request.getParameter(GET_USERS_LIST_METHOD) != null) {
            pageVariables = createPageVariables(this.dbService.loadAll(), Collections.emptyList());

        } else {
            pageVariables = createPageVariables(Collections.emptyList(),Collections.emptyList());
        }
        response.setContentType(RESP_CONTENT_TYPE);
        response.getWriter().println(this.templateProcessor.getPage(ADMIN_PAGE, pageVariables));
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private Map<String, Object> createPageVariables(Object getUsersListVar, Object createUserVar){
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put(GET_USERS_LIST_METHOD_VAR, getUsersListVar);
        pageVariables.put(CREATE_USER_METHOD_VAR, createUserVar);
        return pageVariables;
    }
}
