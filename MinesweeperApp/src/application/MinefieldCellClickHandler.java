package application;

import java.util.List;

import javafx.animation.Animation;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;

public class MinefieldCellClickHandler implements EventHandler<MouseEvent> {

    @Override
    public void handle(MouseEvent event) {
        if (Main.gameIsOn()) {
            MinefieldCell clickedCell = (MinefieldCell)event.getSource();
            if (event.getButton() == MouseButton.PRIMARY) {
                startAnimationIfStopped();
                if(!clickedCell.isRevealed() && !clickedCell.isflagged()) {
                    MinesweeperDashboard.getResetButton().setButtonLabel("scared");
                }
                revealThis(clickedCell);
            }
            else if (event.getButton() == MouseButton.SECONDARY) {
                if (!clickedCell.isRevealed()) {
                    if (clickedCell.getChildren().isEmpty()) {
                        setFlagOnThis(clickedCell); 
                    }
                    else {
                        removeFlagFromThis(clickedCell);
                    }
                }
            }    
        }
    }

    private void startAnimationIfStopped() {
        if (MinesweeperDashboard.getGameClock().getStatus() == Animation.Status.STOPPED)
            MinesweeperDashboard.getGameClock().play();
    }

    private void revealThis(MinefieldCell cell) {
        if(!cell.isRevealed() && !cell.isflagged()) {
            cell.setRevealed(true);
            if (cell.getValue() == -1) {
                displayMineOnThis(cell);
                stopGame();
                revealAllUnexplodedMines();
            }
            else if (cell.getValue() == 0) {
                displayThisEmpty(cell);
                revealCellsAdjacentToThis(cell);
            }
            else {
                displayAdjacencyValueOfThis(cell);
            }
        }
    }

    private void setFlagOnThis(MinefieldCell clickedCell) {
        clickedCell.getChildren().add(new MinesweeperCellLabel("flag"));
        clickedCell.setFlagged(true);
        Minefield.decrementNumberOfRemainingMines();
        Minefield.getRemainingMinesStringProperty().setValue(Minefield.getNumberOfRemainingMinesAsString());
    }

    private void removeFlagFromThis(MinefieldCell clickedCell) {
        clickedCell.getChildren().clear();
        clickedCell.setFlagged(false);
        Minefield.incrementNumberOfRemainingMines();
        Minefield.getRemainingMinesStringProperty().setValue(Minefield.getNumberOfRemainingMinesAsString());
    }
    
    private void displayMineOnThis(MinefieldCell cell) {
        cell.setBorder(new Border(new BorderStroke(
                Color.GRAY, 
                new BorderStrokeStyle(StrokeType.INSIDE, StrokeLineJoin.BEVEL, null, 10, 0, null), 
                null, new BorderWidths(1))));
        cell.getChildren().add(new MinesweeperCellLabel("mine"));
    }
    
    private void stopGame() {
        Main.setGameOn(false);
        MinesweeperDashboard.getGameClock().stop();
        MinesweeperDashboard.getResetButton().setButtonLabel("done");
    }
    
    private void revealAllUnexplodedMines() {
        for (int row = 0; row < Minefield.ROWS; row++) {
            for (int col = 0; col < Minefield.COLUMNS; col++) {
                MinefieldCell cell = Minefield.getCell(row, col);
                if (!cell.isRevealed() && !cell.isflagged() && cell.getValue() == -1) {
                    displayUnexplodedMineOnThis(cell);
                }
            }
        }
    }
    
    private void displayThisEmpty(MinefieldCell cell) {
        cell.setBorder(new Border(new BorderStroke(
                Color.GRAY, 
                new BorderStrokeStyle(StrokeType.INSIDE, StrokeLineJoin.BEVEL, null, 10, 0, null), 
                null, new BorderWidths(1))));
    }
    
    private void revealCellsAdjacentToThis(MinefieldCell cell) {
        List<MinefieldCell> adjacentCellsList = AdjacentCellsListBuilder.build(Minefield.getCellsMatrix(), cell.getRow(), cell.getCol());
        for (MinefieldCell adjacentCell : adjacentCellsList) {
            revealThis(adjacentCell);
        }
    }
    
    private void displayAdjacencyValueOfThis(MinefieldCell cell) {
        cell.setBorder(new Border(new BorderStroke(
                Color.GRAY, 
                new BorderStrokeStyle(StrokeType.INSIDE, StrokeLineJoin.BEVEL, null, 10, 0, null), 
                null, new BorderWidths(1))));
        cell.getChildren().add(new MinesweeperCellLabel(cell.getValue()));
    }
    
    private void displayUnexplodedMineOnThis(MinefieldCell cell) {
        cell.setRevealed(true);
        cell.setBorder(new Border(new BorderStroke(
                Color.GRAY, 
                new BorderStrokeStyle(StrokeType.INSIDE, StrokeLineJoin.BEVEL, null, 10, 0, null), 
                null, new BorderWidths(1))));
        cell.getChildren().add(new MinesweeperCellLabel("unexplodedMine"));
    }
}
