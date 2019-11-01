package ru.otus.hw11.executor;

import ru.otus.hw11.dataClasses.User;

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
     * @return объект с выгруженными данными
     */
    User load(long id);
}
