package ru.otus.hw06.exceptions;

import ru.otus.hw06.support.Banknote;

import static java.lang.String.format;

public class NotEnoughBanknotesException extends RuntimeException {

    private static final String MESSAGE_TEMPLATE = "Недостаточно банкнот. " +
            "Требуется банкнот: %d, доступно: %d";

    public NotEnoughBanknotesException(int required, int available) {
        super(format(MESSAGE_TEMPLATE, required, available));
    }
}
