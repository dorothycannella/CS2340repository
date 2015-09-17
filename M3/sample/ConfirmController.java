package sample;

import javafx.fxml.FXML;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
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

    @FXML protected void initialize() {
        String[] difficultyCodes = {"Beginner Level", "Standard Level",
                                    "Tournament Level"};
        String[] mapCodes = {"Standard Map", "Random Map"};
        MULEPerson[] players = Game.getPlayers();
        p1.setFill(Paint.valueOf(players[0].getColor()));
        p2.setFill(Paint.valueOf(players[1].getColor()));
        p3.setFill(Paint.valueOf(players[2].getColor()));
        p4.setFill(Paint.valueOf(players[3].getColor()));
        name1.setText(players[0].getName());
        name1.setFill(Paint.valueOf(players[0].getColor()));
        name2.setText(players[1].getName());
        name2.setFill(Paint.valueOf(players[1].getColor()));
        name3.setText(players[2].getName());
        name3.setFill(Paint.valueOf(players[2].getColor()));
        name4.setText(players[3].getName());
        name4.setFill(Paint.valueOf(players[3].getColor()));
        race1.setText(players[0].getRace());
        race1.setFill(Paint.valueOf(players[0].getColor()));
        race2.setText(players[1].getRace());
        race2.setFill(Paint.valueOf(players[1].getColor()));
        race3.setText(players[2].getRace());
        race3.setFill(Paint.valueOf(players[2].getColor()));
        race4.setText(players[3].getRace());
        race4.setFill(Paint.valueOf(players[3].getColor()));
        diff.setText(difficultyCodes[Game.getDifficulty()]);
        nam.setText(mapCodes[Game.getMap()]);
    }
}
