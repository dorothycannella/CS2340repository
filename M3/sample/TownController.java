package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class TownController {
    @FXML private Button map;

    @FXML protected void initialize() {
        map.setOnAction(e -> {
            try {
                Main.swapPane(getClass().getResource("map.fxml"));
            } catch (IOException ex) {
                System.err.println(ex);
            }
        });
    }
}
