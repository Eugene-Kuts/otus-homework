package ru.otus.hw06.support;

import ru.otus.hw06.exceptions.IllegalOperationException;
import ru.otus.hw06.exceptions.NotEnoughBanknotesException;

public class BanknoteCell {

    private Banknote banknoteType;
    private Integer countBanknoteInCell;

    //Создаем ячейку с номиналом и количеством банкнот
    public BanknoteCell(Banknote banknote, Integer amount) {
        this.countBanknoteInCell = amount;
        this.banknoteType = banknote;
    }

    //Получение денег (банкнот) в определенном количестве
    public Integer getBanknotes(int value) {
        if (value<0){
            throw new IllegalOperationException("Невозможно выдать отрицательную сумму");
        }
        if (this.countBanknoteInCell - value < 0) {
            throw new NotEnoughBanknotesException(value, this.countBanknoteInCell);
        }
        this.countBanknoteInCell -= value;
        return value;
    }

    //Добавление денег в хранилище в определенном размере
    public void putBanknotes(int value) {
        if (value<0){
            throw new IllegalOperationException("Невозможно положить отрицательную сумму");
        }
        this.countBanknoteInCell += value;
    }

    //какие банкноты лежат в ячейке
    public Banknote getBanknotesType() { return this.banknoteType; }

    //количество банкнот в ячейке
    public Integer getAvailableAmountInCell(){
        return this.countBanknoteInCell;
    }

    //колчество денег в ячейке
    public Integer getAvailableAmountNominalInCell(){
        return this.banknoteType.getNominal()*this.countBanknoteInCell;
    }
}
