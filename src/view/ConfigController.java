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
    @FXML private Button load;
    private int dif;
    private int map;
    private int num;
    
    @FXML protected void initialize() {
        difficultyBeginner.setOnAction(e -> dif = 1);
        difficultyStandard.setOnAction(e -> dif = 2);
        difficultyTournament.setOnAction(e -> dif = 3);
        standardMap.setOnAction(e -> map = 1);
        randomMap.setOnAction(e -> map = 2);
        numPlayers1.setOnAction(e -> num = 1);
        numPlayers2.setOnAction(e -> num = 2);
        numPlayers3.setOnAction(e -> num = 3);
        numPlayers4.setOnAction(e -> num = 4);
        next.setOnAction(e -> {
            if (difficulty.getSelectedToggle() != null
                    && mapType.getSelectedToggle() != null
                    && numPlayers.getSelectedToggle() != null) {
                ((RadioButton) difficulty.getSelectedToggle()).fire();
                ((RadioButton) mapType.getSelectedToggle()).fire();
                ((RadioButton) numPlayers.getSelectedToggle()).fire();
                Main.setGame(new Game(dif, map, num));
                try {
                    Main.swapPane(getClass().getResource("player.fxml"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.err.println("Missing Asset: player.fxml");
                }
            }
        });
        load.setOnAction(e ->{
            //SystemManager game = new Game(0, 0, 0);
            Main.setGame(new Game(0,0,0));
            try {
                Main.getGame().loadGame();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                Main.swapPane(getClass().getResource("map.fxml"));
            } catch (IOException ex)  {
                ex.printStackTrace();
                System.err.println("Missing Asset: map.fxml");
            }
        });
    }
}
