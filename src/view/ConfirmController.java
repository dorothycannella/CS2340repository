package view;

import controller.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.io.IOException;

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
        Game game = Main.getGame();
        String[] difficultyCodes = {"Beginner Level", "Standard Level",
                                    "Tournament Level"};
        String[] mapCodes = {"Standard Map", "Random Map"};
        p1.setFill(Paint.valueOf(game.getData(1, 1)));
        p2.setFill(Paint.valueOf(game.getData(1, 2)));
        p3.setFill(Paint.valueOf(game.getData(1, 3)));
        p4.setFill(Paint.valueOf(game.getData(1, 4)));
        name1.setText(game.getData(2, 1));
        name1.setFill(Paint.valueOf(game.getData(1, 1)));
        name2.setText(game.getData(2, 2));
        name2.setFill(Paint.valueOf(game.getData(1, 2)));
        name3.setText(game.getData(2, 3));
        name3.setFill(Paint.valueOf(game.getData(1, 3)));
        name4.setText(game.getData(2, 4));
        name4.setFill(Paint.valueOf(game.getData(1, 4)));
        race1.setText(game.getData(0, 1));
        race1.setFill(Paint.valueOf(game.getData(1, 1)));
        race2.setText(game.getData(0, 2));
        race2.setFill(Paint.valueOf(game.getData(1, 2)));
        race3.setText(game.getData(0, 3));
        race3.setFill(Paint.valueOf(game.getData(1, 3)));
        race4.setText(game.getData(0, 4));
        race4.setFill(Paint.valueOf(game.getData(1, 4)));
        diff.setText(difficultyCodes[game.getDifficulty() - 1]);
        nam.setText(mapCodes[game.getMapType() - 1]);
        next.setOnAction(e -> {
            try {
                Main.swapPane(getClass().getResource("map.fxml"));
            } catch (IOException ex) {
                System.err.println("Missing Asset: map.fxml");
            }
        });
    }
}
