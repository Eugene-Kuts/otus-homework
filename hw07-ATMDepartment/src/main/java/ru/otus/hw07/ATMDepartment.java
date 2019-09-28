package ru.otus.hw07;

import ru.otus.hw07.ATM.ATM;
import ru.otus.hw07.visitor.ATMVisitor;

import java.util.ArrayList;

public class ATMDepartment implements ATMDepartmentInterface {
    private final ArrayList<ATM> atms;

    public ATMDepartment(ArrayList<ATM> atms) {
        this.atms = atms;
    }

    /** {@inheritDoc} */
    @Override
    public Integer getATMAmounts() {
        return this.atms.stream().map(atm -> atm.accept(new ATMVisitor())).reduce(Integer::sum).orElse(0);
    }

    /** {@inheritDoc} */
    @Override
    public void restoreInitialStates() {
        for(ATM atm: this.atms){
            atm.restoreDefaultATM();
        }
    }
}
