package ru.otus.hw03;

public class TestClass {

    @Before
    void before() {
        System.out.println("before");
    }

    @After
    void after() {
        System.out.println("after");
    }

    @Test
    void method1() {
        System.out.println("method1");
    }

    @Test
    void method2() {
        System.out.println("method2");
        throw new RuntimeException("Не прокатило");//Этот метод упадет, но тест продолжится
    }

    @Test
    void method3() {
        System.out.println("method3");
    }

    @Test
    void method4() {
        System.out.println("method4");
    }

    void method5() {
        //метод без аннотаций для проверки, что он не будет участовать
        System.out.println("method0");
    }
}
