package application;

import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;

public class Minefield {
    
    public static final int ROWS = 16;
    public static final int COLUMNS = 30;
    private static MinefieldCell[][] cellsMatrix;
    private static int numberOfMines;
    private static int numberOfUnflaggedMines;
    private static StringProperty numberOfUnflaggedMinesProperty;
    private static TilePane minefieldGrid;
    private static StackPane minefieldPane;
    
    private Minefield() {

    }
    
    public static void buildNewMinefield() {
        cellsMatrix = new MinefieldCell[ROWS][COLUMNS];
        placeMines();
        numberOfUnflaggedMines = numberOfMines;
        numberOfUnflaggedMinesProperty = new SimpleStringProperty(Integer.toString(numberOfUnflaggedMines));
        setValuesForNonMineCells();
        
        minefieldGrid = new TilePane();
        for (int row = 0; row < cellsMatrix.length; row++) {
            minefieldGrid.getChildren().addAll(cellsMatrix[row]);
        }

        minefieldPane = new StackPane(minefieldGrid);
        minefieldPane.setMinWidth(COLUMNS * cellsMatrix[0][0].getMinWidth() + 4);
        minefieldPane.setMaxWidth(COLUMNS * cellsMatrix[0][0].getMinWidth() + 4);
        minefieldPane.setMinHeight(ROWS * cellsMatrix[0][0].getMinHeight() + 4);
        minefieldPane.setMaxHeight(ROWS * cellsMatrix[0][0].getMinHeight() + 4);
        minefieldPane.setBorder(new Border(new BorderStroke(
            Color.GRAY, Color.WHITE, Color.WHITE, Color.GRAY, 
            new BorderStrokeStyle(StrokeType.INSIDE, null, null, 10, 0, null), 
            new BorderStrokeStyle(StrokeType.INSIDE, null, null, 10, 0, null), 
            new BorderStrokeStyle(StrokeType.INSIDE, null, null, 10, 0, null),
            new BorderStrokeStyle(StrokeType.INSIDE, null, null, 10, 0, null),
            null, new BorderWidths(2), null)));
    }
    
    public static void reset() {
        placeMines();
        setValuesForNonMineCells();
        
        minefieldGrid.getChildren().clear();
        for (int row = 0; row < cellsMatrix.length; row++) {
            minefieldGrid.getChildren().addAll(cellsMatrix[row]);
        }
        minefieldPane.getChildren().clear();
        minefieldPane.getChildren().add(minefieldGrid);
        
        numberOfUnflaggedMines = numberOfMines;
        numberOfUnflaggedMinesProperty.setValue(Integer.toString(numberOfUnflaggedMines));
    }
    
    public static Pane getMinefield() {
        return minefieldPane;
    }
    
    private static void placeMines() {
        numberOfMines = 0;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                cellsMatrix[row][col] = new MinefieldCell(row, col);
                int value = 0;
                if (Math.random() < 0.2) {
                    numberOfMines++;
                    value = -1;
                }
                cellsMatrix[row][col].setValue(value);
            }
        }
    }
    
    private static void setValuesForNonMineCells() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if (isMine(cellsMatrix[row][col].getValue()))
                    continue;
                else
                    cellsMatrix[row][col].setValue(countAdjacentMines(row, col));
            }
        }
    }
    
    private static boolean isMine(int i) {
        return i == -1;
    }
    
    private static int countAdjacentMines(int row, int col) {
        int numberOfAdjacentMines = 0;
        List<MinefieldCell> adjacentCells = AdjacentCellsListBuilder.build(cellsMatrix, row, col);
        for (MinefieldCell cell : adjacentCells) {
            if (cell.getValue() == -1)
                numberOfAdjacentMines++;
        }
        return numberOfAdjacentMines;
    }
    
    public static int getNumberOfUnflaggedMines() {
        return numberOfUnflaggedMines;
    }
    
    public static void decrementNumberOfUnflaggedMines() {
        numberOfUnflaggedMines--;
    }
    
    public static void incrementNumberOfUnflaggedMines() {
        numberOfUnflaggedMines++;
    }
    
    public static StringProperty getNumberOfUnflaggedMinesProperty() {
        return numberOfUnflaggedMinesProperty;
    }
    
    public static MinefieldCell[][] getCellsMatrix() {
        return cellsMatrix;
    }
    
    public static MinefieldCell getCell(int row, int col) {
        return cellsMatrix[row][col];
    }
}
