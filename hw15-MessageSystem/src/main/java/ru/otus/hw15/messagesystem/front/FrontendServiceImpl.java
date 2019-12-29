package ru.otus.hw15.messagesystem.front;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ru.otus.hw15.db.domain.User;
import ru.otus.hw15.messagesystem.Message;
import ru.otus.hw15.messagesystem.MessageType;
import ru.otus.hw15.messagesystem.MsClient;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class FrontendServiceImpl implements FrontendService {

    private final JsonParser jsonParser = new JsonParser();
    private final Map<UUID, Consumer<?>> consumerMap = new ConcurrentHashMap<>();
    private final MsClient msClient;
    private final String databaseServiceClientName;

    public FrontendServiceImpl(MsClient msClient, String databaseServiceClientName) {
        this.msClient = msClient;
        this.databaseServiceClientName = databaseServiceClientName;
    }

    @Override
    public void addUser(User user, Consumer<User> dataConsumer) {
        sendUserMessage(user, dataConsumer, MessageType.ADD_USER);
    }

    @Override
    public void createUser(String frontMessage, Consumer<String> dataConsumer) {
        JsonObject jsonObject = jsonParser.parse(frontMessage).getAsJsonObject();
        JsonObject messageStr = jsonObject.getAsJsonObject("messageStr");
        String jsonUserData = messageStr.toString();
        Message outMsg = msClient.produceMessage(databaseServiceClientName, jsonUserData, MessageType.ADD_USER);
        consumerMap.put(outMsg.getId(), dataConsumer);
        msClient.sendMessage(outMsg);
    }

    @Override
    public <T> Optional<Consumer<T>> takeConsumer(UUID sourceMessageId, Class<T> tClass) {
        Consumer<T> consumer = (Consumer<T>) consumerMap.remove(sourceMessageId);
        if (consumer == null){
            return Optional.empty();
        }
        return Optional.of(consumer);
    }

    private void sendUserMessage(User user, Consumer<User> dataConsumer, MessageType messageType){
        Message outMessage = msClient.produceMessage(databaseServiceClientName, user, messageType);
        consumerMap.put(outMessage.getId(), dataConsumer);
        msClient.sendMessage(outMessage);
    }
}
