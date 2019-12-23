package ru.otus.hw15.db.executor;

import ru.otus.hw15.db.domain.User;

import java.util.List;

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
     * Запись обобщенного объекта в БД
     * @param objectData обощенный объект для записи
     * @return id Ключ
     */
    long createWithId(T objectData);

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

    /**
     * Поиск всех объектов класса {@literal T} в базе данных.
     *
     * @return список найденных объектов
     */
    List<T> loadAll();
}
