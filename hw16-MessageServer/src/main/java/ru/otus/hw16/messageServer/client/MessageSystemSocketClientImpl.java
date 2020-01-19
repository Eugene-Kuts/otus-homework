package ru.otus.hw16.messageServer.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.otus.message.Message;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

@Component
public class MessageSystemSocketClientImpl implements MessageSystemSocketClient{

    private static Logger logger = LoggerFactory.getLogger(MessageSystemSocketClientImpl.class);

    /** {@inheritDoc} */
    @Override
    public void sendMessage(Message message, String clientHost, int clientPort) {
        try (Socket clientSocket = new Socket(clientHost, clientPort);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream())) {
            objectOutputStream.writeObject(message);
            logger.info("Message with ID [{}] is send to {} on host {} and port {}", message.getId(), message.getTo(), clientHost, clientPort);
            sleep();
        } catch (Exception ex) {
            logger.error("Exception", ex);
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
