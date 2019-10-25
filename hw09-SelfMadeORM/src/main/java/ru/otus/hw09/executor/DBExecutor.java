package ru.otus.hw09.executor;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * Executor DB
 * @param <T> - обобщенный класс
 */
public interface DBExecutor<T> {
    /**
     * Запись обобщенного объекта в БД
     * @param objectData обощенный объект для записи
     */
    void create(T objectData) throws SQLException;


    /**
     * Update данных в БД
     * @param objectData обощенный объект для обновления
     */
    void update(T objectData) throws SQLException;

    /**
     * Получение данных из БД
     * @param id Ключ
     * @param clazz класс для выгрузки
     * @return объект с выгруженными данными
     */
    <T> T load(long id, Class<T> clazz) throws SQLException;
}
