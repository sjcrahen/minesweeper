package application;

import java.io.InputStream;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MinesweeperCellLabel extends Label {
    
    public MinesweeperCellLabel(String label) {
        if (label.equals("flag")) {
            InputStream isFlag = getClass().getClassLoader().getResourceAsStream("img/Flag.PNG");
            this.setGraphic(new ImageView(new Image(isFlag, 14, 14, true, true)));
        } else if (label.equals("mine")) {
            InputStream isMine = getClass().getClassLoader().getResourceAsStream("img/Mine.PNG");
            this.setGraphic(new ImageView(new Image(isMine, 19, 19, false, true)));
        } else if (label.equals("unexplodedMine")) {
            InputStream isUnexplodedMine = getClass().getClassLoader().getResourceAsStream("img/UnexplodedMine.PNG");
            this.setGraphic(new ImageView(new Image(isUnexplodedMine, 19, 19, false, true)));
        }
    }
    
    public MinesweeperCellLabel(int value) {
        this.setText(Integer.toString(value));
        this.setFont(Font.font(null, FontWeight.BOLD, 14.0));
        setFontColor();
    }
    
    private void setFontColor() {
        switch(this.getText()) {
            case "1":
                this.setTextFill(Color.BLUE); break;
            case "2":
                this.setTextFill(Color.GREEN); break;
            case "3":
                this.setTextFill(Color.RED); break;
            case "4":
                this.setTextFill(Color.DARKBLUE); break;
            case "5":
                this.setTextFill(Color.DARKRED); break;
            case "6":
                this.setTextFill(Color.ORANGE); break;
            default:
                this.setTextFill(Color.BLACK); break;
        }
    }

}
