package ru.otus.hw15.messagesystem;

public interface MessageSystem {

  void addClient(MsClient msClient);

  void removeClient(String clientId);

  boolean newMessage(Message msg);

  void dispose() throws InterruptedException;
}

