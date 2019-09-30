package ru.otus.hw07.visitor;

import ru.otus.hw07.atm.ATMImpl;

public interface ATMVisitor {
    /**
     * Получаем сумму остатков в банкомате
     *
     * @return сумма остатка в банкомате {@link ATMImpl}
     */
    Integer visit(ATMImpl atm);
}
