package ru.otus.hw05;

import com.sun.management.GarbageCollectionNotificationInfo;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

public class GCCompare {
    private static int ARRAY_SIZE_1 = 100_000; //размер массива для добавления
    private static int ARRAY_SIZE_2 = 50_000; // размер массива для добавления/удаления
    private static int THREAD_SLEEP_IN_MLS = 300;
    private static final PrintFileAssistant printFileAssistant = PrintFileAssistant.getInstance();

    public static void main(String[] args) {
        installGCMonitoring();
        try {
            run(ARRAY_SIZE_1, ARRAY_SIZE_2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            printFileAssistant.close();
        }
    }

    private static void run(int array_size_1, int array_size_2) throws InterruptedException {
        System.out.println("Starting the inf loop");
        List<Object[]> list = new ArrayList<>();
        while (true) {
            Object[] array1 = new Object[array_size_1];
            Object[] array2 = new Object[array_size_2];
            list.add(array1);
            list.add(array2);
            Thread.sleep(THREAD_SLEEP_IN_MLS);
            list.remove(array2);
            System.out.println("list.size=" + list.size() + ", next loop");
        }
    }

    private static void installGCMonitoring() {
        List<GarbageCollectorMXBean> gcbeans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcbean : gcbeans) {
            NotificationEmitter notificationEmitter = (NotificationEmitter) gcbean;
            System.out.println("gcbean.getName(): " + gcbean.getName());
            NotificationListener listener = (notification, handback) -> {
                if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                    GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo
                            .from((CompositeData) notification.getUserData());
                    printFileAssistant.illustrateGCAction(info);
                }
            };
            notificationEmitter.addNotificationListener(listener, null, null);
        }
    }
}
