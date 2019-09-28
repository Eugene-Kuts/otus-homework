package ru.otus.hw07;

import ru.otus.hw07.visitor.ATMVisitor;

/**
 * Интерфейс департамента АТМ.
 */

public interface ATMDepartmentInterface {
    /**
     * Получение суммы остатков во всех банкоматах
     *
     * @return сумма остатков во всем банкоматах
     */
    Integer getATMAmounts();

    /**
     * Возврат состояния всех банкоматов к начальному состоянию.
     */
    void restoreInitialStates();

}
