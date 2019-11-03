package ru.otus.hw12.db.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheListenerImpl<K, V> implements CacheListener<K, V> {
    static private Logger logger = LoggerFactory.getLogger(CacheListenerImpl.class);

    @Override
    public void notify(K key, V value, String action) {
        logger.info("key : {}, value : {}, action : {}", key, value, action);
    }
}
