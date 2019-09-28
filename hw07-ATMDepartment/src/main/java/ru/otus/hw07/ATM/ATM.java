package ru.otus.hw07.ATM;

import ru.otus.hw07.ATM.Momento.ATMState;
import ru.otus.hw07.ATM.exceptions.NotEnoughBanknotesException;
import ru.otus.hw07.ATM.exceptions.UnableToIssueRequestedAmountException;
import ru.otus.hw07.ATM.support.Banknote;
import ru.otus.hw07.ATM.support.BanknoteCell;
import ru.otus.hw07.visitor.ATMVisitor;

import java.util.*;
import java.util.stream.Collectors;

public class ATM implements ATMInterface {

    private ATMState defaultATMstate;
    private Map<Banknote,BanknoteCell> cells;

    public ATM(Map<Banknote, BanknoteCell> cells) {
        this.cells = cells;
        this.defaultATMstate = new ATMState(cells);
    }

    //Получение остатка денег в банкомате
    /** {@inheritDoc} */
    @Override
    public Integer getTotalAmountInNomunal(){
        return this.cells.values().stream().map(BanknoteCell::getAvailableAmountNominalInCell).reduce(Integer::sum).get();
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
                this.cells.get(banknote).getBanknotes(this.cells.get(banknote).getAvailableAmountInCell());
                returnedBanknotes.put(banknote,this.cells.get(banknote).getAvailableAmountInCell());
            }else if(valueToget == extractedBanknotes*nominal){
                valueToget -= extractedBanknotes * nominal;
                this.cells.get(banknote).getBanknotes(extractedBanknotes);
                returnedBanknotes.put(banknote,extractedBanknotes);
            }
        }
        if (valueToget > 0) {
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
