package ru.otus.hw12.db.cache;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

public class CacheImpl<K, V> implements Cache<K, V> {

    //private static Logger logger = LoggerFactory.getLogger(CacheImpl.class);

    private final WeakHashMap<K, V> data = new WeakHashMap<>();
    private List<WeakReference<CacheListener<K, V>>> listeners = new ArrayList<>();


    @Override
    public void put(K key, V value) {
        data.put(key, value);
        notifyListeners(key, null, "PUT");
    }

    @Override
    public void remove(K key) {
        notifyListeners(key, null, "REMOVE");
        data.remove(key);
    }

    @Override
    public V get(K key) {
        V returnedValue = data.get(key);
        notifyListeners(key, data.get(key), "GET");
        return returnedValue;
    }

    @Override
    public void addListener(CacheListener cacheListener) {
        listeners.add(new WeakReference<>(cacheListener));
    }

    @Override
    public void removeListener(CacheListener cacheListener) {
        listeners.remove(cacheListener);
    }

    @Override
    public int getCountListeners(){
        return this.listeners.size();
    }

    private void notifyListeners(K key, V value, String actionType) {
        for (WeakReference<CacheListener<K, V>> weakReferenceListener : listeners) {
            CacheListener<K, V> listener = weakReferenceListener.get();
            listener.notify(key, value, actionType);
        }
    }
}
