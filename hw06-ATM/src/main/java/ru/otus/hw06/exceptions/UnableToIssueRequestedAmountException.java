package ru.otus.hw06.exceptions;

import static java.lang.String.format;

public class UnableToIssueRequestedAmountException extends RuntimeException {

    private static final String MESSAGE_TEMPLATE = "Невозможно выдать запрашиваемую сумму без остатка равного %d.";

    public UnableToIssueRequestedAmountException(int remainder) {
        super(format(MESSAGE_TEMPLATE, remainder));
    }
}
