package ru.otus.hw15.messagesystem;

public enum MsClientName {
    FRONTEND("frontendService"),
    DATABASE("databaseService");

    private String name;

    public String getName() {
        return name;
    }

    MsClientName(String name) {
        this.name = name;
    }
}
