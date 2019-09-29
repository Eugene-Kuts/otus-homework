package ru.otus.hw06;

import ru.otus.hw06.exceptions.NotEnoughBanknotesException;
import ru.otus.hw06.exceptions.UnableToIssueRequestedAmountException;
import ru.otus.hw06.support.Banknote;
import ru.otus.hw06.support.BanknoteCellImpl;

import java.util.*;
import java.util.stream.Collectors;

public class ATMImpl implements ATM {

    final private Map<Banknote, BanknoteCellImpl> cells;

    //Создаем банкомант с ячейками каждого номинала по 10 банкнот (сумма 88500)
    public ATMImpl() {
        this.cells = new HashMap<>();
        for (Banknote banknote : Banknote.values()) {
            this.cells.put(banknote, new BanknoteCellImpl(banknote, 10));
        }
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
    public void putMoney(Banknote banknote, Integer value) { this.cells.get(banknote).putBanknotes(value); }

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
}
