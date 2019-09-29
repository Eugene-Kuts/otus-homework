package ru.otus.hw07;

import ru.otus.hw07.ATM.ATMImpl;
import ru.otus.hw07.visitor.ATMVisitorImpl;

import java.util.ArrayList;

public class ATMDepartmentImpl implements ATMDepartment {
    private final ArrayList<ATMImpl> atms;

    public ATMDepartmentImpl(ArrayList<ATMImpl> atms) {
        this.atms = atms;
    }

    /** {@inheritDoc} */
    @Override
    public Integer getATMAmounts() {
        return this.atms.stream().map(atm -> atm.accept(new ATMVisitorImpl())).reduce(Integer::sum).orElse(0);
    }

    /** {@inheritDoc} */
    @Override
    public void restoreInitialStates() {
        for(ATMImpl atm: this.atms){
            atm.restoreDefaultATM();
        }
    }
}
