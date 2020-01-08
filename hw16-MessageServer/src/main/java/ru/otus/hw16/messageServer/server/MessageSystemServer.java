package ru.otus.hw16.messageServer.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.hw16.messageServer.messagesystem.MessageSystem;
import ru.otus.message.Message;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class MessageSystemServer {
    private static Logger logger = LoggerFactory.getLogger(MessageSystemServer.class);

    @Value("${messageserver.port}")
    private int messageServerPort;

    @Autowired
    private MessageSystem messageSystem;

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(messageServerPort)) {
            logger.info("Starting MessageServer on port: {}", messageServerPort);
            while (!Thread.currentThread().isInterrupted()) {
                logger.info("Waiting for client connection...");
                try (Socket clientSocket = serverSocket.accept()) {
                    clientHandler(clientSocket);
                }
            }
        } catch (Exception ex) {
            logger.error("Exception", ex);
        }
    }

    private void clientHandler(Socket clientSocket) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream())) {
            Message receivedMessage = (Message) objectInputStream.readObject();
            logger.info("Received from {}: message ID[{}] ", receivedMessage.getFrom(), receivedMessage.getId());
            messageSystem.newMessage(receivedMessage);
        } catch (Exception ex) {
            logger.error("Exception", ex);
        }
    }
}
