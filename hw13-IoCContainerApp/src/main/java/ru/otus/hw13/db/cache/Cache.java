package ru.otus.hw13.db.cache;

public interface Cache<K, V> {
    void put(K key, V value);

    void remove(K key);

    V get(K key);

    void addListener(CacheListener cacheListener);

    void removeListener(CacheListener cacheListener);

    int getCountListeners();
}
