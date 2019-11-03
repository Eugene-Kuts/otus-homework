package ru.otus.hw12.server.servlets;

import ru.otus.hw12.server.utils.TemplateProcessor;
import ru.otus.hw12.server.utils.UserAuthenticationService;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {
    private static final String LOGIN_PAGE = "login.html";
    private static final String AUTHENTICATION_RESULT_MSG = "resultMsg";
    private UserAuthenticationService userAuthenticationService;
    private TemplateProcessor templateProcessor;

    public LoginServlet(UserAuthenticationService userAuthenticationService) throws IOException {
        this.userAuthenticationService = userAuthenticationService;
        this.templateProcessor = new TemplateProcessor();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put(AUTHENTICATION_RESULT_MSG, "");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(templateProcessor.getPage(LOGIN_PAGE, pageVariables));
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (userAuthenticationService.isUserLoginPasswordCorrect(username, password)) {
            request.getSession();
            response.sendRedirect("/admin");
        } else {
            Map<String, Object> pageVariables = new HashMap<>();
            pageVariables.put(AUTHENTICATION_RESULT_MSG, "Введите корректный логин/пароль");
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println(templateProcessor.getPage(LOGIN_PAGE, pageVariables));
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
