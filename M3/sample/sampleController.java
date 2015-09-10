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
import javafx.scene.layout.GridPane;


public class sampleController {
    @FXML
    private RadioButton standardMap;
    @FXML
    private RadioButton randomMap;
    @FXML
    private GridPane content;
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
    


/*
    public void handleButtonAction(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) submit.getScene().getWindow();
        root = FXMLLoader.load(this.getClass().getResource("playerOne.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
*/
     @FXML protected void initialize() throws IOException {
        difficultyBeginner.setOnAction(e -> {
            GameSetUp.setDifficulty(1);
        });
        difficultyStandard.setOnAction(e -> {
            GameSetUp.setDifficulty(2);
        });
        difficultyTournament.setOnAction(e -> {
            GameSetUp.setDifficulty(3);
        });
        standardMap.setOnAction(e -> {
            GameSetUp.setMap(1);
        });
        randomMap.setOnAction(e -> {
            GameSetUp.setMap(2);
        });
        numPlayers1.setOnAction(e -> {
            GameSetUp.setPlayerNum(1);
        });
        numPlayers2.setOnAction(e -> {
            GameSetUp.setPlayerNum(2);
        });
        numPlayers3.setOnAction(e -> {
            GameSetUp.setPlayerNum(3);
        });
        numPlayers4.setOnAction(e -> {
            GameSetUp.setPlayerNum(4);
        });
        submit.setOnAction(e -> {
            try {
                swapPane();
            } catch(IOException exceptional){}           

        } );
   }
   
       public void swapPane() throws IOException {
        Stage primaryStage = (Stage)submit.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("sample - Copy.fxml"));
        primaryStage.setTitle("MULE Configuration");
        primaryStage.setScene(new Scene(root, 515, 300));
        primaryStage.show();
    }

}
