package ru.otus.hw16.frontend.messagesystem;

import ru.otus.message.Message;

public interface MsClient {

    void addHandler(MessageType type, RequestHandler requestHandler);

    boolean sendMessage(Message msg);

    void handle(Message msg);

    String getName();

    <T> Message produceMessage(String to, T data, MessageType msgType);

}