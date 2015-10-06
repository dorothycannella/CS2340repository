package view;

import controller.Game;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.IOException;

public class TownController {
    Timeline timer;
    @FXML private Button exit;
    @FXML private Button pub;
    @FXML private Button store;
    @FXML private Button land;
    @FXML private Button assay;
    @FXML private Button gamble;
    @FXML private Label time;
    @FXML private ImageView background;

    @FXML protected void initialize() {
        time.setText("0:" + String.format("%02d", Game.getTime()));
        time.setVisible(true);
        timer = new Timeline(new KeyFrame(
                Duration.millis(1000),
                e -> {
                    time.setText("0:" + String.format("%02d",
                            Game.getTime()));
                    if (Game.getTime() <= 0) {
                        try {
                            Main.swapPane(getClass().getResource("map.fxml"));
                        } catch (IOException ex) {
                            System.err.println("Missing Asset: map.fxml");
                        }
                    }
                }));
        timer.setCycleCount(Animation.INDEFINITE);
        timer.play();
        exit.setOnAction(e -> {
            try {
                Main.swapPane(getClass().getResource("map.fxml"));
            } catch (IOException ex) {
                System.err.println("Missing Asset: map.fxml");
            }
        });
        pub.setOnAction(e1 -> {
            pub.setVisible(false);
            store.setVisible(false);
            land.setVisible(false);
            assay.setVisible(false);
            gamble.setVisible(true);
            exit.setOnAction(e2 -> {
                pub.setVisible(true);
                store.setVisible(true);
                land.setVisible(true);
                assay.setVisible(true);
                gamble.setVisible(false);
                exit.setOnAction(e3 -> {
                    try {
                        Main.swapPane(getClass().getResource("map.fxml"));
                    } catch (IOException ex) {
                        System.err.println("Missing Asset: map.fxml");
                    }
                });
                background.setImage(new Image(getClass().getResource("assets/t"
                        + "ownScreen.jpg").toExternalForm()));
            });
            background.setImage(new Image(getClass().getResource("assets/pubSc"
                    + "reen.jpg").toExternalForm()));
        });
        gamble.setOnAction(e -> {
            Game.pub();
            try {
                Main.swapPane(getClass().getResource("map.fxml"));
            } catch (IOException ex) {
                System.err.println("Missing Asset: map.fxml");
            }
        });
    }
}
