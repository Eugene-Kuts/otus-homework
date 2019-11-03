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
import java.util.*;

public class AdminServlet extends HttpServlet {
    private static final String ADMIN_PAGE = "admin.html";
    private static final String CREATE_USER_METHOD_VAR = "createdUser";
    private static final String GET_USERS_LIST_METHOD_VAR = "usersList";
    private final TemplateProcessor templateProcessor;
    private DBExecutorHibernate<User> dbService;

    public AdminServlet(DBExecutorHibernate<User> dbService) throws IOException {
        this.dbService = dbService;
        this.templateProcessor = new TemplateProcessor();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getParameter("createUser") != null) {
            String userName = request.getParameter("userName");
            int userAge = Integer.parseInt(request.getParameter("userAge"));
            String userAddress = request.getParameter("userAddress");
            String userPhone = request.getParameter("userPhone");
            User userForSave = new User();
            userForSave.setName(userName);
            userForSave.setAge(userAge);
            userForSave.setAddressDataSet(new AddressDataSet(userAddress));
            List<PhoneDataSet> phoneDataSet = new ArrayList<>();
            phoneDataSet.add(new PhoneDataSet(userPhone));
            userForSave.setPhoneDataSet(phoneDataSet);
            dbService.create(userForSave);
            Map<String, Object> pageVariables = new HashMap<>();
            pageVariables.put(CREATE_USER_METHOD_VAR, Collections.singletonList(userForSave));
            pageVariables.put(GET_USERS_LIST_METHOD_VAR, Collections.emptyList());
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println(templateProcessor.getPage(ADMIN_PAGE, pageVariables));
            response.setStatus(HttpServletResponse.SC_OK);
        } else if (request.getParameter("exitAdminPanel") != null) {
            request.getSession().invalidate();
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getParameter("getUsersList") != null) {
            Map<String, Object> pageVariables = new HashMap<>();
            pageVariables.put(CREATE_USER_METHOD_VAR, Collections.emptyList());
            pageVariables.put(GET_USERS_LIST_METHOD_VAR, dbService.loadAll());
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println(templateProcessor.getPage(ADMIN_PAGE, pageVariables));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            Map<String, Object> pageVariables = new HashMap<>();
            pageVariables.put(GET_USERS_LIST_METHOD_VAR, Collections.emptyList());
            pageVariables.put(CREATE_USER_METHOD_VAR, Collections.emptyList());
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println(templateProcessor.getPage(ADMIN_PAGE, pageVariables));
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
