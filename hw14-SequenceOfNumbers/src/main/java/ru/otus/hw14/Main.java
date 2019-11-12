package ru.otus.hw14;

public class Main {
    public static void main(String[] args) {
        final Object monitor = new Object();
        Thread firstThread = new Thread(new MyRunnableImpl(1, 10, monitor), "Поток 1");
        Thread secondThread = new Thread(new MyRunnableImpl(1, 10, monitor), "Поток 2");
        firstThread.start();
        secondThread.start();
        try {
            firstThread.join();
            secondThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

