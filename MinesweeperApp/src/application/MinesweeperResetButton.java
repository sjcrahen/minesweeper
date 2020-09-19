package application;

import java.io.InputStream;

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
        setButtonLabel("smile");

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
            setButtonLabel("smile");
            Main.resetGame();
        });
    }
    
    public void setButtonLabel(String label) {
        this.getChildren().clear();
        if (label.equals("smile")) {
            InputStream isSmile = getClass().getClassLoader().getResourceAsStream("img/Smile.PNG");
            this.getChildren().add(new Label(null, new ImageView(new Image(isSmile, 26, 26, true, true))));
        } else if (label.equals("scared")) {
            InputStream isScared = getClass().getClassLoader().getResourceAsStream("img/scared.PNG");
            this.getChildren().add(new Label(null, new ImageView(new Image(isScared, 26, 26, true, true))));
        } else if (label.equals("done")) {
            InputStream isGameOver = getClass().getClassLoader().getResourceAsStream("img/done.PNG");
            this.getChildren().add(new Label(null, new ImageView(new Image(isGameOver, 26, 26, true, true))));
        }
    }    
}
