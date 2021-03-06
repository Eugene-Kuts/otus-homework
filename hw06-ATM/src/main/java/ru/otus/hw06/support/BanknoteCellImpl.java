package ru.otus.hw06.support;

import ru.otus.hw06.exceptions.IllegalOperationException;
import ru.otus.hw06.exceptions.NotEnoughBanknotesException;

public class BanknoteCellImpl implements BanknoteCell {

    private final Banknote banknoteType;
    private Integer countBanknoteInCell;

    //Создаем ячейку с номиналом и количеством банкнот
    public BanknoteCellImpl(Banknote banknote, Integer amount) {
        this.countBanknoteInCell = amount;
        this.banknoteType = banknote;
    }

    //Получение денег (банкнот) в определенном количестве
    /** {@inheritDoc} */
    @Override
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
    /** {@inheritDoc} */
    @Override
    public void putBanknotes(int value) {
        if (value<0){
            throw new IllegalOperationException("Невозможно положить отрицательную сумму");
        }
        this.countBanknoteInCell += value;
    }

    //какие банкноты лежат в ячейке
    /** {@inheritDoc} */
    @Override
    public Banknote getBanknotesType() { return this.banknoteType; }

    //количество банкнот в ячейке
    /** {@inheritDoc} */
    @Override
    public Integer getAvailableAmountInCell(){
        return this.countBanknoteInCell;
    }

    //колчество денег в ячейке
    /** {@inheritDoc} */
    @Override
    public Integer getAvailableAmountNominalInCell(){
        return this.banknoteType.getNominal()*this.countBanknoteInCell;
    }
}
