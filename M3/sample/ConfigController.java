package sample;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class ConfigController {
    @FXML private RadioButton difficultyBeginner;
    @FXML private RadioButton difficultyStandard;
    @FXML private RadioButton difficultyTournament;
    @FXML private RadioButton standardMap;
    @FXML private RadioButton randomMap;
    @FXML private RadioButton numPlayers1;
    @FXML private RadioButton numPlayers2;
    @FXML private RadioButton numPlayers3;
    @FXML private RadioButton numPlayers4;
    @FXML private Button submit;
    
    @FXML protected void initialize() {
        difficultyBeginner.setOnAction(e -> {
            Game.setDifficulty(1);
        });
        difficultyStandard.setOnAction(e -> {
            Game.setDifficulty(2);
        });
        difficultyTournament.setOnAction(e -> {
            Game.setDifficulty(3);
        });
        standardMap.setOnAction(e -> {
            Game.setMap(1);
        });
        randomMap.setOnAction(e -> {
            Game.setMap(2);
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
        submit.setOnAction(e -> {
            try {
                swapPane();
            } catch (IOException ex) {
                System.err.println("File not found.");
            }
        });
    }
   
    private void swapPane() throws IOException {
        Stage stage = (Stage) submit.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("player.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
