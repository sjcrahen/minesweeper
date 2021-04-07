package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    
    private static Stage window;
    private static MinesweeperBoard board;
    private static boolean gameOn;
    
    @Override
    public void start(Stage stage) {
        window = stage;
        board = new MinesweeperBoard();
        gameOn = true;
        window.setScene(board.getScene());
        window.setTitle("Minesweeper");
        window.setResizable(false);
        window.show();
    }
    
    public static void resetGame() {
        board.resetBoard();
        gameOn = true;
        window.setScene(board.getScene());
    }
    
    static boolean gameIsOn() {
        return gameOn;
    }
    
    static void setGameOn(boolean gameStatus) {
        gameOn = gameStatus;
    }

    public static void main(String[] args) {
        launch(args);
    }
}