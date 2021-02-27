package sudoku;

import sudoku.exceptions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Table {

    private final int size;
    private int[][] grid;
    private int[][] origin;

    private static final String RESET = "\033[0m";
    private static final String RED_BOLD = "\033[1;31m";

    public Table(int size) throws FileNotFoundException {
        this.size = size;
        try {
            getTable();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Không tìm thấy tệp dữ liệu!");
        }
    }

    private void getTable() throws FileNotFoundException {
        List<int[][]> tables = getAllTable();

        Random generator = new Random(System.currentTimeMillis());
        int index = generator.nextInt()%tables.size();
        int tableIndex = Math.abs(index);

        grid = new int[size][size];
        origin = new int[size][size];

        for (int i=0; i<size; i++){
            grid[i] = tables.get(tableIndex)[i].clone();
            origin[i] = tables.get(tableIndex)[i].clone();
        }
    }

    private List<int[][]> getAllTable() throws FileNotFoundException {
        String fileName = size + ".txt";
        File file = new File(getClass().getResource(fileName).getPath());

        final Scanner sc = new Scanner(file);
        List<int[][]> tables = new ArrayList<>();

        while (sc.hasNextLine()) {
            int[][] table = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    table[i][j] = Integer.parseInt(sc.next());
                }
            }
            tables.add(table);
            sc.nextLine();
        }

        return tables;
    }

    public String getInvalidCondition() {
        for (int i = 0; i < 4; i++) {
            int[] a = new int[]{ 0, 0, 0, 0 };
            for (int j = 0; j < 4; j++) {
                if (grid[i][j] == 0) {
                    return "";
                }

                a[grid[i][j] - 1]++;
                if (a[grid[i][j] - 1] > 1) {
                    return String.format("Hàng %d bị trùng số!\n", i + 1);
                }
            }
        }

        for (int j = 0; j < 4; j++) {
            int[] a = new int[]{ 0, 0, 0, 0 };
            for (int i = 0; i < 4; i++) {
                a[grid[i][j] - 1]++;
                if (a[grid[i][j] - 1] > 1) {
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
                a[2*i + j][grid[i][j] - 1]++;
                if (a[2*i + j][grid[i][j] - 1] > 1) {
                    return String.format("Vùng %d bị trùng số!\n", 2*i + j + 1);
                }
            }
        }

        return null;
    }

    public void write(Select select)
            throws InvalidColumnException, InvalidRowException, InvalidValueException, ImmutableCellWriting {

        validateRow(select.row);
        validateColumn(select.column);
        validateValue(select.row - 1, select.column - 1, select.value);

        _write(select);
    }

    private void _write(Select select) {
        grid[select.row - 1][select.column - 1] = select.value;
    }

    private void validateRow(int row) throws InvalidRowException {
        if (row <= 0 || row > size) {
            throw new InvalidRowException(0, size+1);
        }
    }

    private void validateColumn(int column) throws InvalidColumnException {
        if (column <= 0 || column > size) {
            throw new InvalidColumnException(0, size+1);
        }
    }

    private void validateValue(int row, int column, int value) throws InvalidValueException, ImmutableCellWriting {
        if (value < 0 || value > size) {
            throw new InvalidValueException(0, size, value);
        }

        if (!canChange(row, column)) {
            throw new ImmutableCellWriting(row+1, column+1);
        }
    }

    public String toString() {
        StringBuilder tableStr = new StringBuilder("\n");

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (isEmptyCell(i, j)) {
                    tableStr.append(" [-] ");
                }
                else {
                    if (canChange(i, j)) {
                        tableStr.append(" [").append(grid[i][j]).append("] ");
                    }
                    else {
                        tableStr.append(" [").append(RED_BOLD).append(grid[i][j]).append(RESET).append("] ");
                    }
                }
            }
            tableStr.append('\n');
        }

        return tableStr.toString();
    }

    private boolean canChange(int row, int column) {
        return origin[row][column] == 0;
    }

    private boolean isEmptyCell(int row, int column) {
        return grid[row][column] == 0;
    }
}
