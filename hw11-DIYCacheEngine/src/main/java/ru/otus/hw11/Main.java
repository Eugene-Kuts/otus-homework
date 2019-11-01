package ru.otus.hw11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.hw11.cache.CacheImpl;
import ru.otus.hw11.cache.CacheListenerImpl;

public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);
    private long counter = 0;

    public static void main(String[] args) throws InterruptedException {

       // new Main().loop();
       // CacheListenerImpl<String,String> aaaa=new CacheListenerImpl<String,String>();
        //aaaa.notify("qqqqqqq", "ddddddddddddd", "sssssssssss");
        CacheImpl myCache = new CacheImpl();
        myCache.put(1, "Один");
        myCache.put(2, "Два");
        myCache.put(3, "Три");
        myCache.put(2, "Пять");
        myCache.remove(3);
        System.out.println(myCache.get(2));
    }

    public void loop() throws InterruptedException {
        while(counter<10) {
            logger.info("info level:{}", counter);
            logger.error("error level:{}", counter);
            System.out.println("counter= " + counter);
            counter++;
            Thread.sleep(1_000);
        }
    }
}
