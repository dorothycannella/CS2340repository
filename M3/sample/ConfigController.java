package sample;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

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
        difficultyBeginner.setOnAction(e -> {
            Game.setDifficulty(0);
        });
        difficultyStandard.setOnAction(e -> {
            Game.setDifficulty(1);
        });
        difficultyTournament.setOnAction(e -> {
            Game.setDifficulty(2);
        });
        standardMap.setOnAction(e -> {
            Game.setMap(0);
        });
        randomMap.setOnAction(e -> {
            Game.setMap(1);
        });
        numPlayers1.setOnAction(e -> {
            Game.setPlayerNum(1);
        });
        numPlayers2.setOnAction(e -> {
            Game.setPlayerNum(2);
        });
        numPlayers3.setOnAction(e -> {
            Game.setPlayerNum(3);
        });
        numPlayers4.setOnAction(e -> {
            Game.setPlayerNum(4);
        });
        next.setOnAction(e -> {
            ((RadioButton) difficulty.getSelectedToggle()).fire();
            ((RadioButton) mapType.getSelectedToggle()).fire();
            ((RadioButton) numPlayers.getSelectedToggle()).fire();
            try {
                swapPane();
            } catch (IOException ex) {
                System.err.println("File not found.");
            }
        });
    }
   
    private void swapPane() throws IOException {
        Stage stage = (Stage) next.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("player.fxml"));
        stage.setScene(new Scene(root, 960, 540));
        stage.show();
    }
}
