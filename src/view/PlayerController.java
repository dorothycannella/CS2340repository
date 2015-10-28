package view;

import controller.SystemManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;

public class PlayerController {
    @FXML private TextField typefield;
    @FXML private ToggleGroup races;
    @FXML private RadioButton human;
    @FXML private RadioButton flapper;
    @FXML private RadioButton bonzoid;
    @FXML private RadioButton buzzite;
    @FXML private RadioButton ugaite;
    @FXML private ToggleGroup colors;
    @FXML private RadioButton red;
    @FXML private RadioButton orange;
    @FXML private RadioButton blue;
    @FXML private RadioButton green;
    @FXML private RadioButton yellow;
    @FXML private Button next;
    @FXML private Text prompt;
    private int player = 1;
    private String race;
    private String color;
    private String name;
    private ArrayList<String> colorCodes;

    @FXML protected void initialize() {
        SystemManager game = Main.getGame();
        colorCodes = new ArrayList<>(5);
        colorCodes.add("Red");
        colorCodes.add("Orange");
        colorCodes.add("Blue");
        colorCodes.add("Green");
        colorCodes.add("Yellow");
        human.setOnAction(e -> race = "Human");
        flapper.setOnAction(e -> race = "Flapper");
        bonzoid.setOnAction(e -> race = "Bonzoid");
        buzzite.setOnAction(e -> race = "Buzzite");
        ugaite.setOnAction(e -> race = "Ugaite");
        red.setOnAction(e -> color = "Red");
        orange.setOnAction(e -> color = "Orange");
        blue.setOnAction(e -> color = "Blue");
        green.setOnAction(e -> color = "Green");
        yellow.setOnAction(e -> color = "Yellow");
        next.setOnAction(e -> {
            if (races.getSelectedToggle() != null
                    && colors.getSelectedToggle() != null
                    && typefield.getLength() > 0) {
                name = typefield.getCharacters().toString();
                typefield.clear();
                ((RadioButton) races.getSelectedToggle()).fire();
                races.getSelectedToggle().setSelected(false);
                ((RadioButton) colors.getSelectedToggle()).fire();
                ((RadioButton) colors.getSelectedToggle()).setDisable(true);
                colors.getSelectedToggle().setSelected(false);
                colorCodes.remove(color);
                game.setPlayer(player, race, color, name);
                if (player < game.getPlayerNum()) {
                    prompt.setText("Player " + (++player) + " Configuration");
                } else {
                    try {
                        game.setComputers(colorCodes);
                        Main.swapPane(getClass().getResource("confirm.fxml"));
                    } catch (IOException ex) {
                        System.err.println("Missing Asset: confirm.fxml");
                    }
                }
            }
        });
    }
}

