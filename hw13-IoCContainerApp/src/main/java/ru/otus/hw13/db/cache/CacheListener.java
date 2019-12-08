package ru.otus.hw13.db.cache;

public interface CacheListener<K, V> {
    void notify(K key, V value, String action);
}
