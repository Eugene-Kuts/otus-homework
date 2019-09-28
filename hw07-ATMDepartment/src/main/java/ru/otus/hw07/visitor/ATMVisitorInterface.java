package ru.otus.hw07.visitor;

import ru.otus.hw07.ATM.ATM;

public interface ATMVisitorInterface {
    /**
     * Получаем сумму остатков в банкомате
     *
     * @return сумма остатка в банкомате {@link ATM}
     */
    Integer visit(ATM atm);
}
