package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Minesweeper extends Application {
    
    private static Stage window;
    
    @Override
    public void start(Stage stage) {
        window = stage;
        new MinesweeperBoard();
        window.setTitle("Minesweeper");
        window.setResizable(false);
        window.show();
    }
    
    public static Stage getStage() {
        return window;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
