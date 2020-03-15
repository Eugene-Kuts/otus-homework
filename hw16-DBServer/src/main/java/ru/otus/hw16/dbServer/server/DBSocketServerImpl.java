package ru.otus.hw16.dbServer.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.hw16.dbServer.messagesystem.MsClient;
import ru.otus.message.Message;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

@Service
public class DBSocketServerImpl implements DBSocketServer{
    private static Logger logger = LoggerFactory.getLogger(DBSocketServerImpl.class);

    private final MsClient dbServerMsClient;
    private final int dbServerPort;

    public DBSocketServerImpl(MsClient dbServerMsClient, @Value("${DB-server.port}") int dbServerPort) {
        this.dbServerMsClient = dbServerMsClient;
        this.dbServerPort = dbServerPort;
    }

    /** {@inheritDoc} */
    @Override
    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(dbServerPort)) {
            logger.info("Starting DBServer on port: {}", dbServerPort);
            while (!Thread.currentThread().isInterrupted()) {
                logger.info("Waiting for client connection...");
                try (Socket clientSocket = serverSocket.accept()) {
                    clientHandler(clientSocket);
                }
            }
        } catch (Exception ex) {
            logger.error("error", ex);
        }
    }

    private void clientHandler(Socket clientSocket) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream())) {
            Message receivedMessage = (Message) objectInputStream.readObject();
            logger.info("Received from {} message ID[{}]", receivedMessage.getFrom(), receivedMessage.getId());
            dbServerMsClient.handle(receivedMessage);
        } catch (Exception ex) {
            logger.error("error", ex);
        }
    }
}
