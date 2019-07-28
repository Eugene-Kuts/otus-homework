package ru.otus.hw04;

/*
Запускать  java -javaagent:AutoLogging.jar -jar AutoLogging.jar
 */

public class Main {
    public static void main(String[] args) {
        System.out.println("Main");
        Demo demo = new Demo();
        demo.action();
    }
}
