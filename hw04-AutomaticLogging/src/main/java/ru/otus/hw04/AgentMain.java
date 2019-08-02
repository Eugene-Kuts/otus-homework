package ru.otus.hw04;

import java.lang.instrument.Instrumentation;

public class AgentMain {
    public static void premain(String args, Instrumentation instrumentation) {
        System.out.println("premain");
        instrumentation.addTransformer(new ClassTransformer());
    }
}
