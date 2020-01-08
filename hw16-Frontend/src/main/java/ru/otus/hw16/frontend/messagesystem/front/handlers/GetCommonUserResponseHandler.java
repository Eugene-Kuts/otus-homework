package ru.otus.hw16.frontend.messagesystem.front.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.message.Message;
import ru.otus.hw16.frontend.messagesystem.RequestHandler;
import ru.otus.hw16.frontend.messagesystem.common.Serializers;
import ru.otus.hw16.frontend.messagesystem.front.FrontendService;

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
        try {
            Object userData = Serializers.deserialize(message.getPayload(), Object.class);
            UUID sourceMessageId = message.getSourceMessageId();
            frontendService.takeConsumer(sourceMessageId, Object.class).ifPresent(
                    consumer -> consumer.accept(userData)
            );
        } catch (Exception ex) {
            logger.error("message : {}, exception {}", message, ex);
        }
        return Optional.empty();
    }
}
