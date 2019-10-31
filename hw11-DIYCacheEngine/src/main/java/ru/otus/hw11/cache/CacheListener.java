package ru.otus.hw11.cache;

public interface CacheListener<K, V> {
    void notify(K key, V value, String action);
}
