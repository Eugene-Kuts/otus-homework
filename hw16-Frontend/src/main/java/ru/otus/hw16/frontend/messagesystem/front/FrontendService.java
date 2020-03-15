package ru.otus.hw16.frontend.messagesystem.front;


import ru.otus.hw16.frontend.domain.User;
import ru.otus.message.Message;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public interface FrontendService {
    void addUser(User user, Consumer<User> dataConsumer);

    void createUser(String frontMessage, Consumer<String> dataConsumer);

    <T> Optional<Consumer<T>> takeConsumer(UUID sourceMessageId, Class<T> tClass);

    void sendUserMessage(Message message);
}
