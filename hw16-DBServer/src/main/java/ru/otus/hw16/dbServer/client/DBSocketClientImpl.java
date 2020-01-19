package ru.otus.hw16.dbServer.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.message.Message;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

@Component
public class DBSocketClientImpl implements DBSocketClient{

    private static Logger logger = LoggerFactory.getLogger(DBSocketClientImpl.class);

    @Value("${message-server.host}")
    private String host;
    @Value("${message-server.port}")
    private int port;

    /** {@inheritDoc} */
    @Override
    public void sendMessage(Message message) {
        try (Socket clientSocket = new Socket(host, port);
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
