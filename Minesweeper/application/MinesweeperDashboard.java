package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MinesweeperDashboard {

    private static MinesweeperResetButton resetButton;
    private static StackPane mineCountPane;
    private static StackPane gameCountPane;
    private static Label mineCountLabel;
    private static Label gameCountLabel;
    private static HBox dashPane;
    
    private MinesweeperDashboard() {      

    }
    
    public static void buildNewMinesweeperDashboard() {        
        mineCountPane = new StackPane();
        mineCountPane.setPrefWidth(70);
        mineCountPane.setMinHeight(30);
        mineCountPane.setMaxHeight(30);
        mineCountPane.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(5), null)));
        mineCountLabel = new Label();
        mineCountLabel.textProperty().bind(Minefield.getNumberOfUnflaggedMinesProperty());
        mineCountLabel.setFont(Font.font(null, FontWeight.BOLD, 30.0));
        mineCountLabel.setTextFill(Color.RED);
        mineCountPane.getChildren().add(mineCountLabel);
        
        gameCountPane = new StackPane();
        gameCountPane.setPrefWidth(70);
        gameCountPane.setMinHeight(30);
        gameCountPane.setMaxHeight(30);
        gameCountPane.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(5), null)));
        gameCountLabel = new Label();
        gameCountLabel.textProperty().bind(MinesweeperBoard.getGameTimeProperty());
        gameCountLabel.setFont(Font.font(null, FontWeight.BOLD, 30.0));
        gameCountLabel.setTextFill(Color.RED);
        gameCountPane.getChildren().add(gameCountLabel);
        
        resetButton = new MinesweeperResetButton();
        resetButton.setButtonLabel(MinesweeperResetButton.SMILE); 
        
        dashPane = new HBox();
        dashPane.setMinWidth(Minefield.COLUMNS * 20 + 4);
        dashPane.setMaxWidth(Minefield.COLUMNS * 20 + 4);
        dashPane.setMinHeight(54);
        dashPane.setMaxHeight(54);
        dashPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
        dashPane.setBorder(new Border(new BorderStroke(
            Color.GRAY, Color.WHITE, Color.WHITE, Color.GRAY, 
            new BorderStrokeStyle(StrokeType.INSIDE, null, null, 10, 0, null), 
            new BorderStrokeStyle(StrokeType.INSIDE, null, null, 10, 0, null), 
            new BorderStrokeStyle(StrokeType.INSIDE, null, null, 10, 0, null),
            new BorderStrokeStyle(StrokeType.INSIDE, null, null, 10, 0, null),
            null, new BorderWidths(2), null)));
        dashPane.getChildren().addAll(mineCountPane, resetButton, gameCountPane);
        dashPane.setAlignment(Pos.CENTER);
        HBox.setMargin(mineCountPane, new Insets(10, 195, 10, 10));
        HBox.setMargin(resetButton, new Insets(10));
        HBox.setMargin(gameCountPane, new Insets(10, 10, 10, 195));
    }
    
    public static void reset() {
        resetButton.setButtonLabel(MinesweeperResetButton.SMILE);        
    }
    
    public static HBox getDashboard() {
        return dashPane;
    }
    
    public static MinesweeperResetButton getResetButton() {
        return resetButton;
    }
}
