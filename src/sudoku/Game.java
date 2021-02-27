package sudoku;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game {

    Scanner sc;
    Table table;

    public Game() {
        sc = new Scanner(System.in);

        do {
            try {
                table = new Table(4);
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
                return;
            }

            GameLoop();

            congratulate();

        } while (continueGame());
    }

    private void GameLoop() {
        String invalidCondition = "";
        do {
            if (!invalidCondition.equals("")) {
                System.out.println("\n--> " + invalidCondition);
            }

            printTable();

            try {
                table.write(askForChoose());

            } catch (Exception e) {
                System.out.println("\n--> " + e.getMessage());
            }

            invalidCondition = table.getInvalidCondition();

        } while (invalidCondition != null);
    }

    private void printTable() {
        System.out.println("\n------------------------------------");
        System.out.println("\nBảng hiện tại:");
        System.out.println(table);
    }

    private Select askForChoose() throws NumberFormatException {
        int row, column, value;

        System.out.print("Hãy nhập hàng: ");
        row = tryParseInt(sc.next());

        System.out.print("Hãy nhập cột: ");
        column = tryParseInt(sc.next());

        System.out.print("Hãy nhập giá trị: ");
        value = tryParseInt(sc.next());

        return new Select(row, column, value);
    }

    private void congratulate() {
        System.out.println("XIN CHÚC MỪNG! BẠN ĐÃ CHIẾN THẮNG");
    }

    private boolean continueGame() {
        System.out.println("Bạn có muốn tiếp tục không?");
        int i = sc.nextInt();
        return i == 1;
    }

    private int tryParseInt(String str) throws NumberFormatException {
        try {
            return Integer.parseInt(str);

        } catch (NumberFormatException e) {
            throw new NumberFormatException("Ký tự vừa nhập không phải số!");
        }
    }
}
