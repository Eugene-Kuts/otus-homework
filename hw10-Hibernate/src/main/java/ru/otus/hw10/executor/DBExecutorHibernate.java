package ru.otus.hw10.executor;

import java.sql.SQLException;

/**
 * Executor DB
 * @param <T> - обобщенный класс
 */
public interface DBExecutorHibernate<T> {
    /**
     * Запись обобщенного объекта в БД
     * @param objectData обощенный объект для записи
     */
    void create(T objectData);


    /**
     * Update данных в БД
     * @param objectData обощенный объект для обновления
     */
    void update(T objectData);

    /**
     * Получение данных из БД
     * @param id Ключ
     * @param clazz класс для выгрузки
     * @return объект с выгруженными данными
     */
    <T> T load(long id, Class<T> clazz);
}
