package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;

public class MinesweeperBoard {

    private static boolean gameOn;
    private static FlowPane board;
    private static Scene scene;
    
    public MinesweeperBoard() {
        
        gameOn = true;
        
        Minefield.buildNewMinefield();
        MinesweeperDashboard.buildNewMinesweeperDashboard();
        
        board = new FlowPane();
        board.setVgap(10);
        board.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
        board.setPrefWidth(624);
        board.setPrefHeight(408);
        
        board.getChildren().addAll(MinesweeperDashboard.getDashboard(), Minefield.getMinefield());
        FlowPane.setMargin(MinesweeperDashboard.getDashboard(), new Insets(10, 10, 0, 10));
        FlowPane.setMargin(Minefield.getMinefield(), new Insets(0, 10, 10, 10));
        
        scene = new Scene(board);
    }
    
    public void resetBoard() {
        gameOn = true;
        
        Minefield.buildNewMinefield();
        MinesweeperDashboard.resetDashboard();
        
        board.getChildren().remove(1);
        board.getChildren().add(Minefield.getMinefield());
        FlowPane.setMargin(Minefield.getMinefield(), new Insets(0, 10, 10, 10));
          
        scene.setRoot(board);
    }
    
    public Scene getScene() {
        return scene;
    }
    
    static boolean gameIsOn() {
        return gameOn;
    }
    
    static void setGameOn(boolean gameStatus) {
        gameOn = gameStatus;
    }
}
