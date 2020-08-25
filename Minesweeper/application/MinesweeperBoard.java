package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class MinesweeperBoard {

    private static Timeline animation;
    private static boolean gameOn;
    private static int gameTime;
    private static StringProperty gameTimeProperty;
    private static FlowPane gameBoard;
    private static Scene scene;
    
    public MinesweeperBoard() {     
        animation = new Timeline(new KeyFrame(Duration.millis(1000), gameCounter));
        animation.setCycleCount(Timeline.INDEFINITE);
        
        gameOn = true;
        gameTime = 0;
        gameTimeProperty = new SimpleStringProperty(Integer.toString(gameTime));
        
        Minefield.buildNewMinefield();
        MinesweeperDashboard.buildNewMinesweeperDashboard();
        
        gameBoard = new FlowPane();
        gameBoard.setVgap(10);
        gameBoard.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
        gameBoard.setPrefWidth(624);
        gameBoard.setPrefHeight(408);
        gameBoard.getChildren().clear();
        gameBoard.getChildren().addAll(MinesweeperDashboard.getDashboard(), Minefield.getMinefield());
        FlowPane.setMargin(MinesweeperDashboard.getDashboard(), new Insets(10, 10, 0, 10));
        FlowPane.setMargin(Minefield.getMinefield(), new Insets(0, 10, 10, 10));
        
        scene = new Scene(gameBoard);
        MinesweeperMain.getStage().setScene(scene);
    }
    
    static boolean isGameOn() {
        return gameOn;
    }
    
    static void setGameOn(boolean gameStatus) {
        gameOn = gameStatus;
    }
    
    static StringProperty getGameTimeProperty() {
        return gameTimeProperty;
    }
    
    static Timeline getAnimation() {
        return animation;
    }
    
    static void doNewGame() {
        gameOn = true;
        gameTime = 0;
        gameTimeProperty.setValue(Integer.toString(gameTime));
        
        Minefield.reset();
        MinesweeperDashboard.reset();
        
        gameBoard.getChildren().clear();
        gameBoard.getChildren().addAll(MinesweeperDashboard.getDashboard(), Minefield.getMinefield());
          
        scene.setRoot(gameBoard);
        MinesweeperMain.getStage().setScene(scene);
    }
    
    static EventHandler<ActionEvent> gameCounter = e -> {
        gameTime++;
        gameTimeProperty.setValue(Integer.toString(gameTime));
    };

}
