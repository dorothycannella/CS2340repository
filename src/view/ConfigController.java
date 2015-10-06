package view;

import controller.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;

public class ConfigController {
    @FXML private ToggleGroup difficulty;
    @FXML private RadioButton difficultyBeginner;
    @FXML private RadioButton difficultyStandard;
    @FXML private RadioButton difficultyTournament;
    @FXML private ToggleGroup mapType;
    @FXML private RadioButton standardMap;
    @FXML private RadioButton randomMap;
    @FXML private ToggleGroup numPlayers;
    @FXML private RadioButton numPlayers1;
    @FXML private RadioButton numPlayers2;
    @FXML private RadioButton numPlayers3;
    @FXML private RadioButton numPlayers4;
    @FXML private Button next;
    
    @FXML protected void initialize() {
        difficultyBeginner.setOnAction(e -> Game.setDifficulty(1));
        difficultyStandard.setOnAction(e -> Game.setDifficulty(2));
        difficultyTournament.setOnAction(e -> Game.setDifficulty(3));
        standardMap.setOnAction(e -> Game.setMapType(1));
        randomMap.setOnAction(e -> Game.setMapType(2));
        numPlayers1.setOnAction(e -> Game.setPlayerNum(1));
        numPlayers2.setOnAction(e -> Game.setPlayerNum(2));
        numPlayers3.setOnAction(e -> Game.setPlayerNum(3));
        numPlayers4.setOnAction(e -> Game.setPlayerNum(4));
        next.setOnAction(e -> {
            if (difficulty.getSelectedToggle() != null && mapType.getSelectedToggle() != null
                    && numPlayers.getSelectedToggle() != null) {
                ((RadioButton) difficulty.getSelectedToggle()).fire();
                ((RadioButton) mapType.getSelectedToggle()).fire();
                ((RadioButton) numPlayers.getSelectedToggle()).fire();
                try {
                    Main.swapPane(getClass().getResource("player.fxml"));
                } catch (IOException ex) {
                    System.err.println("Missing Asset: player.fxml");
                }
            }
        });
    }
}
