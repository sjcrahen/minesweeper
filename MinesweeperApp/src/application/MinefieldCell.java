package application;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;

public class MinefieldCell extends StackPane {
    
    private int row;
    private int col;
    private int value;
    private boolean revealed = false;
    private boolean flagged = false;    
    
    public MinefieldCell(int rowIndex, int colIndex) {
        row = rowIndex;
        col = colIndex;

        setMinWidth(20);
        setMaxWidth(20);
        setMinHeight(20);
        setMaxHeight(20);
        
        setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
        setBorder(new Border(new BorderStroke(
            Color.WHITE, Color.GRAY, Color.GRAY, Color.WHITE, 
            new BorderStrokeStyle(StrokeType.INSIDE, StrokeLineJoin.BEVEL, null, USE_PREF_SIZE, USE_PREF_SIZE, null), 
            new BorderStrokeStyle(StrokeType.INSIDE, StrokeLineJoin.BEVEL, null, USE_PREF_SIZE, USE_PREF_SIZE, null), 
            new BorderStrokeStyle(StrokeType.INSIDE, StrokeLineJoin.BEVEL, null, USE_PREF_SIZE, USE_PREF_SIZE, null),
            new BorderStrokeStyle(StrokeType.INSIDE, StrokeLineJoin.BEVEL, null, USE_PREF_SIZE, USE_PREF_SIZE, null),
            null, new BorderWidths(2), null)));

        setOnMousePressed(new MinefieldCellClickHandler());
        setOnMouseReleased(e -> {
            if(Main.gameIsOn())
                MinesweeperDashboard.getResetButton().setButtonLabel("smile");
        });
    }

    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    public void setRevealed(boolean val) {
        revealed = val;
    }
    
    public boolean isRevealed() {
        return revealed;
    }
    
    public void setFlagged(boolean val) {
        flagged = val;
    }
    
    public boolean isflagged() {        
        return flagged;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    
    public String toString() {
        return "( " + getRow() + ", " + getCol() + ")";
    }
}

