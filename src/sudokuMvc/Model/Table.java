package sudokuMvc.Model;

import sudokuMvc.exceptions.ImmutableCellWriting;
import sudokuMvc.exceptions.InvalidColumnException;
import sudokuMvc.exceptions.InvalidRowException;
import sudokuMvc.exceptions.InvalidValueException;

public class Table {

    private int size;
    private Cell[][] grid;

    public void setSize(int size) {
        this.size = size;
        grid = new Cell[size][size];
    }

    public int getSize() {
        return size;
    }

    public void generateData(int[][] gridInt) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (gridInt[i][j] == 0) {
                    grid[i][j] = new MutableCell(0);
                }
                else {
                    grid[i][j] = new ImmutableCell(gridInt[i][j]);
                }
            }
        }
    }

    public String getInvalidCondition() {
        for (int i = 0; i < 4; i++) {
            int[] a = new int[]{ 0, 0, 0, 0 };
            for (int j = 0; j < 4; j++) {
                if (grid[i][j].value == 0) {
                    return "";
                }

                a[grid[i][j].value - 1]++;
                if (a[grid[i][j].value - 1] > 1) {
                    return String.format("Hàng %d bị trùng số!\n", i + 1);
                }
            }
        }

        for (int j = 0; j < 4; j++) {
            int[] a = new int[]{ 0, 0, 0, 0 };
            for (int i = 0; i < 4; i++) {
                a[grid[i][j].value - 1]++;
                if (a[grid[i][j].value - 1] > 1) {
                    return String.format("Cột %d bị trùng số!\n", j + 1);
                }
            }
        }

        int[][] a = new int[][]{
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 }
        };
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                a[2*i + j][grid[i][j].value - 1]++;
                if (a[2*i + j][grid[i][j].value - 1] > 1) {
                    return String.format("Vùng %d bị trùng số!\n", 2*i + j + 1);
                }
            }
        }

        return null;
    }

    public void setGrid(Choice choice) throws InvalidRowException, InvalidColumnException, InvalidValueException, ImmutableCellWriting {
        _validateRow(choice.row);
        _validateColumn(choice.column);
        _validateValue(choice.value);

        grid[choice.row-1][choice.column-1].setValue(choice);
    }

    private void _validateRow(int row) throws InvalidRowException {
        if (row <= 0 || row > size) {
            throw new InvalidRowException(0, size + 1);
        }
    }

    private void _validateColumn(int column) throws InvalidColumnException {
        if (column <= 0 || column > size) {
            throw new InvalidColumnException(0, size + 1);
        }
    }

    private void _validateValue(int value) throws InvalidValueException {
        if (value < 0 || value > size) {
            throw new InvalidValueException(0, size, value);
        }
    }

    @Override
    public String toString() {
        StringBuilder tableStr = new StringBuilder("\n");

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tableStr.append(grid[i][j]);
            }
            tableStr.append('\n');
        }

        return tableStr.toString();
    }
}
