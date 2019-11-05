package ru.otus.hw12.db.cache;

public interface CacheListener<K, V> {
    void notify(K key, V value, String action);
}
