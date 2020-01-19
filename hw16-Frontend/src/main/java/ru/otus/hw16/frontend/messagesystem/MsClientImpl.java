package ru.otus.hw16.frontend.messagesystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.hw16.frontend.client.FrontEndSocketClient;
import ru.otus.hw16.frontend.messagesystem.common.Serializers;
import ru.otus.message.Message;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MsClientImpl implements MsClient {

    private static final Logger logger = LoggerFactory.getLogger(MsClientImpl.class);

    private final String name;

    private final Map<String, RequestHandler> handlers = new ConcurrentHashMap<>();

    private final FrontEndSocketClient frontEndSocketClient;

    public MsClientImpl(@Value("${frontend-server.name}") String name, FrontEndSocketClient frontEndSocketClient) {
        this.name = name;
        this.frontEndSocketClient = frontEndSocketClient;
    }

    @Override
    public void addHandler(MessageType type, RequestHandler requestHandler) {
        this.handlers.put(type.getValue(), requestHandler);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean sendMessage(Message msg) {
        frontEndSocketClient.sendMessage(msg);

        return true;
    }

    @Override
    public void handle(Message msg) {
        logger.info("new message:{}", msg);
        try {
            RequestHandler requestHandler = handlers.get(msg.getType());
            if (requestHandler != null) {
                requestHandler.handle(msg).ifPresent(this::sendMessage);
            } else {
                logger.error("handler not found for the message type:{}", msg.getType());
            }
        } catch (Exception ex) {
            logger.error("msg:" + msg, ex);
        }
    }

    @Override
    public <T> Message produceMessage(String to, T data, MessageType msgType) {
        return new Message(name, to, null, msgType.getValue(), Serializers.serialize(data));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MsClientImpl that = (MsClientImpl) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(handlers, that.handlers) &&
                Objects.equals(frontEndSocketClient, that.frontEndSocketClient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, handlers, frontEndSocketClient);
    }
}
