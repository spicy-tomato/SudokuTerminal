package sudokuMvc.Model;

import sudokuMvc.exceptions.ImmutableCellWriting;

abstract class Cell {

    protected int value;
    protected final boolean isMutable;

    public Cell(int value, boolean isMutable) {
        this.value = value;
        this.isMutable = isMutable;
    }

    public abstract void setValue(Choice choice) throws ImmutableCellWriting;
}

