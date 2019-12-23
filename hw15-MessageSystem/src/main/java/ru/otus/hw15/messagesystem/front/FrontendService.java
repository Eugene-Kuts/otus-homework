package ru.otus.hw15.messagesystem.front;


import ru.otus.hw15.db.domain.User;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public interface FrontendService {

    void addUser(User user, Consumer<User> dataConsumer);
    void createUser(String frontMessage, Consumer<String> dataConsumer);
    <T> Optional<Consumer<T>> takeConsumer(UUID sourceMessageId, Class<T> tClass);
}
