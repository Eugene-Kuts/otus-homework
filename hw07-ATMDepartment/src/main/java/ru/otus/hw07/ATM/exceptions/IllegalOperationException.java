package ru.otus.hw07.ATM.exceptions;

public class IllegalOperationException extends RuntimeException {

    public IllegalOperationException(String message) {
        super(message);
    }

}
