package application;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

public class MinesweeperResetButton extends StackPane {
    
    public static final Label SMILE = new Label(null, new ImageView(new Image("application/smile.png", 26, 26, true, true)));
    public static final Label SCARED = new Label(null, new ImageView(new Image("application/scared.png", 26, 26, true, true)));
    public static final Label GAME_OVER = new Label(null, new ImageView(new Image("application/done.png", 26, 26, true, true)));
    
    public MinesweeperResetButton() {
        setMinWidth(30);
        setMaxHeight(30);
        setMinHeight(30);
        setMaxHeight(30);
        setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
        setBorder(new Border(new BorderStroke(
            Color.WHITE, Color.GRAY, Color.GRAY, Color.WHITE, 
            new BorderStrokeStyle(StrokeType.INSIDE, StrokeLineJoin.BEVEL, null, 10, 0, null), 
            new BorderStrokeStyle(StrokeType.INSIDE, StrokeLineJoin.BEVEL, null, 10, 0, null), 
            new BorderStrokeStyle(StrokeType.INSIDE, StrokeLineJoin.BEVEL, null, 10, 0, null),
            new BorderStrokeStyle(StrokeType.INSIDE, StrokeLineJoin.BEVEL, null, 10, 0, null),
            null, new BorderWidths(2), null)));
        setButtonLabel(SMILE);

        setOnMousePressed(e -> {
            setBorder(new Border(new BorderStroke(
                Color.GRAY, 
                new BorderStrokeStyle(StrokeType.INSIDE, StrokeLineJoin.BEVEL, null, 10, 0, null), 
                null, new BorderWidths(2, 1, 1, 2))));
            MinesweeperDashboard.getGameClock().stop();
        });
        
        setOnMouseReleased(e -> {
            setBorder(new Border(new BorderStroke(
                Color.WHITE, Color.GRAY, Color.GRAY, Color.WHITE, 
                new BorderStrokeStyle(StrokeType.INSIDE, StrokeLineJoin.BEVEL, null, 10, 0, null), 
                new BorderStrokeStyle(StrokeType.INSIDE, StrokeLineJoin.BEVEL, null, 10, 0, null), 
                new BorderStrokeStyle(StrokeType.INSIDE, StrokeLineJoin.BEVEL, null, 10, 0, null),
                new BorderStrokeStyle(StrokeType.INSIDE, StrokeLineJoin.BEVEL, null, 10, 0, null),
                null, new BorderWidths(2), null)));
            setButtonLabel(SMILE);
            Minesweeper.resetGame();
        });
    }
    
    public void setButtonLabel(Label label) {
        getChildren().clear();
        getChildren().add(label);
    }
}
