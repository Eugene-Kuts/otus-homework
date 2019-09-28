package ru.otus.hw06;

import ru.otus.hw06.support.Banknote;

import java.util.Map;

public interface ATMInterface {

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

}
