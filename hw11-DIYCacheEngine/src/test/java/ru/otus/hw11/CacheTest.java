package ru.otus.hw11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.otus.hw11.cache.Cache;
import ru.otus.hw11.cache.CacheImpl;
import ru.otus.hw11.cache.CacheListenerImpl;
import ru.otus.hw11.dataClasses.AddressDataSet;
import ru.otus.hw11.dataClasses.PhoneDataSet;
import ru.otus.hw11.dataClasses.User;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CacheTest {
    private Cache<Integer, WeakReference<User>> cache;

    @BeforeEach
    void setUp() {
        this.cache = new CacheImpl<>();
    }

    @Test
    @DisplayName("PUT")
    void put() {
        for (int i = 0; i < 50; i++) {
            List<PhoneDataSet> phoneDataSet = new ArrayList<>();
            phoneDataSet.add(new PhoneDataSet("495-777-77-77"));
            User user = new User("Иван" + i, 10 + i, new AddressDataSet("Мытная"), phoneDataSet);
            cache.put(i, new WeakReference<>(user));
            assertEquals(user, cache.get(i).get());
        }
    }

    @Test
    @DisplayName("GET")
    void get() {
        List<PhoneDataSet> phoneDataSet = new ArrayList<>();
        phoneDataSet.add(new PhoneDataSet("495-777-77-77"));
        User user = new User("Иван", 77, new AddressDataSet("Мытная"), phoneDataSet);
        cache.put(1000, new WeakReference<>(user));
        assertEquals(user, cache.get(1000).get());
    }

    @Test
    @DisplayName("REMOVE")
    void remove() {
        for (int i = 0; i < 50; i++) {
            List<PhoneDataSet> phoneDataSet = new ArrayList<>();
            phoneDataSet.add(new PhoneDataSet("495-777-77-77"));
            User user = new User("Иван" + i, 10 + i, new AddressDataSet("Мытная"), phoneDataSet);
            cache.put(i, new WeakReference<>(user));
        }

        int cacheSize = 0;
        for (int j = 0; j < 50; j++) {
            cache.remove(j);
            WeakReference<User> userFromCache = cache.get(j);
            if (userFromCache != null) {
                cacheSize++;
            }
        }
        assertEquals(0,cacheSize);
    }

    @Nested
    @DisplayName("GC")
    class GC {
        private Cache<Integer, WeakReference<User>> cacheForGCTest;

        @BeforeEach
        void createCache() {
            this.cacheForGCTest = new CacheImpl<>();

        }

        @Test
        @DisplayName("Создаем пустые Listener'ы и осуществляем их подписку")
        void createAndAddListener() throws InterruptedException {
            for (int i = 1 ; i <= 1000; i++){
                cacheForGCTest.addListener(new CacheListenerImpl<>());
            }
            assertEquals(1000, cacheForGCTest.getCountListeners());
            System.gc();
            Thread.sleep(1000);
        }

        @Test
        @DisplayName("Проверяем, что Listener'ы были удалены GC")
        void checkListenersSize() {
            assertEquals(0, cacheForGCTest.getCountListeners());
        }
    }
}
