package ru.otus.hw11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.hw11.cache.Cache;
import ru.otus.hw11.cache.CacheImpl;
import ru.otus.hw11.cache.CacheListenerImpl;
import ru.otus.hw11.dataClasses.User;

import java.lang.ref.WeakReference;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Проверка работы GC с Cash'ом")
public class CacheGCTest {
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
