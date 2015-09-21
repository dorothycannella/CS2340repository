package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class ConfirmController {
    @FXML private Text p1;
    @FXML private Text p2;
    @FXML private Text p3;
    @FXML private Text p4;
    @FXML private Text name1;
    @FXML private Text name2;
    @FXML private Text name3;
    @FXML private Text name4;
    @FXML private Text race1;
    @FXML private Text race2;
    @FXML private Text race3;
    @FXML private Text race4;
    @FXML private Text diff;
    @FXML private Text nam;
    @FXML private Button next;

    @FXML protected void initialize() {
        String[] difficultyCodes = {"Beginner Level", "Standard Level",
                                    "Tournament Level"};
        String[] mapCodes = {"Standard Map", "Random Map"};
        p1.setFill(Paint.valueOf(Game.getColor(1)));
        p2.setFill(Paint.valueOf(Game.getColor(2)));
        p3.setFill(Paint.valueOf(Game.getColor(3)));
        p4.setFill(Paint.valueOf(Game.getColor(4)));
        name1.setText(Game.getName(1));
        name1.setFill(Paint.valueOf(Game.getColor(1)));
        name2.setText(Game.getName(2));
        name2.setFill(Paint.valueOf(Game.getColor(2)));
        name3.setText(Game.getName(3));
        name3.setFill(Paint.valueOf(Game.getColor(3)));
        name4.setText(Game.getName(4));
        name4.setFill(Paint.valueOf(Game.getColor(4)));
        race1.setText(Game.getRace(1));
        race1.setFill(Paint.valueOf(Game.getColor(1)));
        race2.setText(Game.getRace(2));
        race2.setFill(Paint.valueOf(Game.getColor(2)));
        race3.setText(Game.getRace(3));
        race3.setFill(Paint.valueOf(Game.getColor(3)));
        race4.setText(Game.getRace(4));
        race4.setFill(Paint.valueOf(Game.getColor(4)));
        diff.setText(difficultyCodes[Game.getDifficulty()]);
        nam.setText(mapCodes[Game.getMapType()]);
        next.setOnAction(e -> {
            try {
                Main.swapPane(getClass().getResource("map.fxml"));
            } catch (IOException ex) {
                System.err.println(ex);
            }
        });
    }
}
