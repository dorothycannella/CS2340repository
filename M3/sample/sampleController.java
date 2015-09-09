package sample;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.Scene;

import java.awt.*;

scene.getStylesheets().add(this.getClass()
.getResource(“controlStyle.css”).toExternalForm());

public class sampleController {
    @FXML
    private RadioButton standardMap;
    @FXML
    private RadioButton randomMap;
    @FXML
    private RadioButton difficultyBeginner;
    @FXML
    private RadioButton difficultyStandard;
    @FXML
    private RadioButton difficultyTournament;
    @FXML
    private RadioButton raceHuman;
    @FXML
    private RadioButton raceFlapper;
    @FXML
    private RadioButton raceBonzoid;
    @FXML
    private RadioButton raceUgaite;
    @FXML
    private RadioButton raceBuzzite;
    @FXML
    private Button submit;
    @FXML
    private Button continueToPlay;
    @FXML
    private ToggleGroup mapType;
    @FXML
    private ToggleGroup numPlayers;
    @FXML
    private ToggleGroup Difficulty;
    @FXML
    private RadioButton blue;
    @FXML
    private RadioButton green;
    @FXML
    private RadioButton yellow;
    @FXML
    private RadioButton red;
    @FXML
    private RadioButton orange;
    @FXML
    private RadioButton dorothy;
    @FXML
    private RadioButton jackie;
    @FXML
    private RadioButton linda;
    @FXML
    private RadioButton tyler;
    @FXML
    private RadioButton raymond;

    private String race;
    private String map;
    private String difficultyLevel;
    private int numOfPlayers;



}
