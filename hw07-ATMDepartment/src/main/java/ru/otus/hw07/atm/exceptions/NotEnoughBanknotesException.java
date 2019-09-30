package ru.otus.hw07.atm.exceptions;

import static java.lang.String.format;

public class NotEnoughBanknotesException extends RuntimeException {

    private static final String MESSAGE_TEMPLATE = "Недостаточно банкнот. " +
            "Требуется банкнот: %d, доступно: %d";

    public NotEnoughBanknotesException(int required, int available) {
        super(format(MESSAGE_TEMPLATE, required, available));
    }
}
