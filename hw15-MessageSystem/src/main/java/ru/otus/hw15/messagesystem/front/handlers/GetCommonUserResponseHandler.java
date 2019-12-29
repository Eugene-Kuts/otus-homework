package ru.otus.hw15.messagesystem.front.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.hw15.messagesystem.Message;
import ru.otus.hw15.messagesystem.RequestHandler;
import ru.otus.hw15.messagesystem.common.Serializers;
import ru.otus.hw15.messagesystem.front.FrontendService;

import java.util.Optional;
import java.util.UUID;

public class GetCommonUserResponseHandler implements RequestHandler {

    private static final Logger logger = LoggerFactory.getLogger(GetCommonUserResponseHandler.class);
    private final FrontendService frontendService;
    public GetCommonUserResponseHandler(FrontendService frontendService) {
        this.frontendService = frontendService;
    }

    @Override
    public Optional<Message> handle(Message message) {
        try{
            Object userData = Serializers.deserialize(message.getPayload(), Object.class);
            UUID sourceMessageId = message.getSourceMessageId().orElseThrow(
                    () -> new RuntimeException("Not found sourceMessage for message : {}" + message.getId())
            );
            frontendService.takeConsumer(sourceMessageId, Object.class).ifPresent(
                    consumer -> consumer.accept(userData)
            );
        } catch(Exception ex){
            logger.error("message : {}, {}", message, ex);
        }
        return Optional.empty();
    }
}
