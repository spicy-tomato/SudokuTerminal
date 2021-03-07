package sudokuMvc.Controller;

import sudokuMvc.View.GameView;
import sudokuMvc.Model.Table;
import sudokuMvc.Model.Choice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameController {

    private final GameView view;
    private final Table model;

    public GameController(GameView view, Table model) {
        this.view = view;
        this.model = model;
    }

    public void start() throws FileNotFoundException {
        try {
            model.setSize(4);
            model.generateData(generateTable(model.getSize()));

        } catch (FileNotFoundException e) {
            view.printError(e);
            throw e;
        }
    }

    public void loop() {
        String invalidCondition = "";
        do {
            if (!invalidCondition.equals("")) {
                System.out.println("\n--> " + invalidCondition);
            }

            System.out.println("\n-------------------------------");

            view.printTable(model);

            try {
                Choice choice = view.enterChoice();
                model.setGrid(choice);

            } catch (Exception e) {
                System.out.println("\n--> " + e.getMessage());
            }

            invalidCondition = model.getInvalidCondition();

        } while (invalidCondition != null);
    }

    public void end() {
        view.congratulate();
    }

    public boolean restart() {
        return view.restart();
    }

    private int[][] generateTable(int size) throws FileNotFoundException {
        List<int[][]> tables;

        try {
            tables = readData(size);
        } catch (FileNotFoundException | NullPointerException e) {
            throw new FileNotFoundException("Không tìm thấy tệp dữ liệu!");
        }

        Random generator = new Random(System.currentTimeMillis());
        int index = generator.nextInt()%tables.size();
        int tableIndex = Math.abs(index);

        return tables.get(tableIndex);
    }

    private List<int[][]> readData(int size) throws FileNotFoundException, NullPointerException {
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
}
