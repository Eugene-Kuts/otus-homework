package ru.otus.hw07.visitor;

import ru.otus.hw07.atm.ATMImpl;

public class ATMVisitorImpl implements ATMVisitor {

    /** {@inheritDoc} */
    @Override
    public Integer visit(ATMImpl atm) {
       return atm.getTotalAmountInNomunal();
    }

}
