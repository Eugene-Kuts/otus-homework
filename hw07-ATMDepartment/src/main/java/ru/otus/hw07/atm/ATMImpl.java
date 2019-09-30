package ru.otus.hw07.atm;

import ru.otus.hw07.atm.momento.ATMState;
import ru.otus.hw07.atm.exceptions.NotEnoughBanknotesException;
import ru.otus.hw07.atm.exceptions.UnableToIssueRequestedAmountException;
import ru.otus.hw07.atm.support.ATMService;
import ru.otus.hw07.atm.support.Banknote;
import ru.otus.hw07.atm.support.BanknoteCellImpl;
import ru.otus.hw07.visitor.ATMVisitor;

import java.util.*;
import java.util.stream.Collectors;

public class ATMImpl implements ATM, ATMService {

    private ATMState defaultATMstate;
    private Map<Banknote, BanknoteCellImpl> cells;

    public ATMImpl(Map<Banknote, BanknoteCellImpl> cells) {
        this.cells = cells;
        this.defaultATMstate = new ATMState(cells);
    }

    //Получение остатка денег в банкомате
    /** {@inheritDoc} */
    @Override
    public Integer getTotalAmountInNomunal(){
        return this.cells.values().stream().map(BanknoteCellImpl::getAvailableAmountNominalInCell).reduce(Integer::sum).get();
    }

    //кладем деньги
    /** {@inheritDoc} */
    @Override
    public void putMoney(Banknote banknote, Integer value){
        this.cells.get(banknote).putBanknotes(value);
    }

    //снимаем деньги
    /** {@inheritDoc} */
    @Override
    public Map<Banknote, Integer> getMoney(Integer valueToget){

        final Map<Banknote, Integer> returnedBanknotes = new HashMap<>();

        if (this.getTotalAmountInNomunal() < valueToget) {
            throw new NotEnoughBanknotesException(valueToget,this.getTotalAmountInNomunal());
        }

        final List<Banknote> sortedBanknotes = Arrays.stream(Banknote.values())
                .sorted(Comparator.comparingInt(Banknote::getNominal).reversed())
                .collect(Collectors.toList());

        for (Banknote banknote : sortedBanknotes) {
            int nominal = banknote.getNominal();
            int extractedBanknotes = valueToget / nominal;

            if(valueToget >= nominal*this.cells.get(banknote).getAvailableAmountInCell() ){
                valueToget -= this.cells.get(banknote).getAvailableAmountInCell() * nominal;
                returnedBanknotes.put(banknote,this.cells.get(banknote).getAvailableAmountInCell());
                this.cells.get(banknote).getBanknotes(this.cells.get(banknote).getAvailableAmountInCell());
            }else if(valueToget == extractedBanknotes*nominal){
                valueToget -= extractedBanknotes * nominal;
                returnedBanknotes.put(banknote,extractedBanknotes);
                this.cells.get(banknote).getBanknotes(extractedBanknotes);
            }
        }

        if (valueToget > 0) {
            //возвращаем снятые банкноты обратно
            returnedBanknotes.forEach((banknote, value) -> this.putMoney(banknote,value));
            //очищаем банкноты на выдачу
            returnedBanknotes.clear();
            throw new UnableToIssueRequestedAmountException(valueToget);
        }
        return returnedBanknotes;
    }

    /** {@inheritDoc} */
    @Override
    public void restoreDefaultATM() {
        this.cells = defaultATMstate.getCellsState().entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /** {@inheritDoc} */
    @Override
    public Integer accept(ATMVisitor visitor) {
        return visitor.visit(this);
    }
}
