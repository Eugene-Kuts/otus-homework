package ru.otus.hw03;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {
    private static int tests = 0;
    private static int passed = 0;
    private static Method after = null;
    private static Method before = null;
    private static List<Method> testMethodsList = new ArrayList<>();
    private static Class testClass = null;

    public static void runTests(String testClassName) {
        findClass(testClassName); //ищем класс
        findAnnotatedMethods(); //ищем аннотированные методы
        runAnnotatedMethods(); // выполняем аннотированные методы

        System.out.printf("Всего тестов: " + tests + ", Успешных: " + passed + ", Неуспешных: " + (tests - passed));
    }

    private static void findAnnotatedMethods(){

        for (Method m : testClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Test.class)) {
                testMethodsList.add(m);
            }else if(m.isAnnotationPresent(After.class)){
                after = m;
            }else if(m.isAnnotationPresent(Before.class)){
                before = m;
            }
        }
    }

    private static void runAnnotatedMethods(){
    Object instance;
        for(Method m : testMethodsList){
            instance = createInstance(testClass);//для каждой тройки @Before @Test @After создаем свой instance
            tests++;
            invokeMethod(before,instance);
            invokeMethod(m,instance);
            invokeMethod(after,instance);
        }
    }

    private static void findClass(String testClassName){
        try {
            testClass = (Class) Class.forName(testClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Object createInstance(Class classForInstance){
        Object inst = new Object();
        try {
            inst = classForInstance.getDeclaredConstructors()[0].newInstance();
        } catch (Exception e) {
            System.out.println(e);
        }
        return inst;
    }

    private static void invokeMethod(Method m, Object ins){
        if(m!=null) {
            try {
                m.invoke(ins);
                if(m.isAnnotationPresent(Test.class)){
                    passed++;
                }
            } catch (Exception e) {
                Throwable exception = e.getCause();
                System.out.println("Invalid method: " + m.getName() + " with annotation: "+ m.getDeclaredAnnotations()[0] + ". Method failed because: " + exception);
            }
        }
    }

}
