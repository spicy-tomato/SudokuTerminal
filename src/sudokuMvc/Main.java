package sudokuMvc;

import sudokuMvc.Controller.GameController;
import sudokuMvc.View.GameView;
import sudokuMvc.Model.Table;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        GameView view;
        Table model;
        GameController controller;

        do {
            view = new GameView();
            model = new Table();
            controller = new GameController(view, model);

            try {
                controller.start();
            } catch (FileNotFoundException e) {
                return;
            }

            controller.loop();
            controller.end();

        } while (controller.restart());
    }
}
