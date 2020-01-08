package ru.otus.hw16.dbServer.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import ru.otus.message.Message;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class DBClient {

    private static Logger logger = LoggerFactory.getLogger(DBClient.class);

    @Value("${MessageServer.Host}")
    private String messageServerHost;
    @Value("${MessageServer.Port}")
    private int messageServerPort;

    public void sendMessage(Message message) {
        try (Socket clientSocket = new Socket(messageServerHost, messageServerPort);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream())) {
            objectOutputStream.writeObject(message);
            sleep();
        } catch (Exception ex) {
            logger.error("Ошибка:", ex);
        }
    }

    private static void sleep() {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
