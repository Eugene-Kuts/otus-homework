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
    private static final String REQUEST_PARAMETER_USER_NAME = "username";
    private static final String REQUEST_PARAMETER_PASSWORD = "password";
    private final UserAuthenticationService userAuthenticationService;
    private final TemplateProcessor templateProcessor;

    public LoginServlet(UserAuthenticationService userAuthenticationService, TemplateProcessor templateProcessor) {
        this.userAuthenticationService = userAuthenticationService;
        this.templateProcessor = templateProcessor;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        createResponse(response,"");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (this.userAuthenticationService.isUserLoginPasswordCorrect(request.getParameter(REQUEST_PARAMETER_USER_NAME),
                request.getParameter(REQUEST_PARAMETER_PASSWORD))) {
            request.getSession();
            response.sendRedirect("/admin");
        } else {
            createResponse(response,"Введите корректный логин/пароль");
        }
    }

    private void createResponse(HttpServletResponse response, String message) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put(AUTHENTICATION_RESULT_MSG, message);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(this.templateProcessor.getPage(LOGIN_PAGE, pageVariables));
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
