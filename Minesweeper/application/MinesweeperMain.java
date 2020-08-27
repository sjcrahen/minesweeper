package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class MinesweeperMain extends Application {
    
    private static Stage window;
    private static MinesweeperBoard board;
    
    @Override
    public void start(Stage stage) {
        window = stage;
        board = new MinesweeperBoard();
        window.setScene(board.getScene());
        window.setTitle("Minesweeper");
        window.setResizable(false);
        window.show();
    }
    
    public static void resetGame() {
        board.resetBoard();
        window.setScene(board.getScene());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
