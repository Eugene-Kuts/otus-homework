package ru.otus.hw16.frontend.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.message.Message;
import ru.otus.hw16.frontend.messagesystem.front.FrontendService;


import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

@Service
public class FrontendSocketServerImpl implements FrontendSocketServer{

    private static Logger logger = LoggerFactory.getLogger(FrontendSocketServerImpl.class);

    private final FrontendService frontendService;

    private final int frontendSocketServerPort;

    public FrontendSocketServerImpl(@Value("${frontend-server.port}") int frontendSocketServerPort, FrontendService frontendService) {
        this.frontendSocketServerPort = frontendSocketServerPort;
        this.frontendService = frontendService;
    }

    /** {@inheritDoc} */
    @Override
    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(frontendSocketServerPort)) {
            logger.info("Starting frontendServerSocket on port: {}", frontendSocketServerPort);
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
            logger.info("Received from {} message ID[{}]", receivedMessage.getFrom(), receivedMessage.getId());
            frontendService.sendUserMessage(receivedMessage);
        } catch (Exception ex) {
            logger.error("Exception", ex);
        }
    }
}
