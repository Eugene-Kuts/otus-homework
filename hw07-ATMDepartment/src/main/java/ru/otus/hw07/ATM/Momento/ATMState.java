package ru.otus.hw07.ATM.Momento;

import lombok.Getter;
import ru.otus.hw07.ATM.support.Banknote;
import ru.otus.hw07.ATM.support.BanknoteCell;

import java.util.HashMap;
import java.util.Map;

/**
 * Memento object.
 */
@Getter
public class ATMState {

    private Map<Banknote,BanknoteCell> cellsState;

    public ATMState(Map<Banknote,BanknoteCell> cells) {
        Map<Banknote, BanknoteCell> tempCellsState = new HashMap<>();
        cells.forEach((banknote, banknoteCell) -> {tempCellsState.put(banknote, new BanknoteCell(banknote,banknoteCell.getAvailableAmountInCell()));});
        this.cellsState = tempCellsState;
    }
}
