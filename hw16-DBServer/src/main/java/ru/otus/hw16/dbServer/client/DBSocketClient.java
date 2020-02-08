package ru.otus.hw16.dbServer.client;

import ru.otus.message.Message;
/**
 * Класс реализует Клиента в рамках клиент-сервисного взаимодействия с Системой обмена сообщениями
 *
 * @autor Eugene Kuts
 *
 * @version 1.0
 */
public interface DBSocketClient {
    /**
     * Функция отправки сообщения в Систему обмена сообщениями
     *  @param message - сообщение {@link Message}
     */
    void sendMessage(Message message);
}
