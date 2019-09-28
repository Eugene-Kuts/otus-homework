package ru.otus.hw06.support;

/*
Ячейка с банкнотами
 */

public interface BanknoteCellInterface {
    /*
    * Получение денег (банкнот) в определенном количестве
    *
    * @param value - количество банкнот, которые необходимо выдать
    * @return количество банконт, которые выдаются из ячейки
    */
    Integer getBanknotes(int value);

    /*
    * Добавление денег в хранилище в определенном размере
    *
    * @param value - количество банкнот, которые необходимо положить в ячейку
    * @param banknote номинал банкноты {@link Banknote}
    */
    void putBanknotes(int value);

    /*
    * какие банкноты лежат в ячейке
    *
    * @return тип банкнот в ячейке {@link Banknote}
    */
    Banknote getBanknotesType();

    /*
    * количество банкнот в ячейке
    *
    * @return доступное количество банконт в ячейке
    */
    Integer getAvailableAmountInCell();

    /*
    * колчество денег в ячейке
    *
    * @return доступное количество денег в ячейке (количество банкнот * номинал банкноты)
    */
    Integer getAvailableAmountNominalInCell();

}
