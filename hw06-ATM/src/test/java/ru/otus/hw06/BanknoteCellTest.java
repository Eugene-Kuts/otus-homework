package ru.otus.hw06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.hw06.exceptions.IllegalOperationException;
import ru.otus.hw06.exceptions.NotEnoughBanknotesException;
import ru.otus.hw06.support.Banknote;
import ru.otus.hw06.support.BanknoteCell;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BanknoteCellTest {

    private BanknoteCell banknoteCell;

    //Создаем ячейку с номиналом и количеством банкнот
    @BeforeEach
    void initialize() {
        banknoteCell = new BanknoteCell(Banknote.ONE_HUNDRED, 100);
    }

    //Кладем деньги в ячейку
    @Test
    void putMoneyInCell() {
        banknoteCell.putBanknotes(10);
        banknoteCell.putBanknotes(5);
        assertEquals(115, banknoteCell.getAvailableAmountInCell());
    }

    //Кладем отрицательную сумму в ячейку
    @Test
    void putMoneyInCellException() {
        assertThrows(IllegalOperationException.class, () -> {banknoteCell.putBanknotes(-100);});
    }

    //забираем деньги из ячейки
    @Test
    void getMoney() {
        banknoteCell.getBanknotes(90);
        assertEquals(10, banknoteCell.getAvailableAmountInCell());
    }

    //пытаемся забрать больше денег, нежели есть в ячейке
    @Test
    void getMoneyException() {
        assertThrows(NotEnoughBanknotesException.class, () -> { banknoteCell.getBanknotes(150);});
    }

    //проверяем какие деньги лежат в ячейке
    @Test
    void getBanknotesType() {
        assertEquals(Banknote.ONE_HUNDRED, banknoteCell.getBanknotesType());
    }

    //проверяем доступное количество денег в ячейке
    @Test
    void getAvailableAmountInCell() {
        assertEquals(10000, banknoteCell.getAvailableAmountNominalInCell());
    }

}
