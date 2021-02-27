package sudoku.exceptions;

public class ImmutableCellWriting extends Exception {
    private final int row;
    private final int column;

    public ImmutableCellWriting(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public String getMessage() {
        return String.format("Không thể ghi lên ô (%d, %d)", row, column);
    }
}
