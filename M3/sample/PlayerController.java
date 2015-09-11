package sample;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class PlayerController {
    @FXML private RadioButton dorothy; 
    @FXML private RadioButton jackie;
    @FXML private RadioButton linda;
    @FXML private RadioButton tyler;
    @FXML private RadioButton raymond;
    @FXML private RadioButton human;
    @FXML private RadioButton flapper;
    @FXML private RadioButton bonzoid;
    @FXML private RadioButton buzzite;
    @FXML private RadioButton ugaite;
    @FXML private RadioButton red;
    @FXML private RadioButton orange;
    @FXML private RadioButton blue;
    @FXML private RadioButton green;
    @FXML private RadioButton yellow;
    @FXML private Button submit;
    @FXML private Text prompt;
    private int player = 1;
    private String race;
    private String color;
    private String name;

    @FXML protected void initialize() {
        dorothy.setOnAction(e -> {
            name = "Dorothy";
        });
        jackie.setOnAction(e -> {
            name = "Jackie";
        });
        linda.setOnAction(e -> {
            name = "Linda";
        });
        tyler.setOnAction(e -> {
            name = "Tyler";
        });
        raymond.setOnAction(e -> {
            name = "Raymond";
        });
        human.setOnAction(e -> {
            race = "Human";
        });
        flapper.setOnAction(e -> {
            race = "Flapper";
        });
        bonzoid.setOnAction(e -> {
            race = "Bonzoid";
        });
        buzzite.setOnAction(e -> {
            race = "Buzzite";
        });
        ugaite.setOnAction(e -> {
            race = "Ugaite";
        });
        red.setOnAction(e -> {
            color = "Red";
        });
        orange.setOnAction(e -> {
            color = "Orange";
        });
        blue.setOnAction(e -> {
            color = "Blue";
        });
        green.setOnAction(e -> {
            color = "Green";
        });
        yellow.setOnAction(e -> {
            color = "Yellow";
        });
        submit.setOnAction(e -> {
            Game.setPlayer(new MULEPerson(race, color, name), player - 1);
            if (player < Game.getPlayerNum()) {
                prompt.setText("Player " + (++player) + " Configuration");
            } else {
                try {
                    swapPane();
                } catch (IOException ex) {
                    System.err.println("File not found.");
                }
            }
        });
    }

    private void swapPane() throws IOException {
        Stage stage = (Stage) submit.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("confirm.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}

