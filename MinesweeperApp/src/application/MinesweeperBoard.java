package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;

public class MinesweeperBoard {

    private FlowPane board;
    private Scene scene;
    
    public MinesweeperBoard() {        
        Minefield.buildNewMinefield();
        MinesweeperDashboard.buildNewMinesweeperDashboard();
        
        board = new FlowPane();
        board.setVgap(10);
        board.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
        board.setPrefWidth(624);
        board.setPrefHeight(408);
        
        addMinefieldAndDashboardToBoard();

        scene = new Scene(board);
    }
    
    public void resetBoard() {
        Minefield.resetMinefield();
        MinesweeperDashboard.resetDashboard();
        addMinefieldAndDashboardToBoard();
          
        scene.setRoot(board);
    }
    
    private void addMinefieldAndDashboardToBoard() {
        board.getChildren().clear();
        board.getChildren().addAll(MinesweeperDashboard.getDashboard(), Minefield.getMinefield());
        FlowPane.setMargin(MinesweeperDashboard.getDashboard(), new Insets(10, 10, 0, 10));
        FlowPane.setMargin(Minefield.getMinefield(), new Insets(0, 10, 10, 10));
    }
    
    public Scene getScene() {
        return scene;
    }
}
