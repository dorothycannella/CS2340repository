package sample;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.awt.*;

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
    private Button submitConfig;
    @FXML
    private ToggleGroup mapType;
    @FXML
    private ToggleGroup numPlayers;
    @FXML
    private ToggleGroup Difficulty;

    private String race;
    private String map;
    private String difficultyLevel;
    private int numOfPlayers;



}
