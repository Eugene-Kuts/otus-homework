package ru.otus.hw07.atm;

import ru.otus.hw07.atm.support.Banknote;
import ru.otus.hw07.visitor.ATMVisitor;

import java.util.Map;

public interface ATM {

    /*
     * Получение остатка денег в банкомате
     *
     * @return доступное количество денег в банкомате
     */
    Integer getTotalAmountInNomunal();

    /*
     * кладем деньги
     *
     * @param value - количество банкнот, которые необходимо положить в ячейку
     * @param banknote номинал банкноты {@link Banknote}
     */
    void putMoney(Banknote banknote, Integer value);

    /*
     * снимаем деньги
     *
     * @param valueToget - количество денег, которые мы хотим снять
     *
     * @return полученные деньги соответсвующих номиналов {@link Banknote} и количестве
     */
    Map<Banknote, Integer> getMoney(Integer valueToget);

    /**
     * Посещаем данный банкомат для получения остатка
     *
     * @param visitor объект {@link ATMVisitor}
     *
     * @return сумма остатка в банкомате
     */
    Integer accept(ATMVisitor visitor);
}
