package ru.otus.hw12.server.utils;

public interface UserAuthenticationService {
    /**
     * Проверка корректности введенных данных
     * @param username имя пользователя
     * @param password пароль
     */
    boolean isUserLoginPasswordCorrect(String username, String password);
}
