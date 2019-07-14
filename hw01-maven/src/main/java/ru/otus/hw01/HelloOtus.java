package ru.otus.hw01;

import java.util.List;
import java.util.Map;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

public class HelloOtus {
    public static void main(String[] args) {

        // initialize collections in one line with Guava

        Map items = ImmutableMap.of("coin", 3, "glass", 4, "pencil", 1);

        System.out.println("ImmutableMap:");

        items.entrySet()
                .stream()
                .forEach(System.out::println);

        List<String> fruits = Lists.newArrayList("orange", "banana", "kiwi",
                "mandarin", "date", "quince");

        System.out.println("---------------------------------------");

        System.out.println("Lists:");
        for (String fruit: fruits) {
            System.out.println(fruit);
        }
    }
}
