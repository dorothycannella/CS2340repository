package sample;

import java.awt.event.ActionEvent;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.event.Event;
import javafx.event.EventHandler;


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
    private Button submit;
    @FXML
    private ToggleGroup mapType;
    @FXML
    private RadioButton numPlayers1;
    @FXML
    private RadioButton numPlayers2;
    @FXML
    private RadioButton numPlayers3;
    @FXML
    private RadioButton numPlayers4;
    @FXML
    private ToggleGroup Difficulty;
    @FXML
    private ToggleGroup numPlayers;
    private String race;
    private String map;
    private String difficultyLevel;
    private int numOfPlayers;



    public void handleButtonAction(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) submit.getScene().getWindow();
        root = FXMLLoader.load(this.getClass().getResource("playerOne.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
