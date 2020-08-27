package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.util.Duration;

public class MinesweeperDashboard {

    private static Timeline animation;
    private static int gameTime;
    private static StringProperty gameTimeProperty;
    private static MinesweeperResetButton resetButton;
    private static StackPane mineCountPane;
    private static StackPane gameCountPane;
    private static Label mineCountLabel;
    private static Label gameCountLabel;
    private static HBox dashPane;
    
    private MinesweeperDashboard() {}
    
    public static void buildNewMinesweeperDashboard() {        
        animation = new Timeline(new KeyFrame(Duration.millis(1000), gameCounter));
        animation.setCycleCount(Timeline.INDEFINITE);
        gameTimeProperty = new SimpleStringProperty();
        initGameTimer();
        
        mineCountPane = new StackPane();
        mineCountPane.setPrefWidth(70);
        mineCountPane.setMinHeight(30);
        mineCountPane.setMaxHeight(30);
        mineCountPane.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(5), null)));
        mineCountLabel = new Label();
        mineCountLabel.textProperty().bind(Minefield.getNumberOfUnflaggedMinesStringProperty());
        mineCountLabel.setFont(Font.font(null, FontWeight.BOLD, 30.0));
        mineCountLabel.setTextFill(Color.RED);
        mineCountPane.getChildren().add(mineCountLabel);
        
        gameCountPane = new StackPane();
        gameCountPane.setPrefWidth(70);
        gameCountPane.setMinHeight(30);
        gameCountPane.setMaxHeight(30);
        gameCountPane.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(5), null)));
        gameCountLabel = new Label();
        gameCountLabel.textProperty().bind(gameTimeProperty);
        gameCountLabel.setFont(Font.font(null, FontWeight.BOLD, 30.0));
        gameCountLabel.setTextFill(Color.RED);
        gameCountPane.getChildren().add(gameCountLabel);
        
        resetButton = new MinesweeperResetButton();
        
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
    
    public static void resetDashboard() {
        resetButton.setButtonLabel(MinesweeperResetButton.SMILE);
        initGameTimer();
    }
    
    private static void initGameTimer() {
        gameTime = 0;
        gameTimeProperty.setValue(zeroPaddedNumberString(gameTime));
    }
    
    static StringProperty getGameTimeProperty() {
        return gameTimeProperty;
    }
    
    static Timeline getAnimation() {
        return animation;
    }
    
    static HBox getDashboard() {
        return dashPane;
    }
    
    static MinesweeperResetButton getResetButton() {
        return resetButton;
    }
    
    private static String zeroPaddedNumberString(int num) {
        StringBuffer sb = new StringBuffer();
        if (num > 99) sb.append(num);
        else if (num > 9) sb.append("0" + num);
        else sb.append("00" + num);
        return sb.toString();
    }
    
    static EventHandler<ActionEvent> gameCounter = e -> {
        gameTime++;
        gameTimeProperty.setValue(zeroPaddedNumberString(gameTime));
    };
}
