package ru.otus.hw16.frontend.messagesystem.front;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.hw16.frontend.domain.User;
import ru.otus.message.Message;
import ru.otus.hw16.frontend.messagesystem.MessageType;
import ru.otus.hw16.frontend.messagesystem.MsClient;
import ru.otus.hw16.frontend.messagesystem.common.Serializers;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@Component
public class FrontendServiceImpl implements FrontendService {
    private static final Logger logger = LoggerFactory.getLogger(FrontendServiceImpl.class);
    private final JsonParser jsonParser = new JsonParser();
    private final Map<UUID, Consumer<?>> consumerMap = new ConcurrentHashMap<>();
    private final MsClient msClient;
    private final String databaseServiceClientName;

    public FrontendServiceImpl(MsClient msClient, @Value("${db-server.name}") String databaseServiceClientName) {
        this.msClient = msClient;
        this.databaseServiceClientName = databaseServiceClientName;
    }

    @Override
    public void addUser(User user, Consumer<User> dataConsumer) {
        //sendUserMessage(user, dataConsumer, MessageType.ADD_USER);
    }

    @Override
    public void createUser(String frontMessage, Consumer<String> dataConsumer) {
        JsonObject jsonObject1 = jsonParser.parse(frontMessage).getAsJsonObject();
        JsonObject jsonObject2 = jsonObject1.getAsJsonObject("messageStr");
        String messageString = jsonObject2.toString();
        Message messageForSent = msClient.produceMessage(databaseServiceClientName, messageString, MessageType.ADD_USER);
        consumerMap.put(messageForSent.getId(), dataConsumer);
        msClient.sendMessage(messageForSent);
    }

    @Override
    public <T> Optional<Consumer<T>> takeConsumer(UUID sourceMessageId, Class<T> tClass) {
        Consumer<T> consumer = (Consumer<T>) consumerMap.remove(sourceMessageId);
        if (consumer == null) {
            return Optional.empty();
        }
        return Optional.of(consumer);
    }

    @Override
    public void sendUserMessage(Message message) {
        logger.info("new message:{}", message);
        try {
            String userJsonData = Serializers.deserialize(message.getPayload(), String.class);
            UUID sourceMessageId = message.getSourceMessageId();
            if (sourceMessageId == null) {
                logger.error("Not found sourceMsg for message with ID:" + message.getId());
                throw new RuntimeException("Not found sourceMsg for message:" + message.getId());
            }
            takeConsumer(sourceMessageId, String.class).ifPresent(consumer -> consumer.accept(userJsonData));
        } catch (Exception ex) {
            logger.error("message: [{}], exception {}", message, ex);
        }
    }
}
