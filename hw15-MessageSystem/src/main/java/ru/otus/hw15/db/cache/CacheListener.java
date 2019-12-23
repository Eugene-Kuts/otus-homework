package ru.otus.hw15.db.cache;

public interface CacheListener<K, V> {
    void notify(K key, V value, String action);
}
