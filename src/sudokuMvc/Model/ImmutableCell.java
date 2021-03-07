package sudokuMvc.Model;

import sudokuMvc.exceptions.ImmutableCellWriting;

public class ImmutableCell extends Cell {

    public ImmutableCell(int value) {
        super(value, false);
    }

    private static final String RESET = "\033[0m";
    private static final String RED_BOLD = "\033[1;31m";

    @Override
    public void setValue(Choice choice) throws ImmutableCellWriting {
        throw new ImmutableCellWriting(choice.row, choice.column);
    }

    @Override
    public String toString() {
        return String.format("[%s%d%s] ", RED_BOLD, value, RESET);
    }
}
