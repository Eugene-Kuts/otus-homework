package ru.otus.hw07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import ru.otus.hw07.atm.ATMImpl;
import ru.otus.hw07.atm.support.Banknote;
import ru.otus.hw07.atm.support.BanknoteCellImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ATMDepartmentTests {
    private ATMDepartmentImpl atmDepartment;
    private ATMImpl atm1;
    private ATMImpl atm2;

    @BeforeEach
    void setUp() {

        //инициализируем первый банкомат
        Map<Banknote, BanknoteCellImpl> cellsForATM1 = new HashMap<>();
        cellsForATM1.put(Banknote.FIFTY, new BanknoteCellImpl(Banknote.FIFTY,10));
        cellsForATM1.put(Banknote.ONE_HUNDRED, new BanknoteCellImpl(Banknote.ONE_HUNDRED,10));
        cellsForATM1.put(Banknote.TWO_HUNDRED, new BanknoteCellImpl(Banknote.TWO_HUNDRED,10));
        cellsForATM1.put(Banknote.FIVE_HUNDRED, new BanknoteCellImpl(Banknote.FIVE_HUNDRED,10));
        cellsForATM1.put(Banknote.ONE_THOUSAND, new BanknoteCellImpl(Banknote.ONE_THOUSAND,10));
        cellsForATM1.put(Banknote.TWO_THOUSAND, new BanknoteCellImpl(Banknote.TWO_THOUSAND,10));
        cellsForATM1.put(Banknote.FIVE_THOUSAND, new BanknoteCellImpl(Banknote.FIVE_THOUSAND,10));
        atm1 = new ATMImpl(cellsForATM1);

        //инициализируем второй банкомат
        Map<Banknote, BanknoteCellImpl> cellsForATM2 = new HashMap<>();
        cellsForATM2.put(Banknote.FIFTY, new BanknoteCellImpl(Banknote.FIFTY,1));
        cellsForATM2.put(Banknote.ONE_HUNDRED, new BanknoteCellImpl(Banknote.ONE_HUNDRED,1));
        cellsForATM2.put(Banknote.TWO_HUNDRED, new BanknoteCellImpl(Banknote.TWO_HUNDRED,1));
        cellsForATM2.put(Banknote.FIVE_HUNDRED, new BanknoteCellImpl(Banknote.FIVE_HUNDRED,1));
        cellsForATM2.put(Banknote.ONE_THOUSAND, new BanknoteCellImpl(Banknote.ONE_THOUSAND,1));
        cellsForATM2.put(Banknote.TWO_THOUSAND, new BanknoteCellImpl(Banknote.TWO_THOUSAND,1));
        cellsForATM2.put(Banknote.FIVE_THOUSAND, new BanknoteCellImpl(Banknote.FIVE_THOUSAND,1));
        atm2 = new ATMImpl(cellsForATM2);


        ArrayList<ATMImpl> atmList = new ArrayList<>();
        atmList.add(atm1);
        atmList.add(atm2);

        atmDepartment = new ATMDepartmentImpl(atmList);
    }

    @Test
    void getATMAmounts(){
        assertEquals(97350, atmDepartment.getATMAmounts());
    }

    @Test
    void getATMAmountsAfterCashOut(){
        atm1.getMoney(5000);
        assertEquals(92350, atmDepartment.getATMAmounts());
    }

    @Test
    void getATMAmountsAfterRestore(){
        atm1.getMoney(5000);
        atmDepartment.restoreInitialStates();
        assertEquals(97350, atmDepartment.getATMAmounts());
    }

}
