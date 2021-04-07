package application;

import java.util.ArrayList;

public class AdjacentCellsListBuilder {
    
    private AdjacentCellsListBuilder() {} // prevent instantiation
    
    public static ArrayList<MinefieldCell> build(MinefieldCell[][] cellsMatrix, int row, int col) {
        ArrayList<MinefieldCell> cellsList = new ArrayList<>();
        if (row > 0) {
            cellsList.add(cellsMatrix[row - 1][col]);
            if (col > 0) {
                cellsList.add(cellsMatrix[row - 1][col - 1]);
            }
            if (col < Minefield.COLUMNS - 1) {
                cellsList.add(cellsMatrix[row - 1][col + 1]);
            }
        }
        if (row < Minefield.ROWS - 1) {
            cellsList.add(cellsMatrix[row + 1][col]);
            if (col > 0) {
                cellsList.add(cellsMatrix[row + 1][col - 1]);
            }
            if (col < Minefield.COLUMNS - 1) {
                cellsList.add(cellsMatrix[row + 1][col + 1]);
            }
        }
        if (col > 0) {
            cellsList.add(cellsMatrix[row][col - 1]);
        }
        if (col < Minefield.COLUMNS - 1) {
            cellsList.add(cellsMatrix[row][col + 1]);
        }
        return cellsList;
    }

}
