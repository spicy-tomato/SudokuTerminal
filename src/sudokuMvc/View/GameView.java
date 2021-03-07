package sudokuMvc.View;

import sudokuMvc.Model.Choice;
import sudokuMvc.Model.Table;

import java.util.Scanner;

public class GameView {

    final Scanner sc = new Scanner(System.in);

    public void printTable(Table table) {
        System.out.println(table);
    }

    public Choice enterChoice() throws NumberFormatException {
        String row, column, value;
        System.out.print("Hãy nhập hàng: ");
        row = sc.next();

        System.out.print("Hãy nhập cột: ");
        column = sc.next();

        System.out.print("Hãy nhập giá trị: ");
        value = sc.next();

        return new Choice(row, column, value);
    }

    public void printError(Exception e) {
        System.out.println("\n--> " + e.getMessage());
    }

    public void congratulate() {
        System.out.println("\n\n----------------------------\n");
        System.out.println("XIN CHÚC MỪNG! BẠN ĐÃ CHIẾN THẮNG");
    }

    public boolean restart() {
        System.out.println("Bạn có muốn tiếp tục không?");
        int i = sc.nextInt();
        return i == 1;
    }
}
