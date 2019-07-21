package ru.otus.hw03;

import java.lang.reflect.Method;

public class TestRunner {
    public static void main(String[] args){
        Class<TestClass> testClass = TestClass.class;
        runTests(testClass);
    }

    public static void runTests(Class testClass) {
        if(testClass.getDeclaredMethods().length == 0){
            throw new RuntimeException("Нет ни одно метода для теста");
        }
        int tests = 0;
        int passed = 0;
        Method after = testClass.getDeclaredMethods()[0];
        Method before = testClass.getDeclaredMethods()[0];

        for (Method m1 : testClass.getDeclaredMethods()) {
            if (m1.isAnnotationPresent(After.class)) {
                after = m1;
            }
        }

        for (Method m2 : testClass.getDeclaredMethods()) {
            if (m2.isAnnotationPresent(Before.class)) {
                before = m2;
            }
        }

        for (Method m : testClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Test.class)) {
                tests++;
                try {
                    TestClass newTestClass = new TestClass();
                    if(before.isAnnotationPresent(Before.class)) {//аннотации before может и не быть
                        before.invoke(newTestClass);
                    }

                    m.invoke(newTestClass);

                    if(after.isAnnotationPresent(After.class)) {//аннотации after может и не быть
                        after.invoke(newTestClass);
                    }
                    passed++;
                } catch (Exception e) {
                    Throwable exception = e.getCause();
                    System.out.println("Invalid method: " + m.getName() + " with annotation: "+ m.getDeclaredAnnotations()[0] + ". Method failed because: " + exception);
                }
            }
        }
        System.out.printf("Passed: %d, Failed: %d%n",
                passed, tests - passed);
    }
}
