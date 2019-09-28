package ru.otus.hw07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import ru.otus.hw07.ATM.ATM;
import ru.otus.hw07.ATM.support.Banknote;
import ru.otus.hw07.ATM.support.BanknoteCell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ATMDepartmentTests {
    private ATMDepartment atmDepartment;
    private ATM atm1;
    private ATM atm2;

    @BeforeEach
    void setUp() {

        //инициализируем первый банкомат
        Map<Banknote, BanknoteCell> cellsForATM1 = new HashMap<>();
        cellsForATM1.put(Banknote.FIFTY, new BanknoteCell(Banknote.FIFTY,10));
        cellsForATM1.put(Banknote.ONE_HUNDRED, new BanknoteCell(Banknote.ONE_HUNDRED,10));
        cellsForATM1.put(Banknote.TWO_HUNDRED, new BanknoteCell(Banknote.TWO_HUNDRED,10));
        cellsForATM1.put(Banknote.FIVE_HUNDRED, new BanknoteCell(Banknote.FIVE_HUNDRED,10));
        cellsForATM1.put(Banknote.ONE_THOUSAND, new BanknoteCell(Banknote.ONE_THOUSAND,10));
        cellsForATM1.put(Banknote.TWO_THOUSAND, new BanknoteCell(Banknote.TWO_THOUSAND,10));
        cellsForATM1.put(Banknote.FIVE_THOUSAND, new BanknoteCell(Banknote.FIVE_THOUSAND,10));
        atm1 = new ATM(cellsForATM1);

        //инициализируем второй банкомат
        Map<Banknote,BanknoteCell> cellsForATM2 = new HashMap<>();
        cellsForATM2.put(Banknote.FIFTY, new BanknoteCell(Banknote.FIFTY,1));
        cellsForATM2.put(Banknote.ONE_HUNDRED, new BanknoteCell(Banknote.ONE_HUNDRED,1));
        cellsForATM2.put(Banknote.TWO_HUNDRED, new BanknoteCell(Banknote.TWO_HUNDRED,1));
        cellsForATM2.put(Banknote.FIVE_HUNDRED, new BanknoteCell(Banknote.FIVE_HUNDRED,1));
        cellsForATM2.put(Banknote.ONE_THOUSAND, new BanknoteCell(Banknote.ONE_THOUSAND,1));
        cellsForATM2.put(Banknote.TWO_THOUSAND, new BanknoteCell(Banknote.TWO_THOUSAND,1));
        cellsForATM2.put(Banknote.FIVE_THOUSAND, new BanknoteCell(Banknote.FIVE_THOUSAND,1));
        atm2 = new ATM(cellsForATM2);


        ArrayList<ATM> atmList = new ArrayList<>();
        atmList.add(atm1);
        atmList.add(atm2);

        atmDepartment = new ATMDepartment(atmList);
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
