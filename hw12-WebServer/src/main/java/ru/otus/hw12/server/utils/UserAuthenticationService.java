package ru.otus.hw12.server.utils;

public class UserAuthenticationService {
    private final String ADMIN_USERNAME = "admin";
    private final String ADMIN_PASSWORD = "qwerty";

    public boolean isUserLoginPasswordCorrect(String username, String password) {
        return (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD));
    }
}
