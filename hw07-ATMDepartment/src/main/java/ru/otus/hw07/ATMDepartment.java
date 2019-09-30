package ru.otus.hw07;

/**
 * Интерфейс департамента АТМ.
 */

public interface ATMDepartment {
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
