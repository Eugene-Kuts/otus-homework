package ru.otus.hw16.frontend.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.message.Message;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

@Component
public class FrontEndSocketClientImpl implements FrontEndSocketClient{
    private static Logger logger = LoggerFactory.getLogger(FrontEndSocketClientImpl.class);

    /** Порт Системы обмена сообщенями */
    @Value("${message-server.port}")
    private int messageServerPort;

    /** Имя хоста Системы обмена сообщенями */
    @Value("${message-server.host}")
    private String messageServerHost;

    /** {@inheritDoc} */
    @Override
    public void sendMessage(Message message) {
        try {
            try (Socket clientSocket = new Socket(messageServerHost, messageServerPort);
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream())) {
                objectOutputStream.writeObject(message);
                logger.info("Message with ID [{}] is send to MessageServer on host {} and port {}", message.getId(), messageServerHost, messageServerPort);
                sleep();
            }

        } catch (Exception ex) {
            logger.error("error", ex);
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
