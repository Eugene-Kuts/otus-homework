package ru.otus.hw06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ru.otus.hw06.exceptions.IllegalOperationException;
import ru.otus.hw06.exceptions.NotEnoughBanknotesException;
import ru.otus.hw06.exceptions.UnableToIssueRequestedAmountException;
import ru.otus.hw06.support.Banknote;

public class ATMTest {

    private ATMImpl atm;

    //Создаем банкомант с ячейками заполненными по умолчанию
    @BeforeEach
    void initialize() {
        atm = new ATMImpl();
    }

    //Получаем сумму остатка в банкомате
    @Test
    void getATMFullValueDefault() {
        assertEquals(88500, atm.getTotalAmountInNomunal());
    }

    //Кладем деньги разных номиналов в разные ячейки
    @Test
    void putMoneyInCells() {
        atm.putMoney(Banknote.FIFTY, 10);
        atm.putMoney(Banknote.ONE_HUNDRED, 3);
        atm.putMoney(Banknote.FIVE_HUNDRED, 5);
        atm.putMoney(Banknote.ONE_THOUSAND, 2);
        assertEquals(93800, atm.getTotalAmountInNomunal());
    }

    //проверяем на ошибку 1-го рубля
    @Test
    void getMoneyOddNumber() {
        assertThrows(UnableToIssueRequestedAmountException.class, () -> {atm.getMoney(80001);});
    }

    //снимаем деньги
    @Test
    void getMoney() {
        atm.getMoney(80000);
        assertEquals(8500, atm.getTotalAmountInNomunal());
    }

    //еще раз снимаем деньги
    @Test
    void getMoneySeconVersion() {
        atm.getMoney(80100);
        assertEquals(8400, atm.getTotalAmountInNomunal());
    }

    //проверяем на ошибку попытки снять денег больше имеющихся в банкомате
    @Test
    void notEnoughtMoney() {
        assertThrows(NotEnoughBanknotesException.class, () -> {atm.getMoney(90000);});
    }

    //проверяем на ошибку попытки снять отрицательное число
    @Test
    void negativeNumber() {
        assertThrows(IllegalOperationException.class, () -> {atm.getMoney(-500);});
    }

    //проверяем, что после неудачной попытки снятия баланс не изменился
    @Test
    void getMoneyOddNumberUnchangedBalance() {
        assertThrows(UnableToIssueRequestedAmountException.class, () -> {atm.getMoney(5001);});
        assertEquals(88500, atm.getTotalAmountInNomunal());
    }

}
