package ru.otus.hw07.ATM.Momento;

import lombok.Getter;
import ru.otus.hw07.ATM.support.Banknote;
import ru.otus.hw07.ATM.support.BanknoteCellImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Memento object.
 */
@Getter
public class ATMState {

    private Map<Banknote, BanknoteCellImpl> cellsState;

    public ATMState(Map<Banknote, BanknoteCellImpl> cells) {
        Map<Banknote, BanknoteCellImpl> tempCellsState = new HashMap<>();
        cells.forEach((banknote, banknoteCell) -> {tempCellsState.put(banknote, new BanknoteCellImpl(banknote,banknoteCell.getAvailableAmountInCell()));});
        this.cellsState = tempCellsState;
    }
}
