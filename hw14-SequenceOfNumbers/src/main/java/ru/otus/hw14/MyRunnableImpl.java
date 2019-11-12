package ru.otus.hw14;

public class MyRunnableImpl implements Runnable {
    private final int runFrom;
    private final int runTo;
    private final Object monitor;

    public MyRunnableImpl(int runFrom, int runTo, Object monitor) {
        this.runFrom = runFrom;
        this.runTo = runTo;
        this.monitor = monitor;
    }

    private void print(int number) {
        synchronized (monitor) {
            try {
                System.out.println(Thread.currentThread().getName() + " : " + number);
                monitor.notifyAll();
                monitor.wait(1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void incrementPrint() {
        for (int i = runFrom; i <= runTo; i++) {
            print(i);
        }
    }

    private void decrementPrint() {
        for (int i = runTo; i >= runFrom; i--) {
            print(i);
        }
    }

    @Override
    public void run() {
        incrementPrint();
        decrementPrint();
    }
}

