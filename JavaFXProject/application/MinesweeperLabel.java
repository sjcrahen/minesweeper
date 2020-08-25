package application;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MinesweeperLabel extends Label {
    
    public MinesweeperLabel(String label) {
        if (label.equals("flag"))
            this.setGraphic(new ImageView(new Image("application/Flag.png", 14, 14, true, true)));
        else if (label.equals("mine"))
            this.setGraphic(new ImageView(new Image("application/Mine.png", 19, 19, false, true)));
        else if (label.equals("unexplodedMine"))
            this.setGraphic(new ImageView(new Image("application/UnexplodedMine.png", 19, 19, false, true)));
    }
    
    public MinesweeperLabel(int value) {
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
