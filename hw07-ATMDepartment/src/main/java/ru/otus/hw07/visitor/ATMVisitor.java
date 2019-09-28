package ru.otus.hw07.visitor;

import ru.otus.hw07.ATM.ATM;

public class ATMVisitor implements ATMVisitorInterface {

    /** {@inheritDoc} */
    @Override
    public Integer visit(ATM atm) {
       return atm.getTotalAmountInNomunal();
    }

}
