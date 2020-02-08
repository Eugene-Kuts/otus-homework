package ru.otus.hw16.dbServer.db.cache;

public interface CacheListener<K, V> {
    void notify(K key, V value, String action);
}
