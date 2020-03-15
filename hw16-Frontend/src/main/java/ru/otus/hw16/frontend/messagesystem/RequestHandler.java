package ru.otus.hw16.frontend.messagesystem;


import ru.otus.message.Message;

import java.util.Optional;

public interface RequestHandler {
    Optional<Message> handle(Message msg);
}
