package ru.otus.hw16.frontend.client;

import ru.otus.message.Message;
/**
 * Класс реализует Клиента в рамках клиент-сервисного взаимодействия с Фронт и Бэк системами
 *
 * @autor Eugene Kuts
 *
 * @version 1.0
 */
public interface FrontEndSocketClient {
    /**
     * Функция отправки сообщения
     *  @param message - сообщение {@link Message}
     */
    void sendMessage(Message message);
}
