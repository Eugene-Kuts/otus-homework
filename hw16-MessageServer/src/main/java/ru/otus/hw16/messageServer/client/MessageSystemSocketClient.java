package ru.otus.hw16.messageServer.client;

import ru.otus.message.Message;
/**
 * Класс реализует Клиента в рамках клиент-сервисного взаимодействия с Системой обмена сообщениями
 *
 * @autor Eugene Kuts
 *
 * @version 1.0
 */
public interface MessageSystemSocketClient {
    /**
     * Функция отправки сообщения
     *  @param message - сообщение {@link Message}
     *  @param clientHost - хост
     *  @param clientPort - порт
     */
    void sendMessage(Message message, String clientHost, int clientPort);
}
