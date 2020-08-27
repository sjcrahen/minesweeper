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
    private static MinefieldCell[][] cellsMatrix = new MinefieldCell[ROWS][COLUMNS];
    private static StringProperty numberOfUnflaggedMinesStringProperty = new SimpleStringProperty();
    private static TilePane minefield = new TilePane();
    private static StackPane minefieldStackPane = new StackPane();
    private static int numberOfMines;
    private static int numberOfUnflaggedMines;
    
    private Minefield() {}
    
    public static void buildNewMinefield() {
        placeMines();
        numberOfUnflaggedMines = numberOfMines;
        numberOfUnflaggedMinesStringProperty.setValue(zeroPaddedNumberString(numberOfUnflaggedMines));
        setValuesForNonMineCells();
        
        addCellsToMinefield();
        
        styleMinefieldStackPane();
        placeMinefieldInStackPane();
    }
    
    public static Pane getMinefield() {
        return minefieldStackPane;
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
    
    private static void addCellsToMinefield() {
        minefield.getChildren().clear();
        for (int row = 0; row < cellsMatrix.length; row++) {
            minefield.getChildren().addAll(cellsMatrix[row]);
        }
    }
    
    private static void styleMinefieldStackPane() {
        minefieldStackPane.setMinWidth(COLUMNS * cellsMatrix[0][0].getMinWidth() + 4);
        minefieldStackPane.setMaxWidth(COLUMNS * cellsMatrix[0][0].getMinWidth() + 4);
        minefieldStackPane.setMinHeight(ROWS * cellsMatrix[0][0].getMinHeight() + 4);
        minefieldStackPane.setMaxHeight(ROWS * cellsMatrix[0][0].getMinHeight() + 4);
        minefieldStackPane.setBorder(new Border(new BorderStroke(
            Color.GRAY, Color.WHITE, Color.WHITE, Color.GRAY, 
            new BorderStrokeStyle(StrokeType.INSIDE, null, null, 10, 0, null), 
            new BorderStrokeStyle(StrokeType.INSIDE, null, null, 10, 0, null), 
            new BorderStrokeStyle(StrokeType.INSIDE, null, null, 10, 0, null),
            new BorderStrokeStyle(StrokeType.INSIDE, null, null, 10, 0, null),
            null, new BorderWidths(2), null)));
    }
    
    private static void placeMinefieldInStackPane() {
        minefieldStackPane.getChildren().clear();
        minefieldStackPane.getChildren().add(minefield);
    }
    
    private static String zeroPaddedNumberString(int num) {
        StringBuffer sb = new StringBuffer();
        if (num > 99) sb.append(num);
        else if (num > 9) sb.append("0" + num);
        else sb.append("00" + num);
        return sb.toString();
    }
    
    public static String getNumberOfUnflaggedMinesAsString() {
        return zeroPaddedNumberString(numberOfUnflaggedMines);
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
    
    public static StringProperty getNumberOfUnflaggedMinesStringProperty() {
        return numberOfUnflaggedMinesStringProperty;
    }
    
    public static MinefieldCell[][] getCellsMatrix() {
        return cellsMatrix;
    }
    
    public static MinefieldCell getCell(int row, int col) {
        return cellsMatrix[row][col];
    }
}
