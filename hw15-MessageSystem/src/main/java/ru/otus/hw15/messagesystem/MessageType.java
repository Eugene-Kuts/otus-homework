package ru.otus.hw15.messagesystem;

public enum MessageType {
    GET_SAVED_USER("GetSavedUser"),
    ADD_USER("AddUser");

    private final String value;

    public String getValue() {
        return value;
    }

    MessageType(String value) {
        this.value = value;
    }
}
