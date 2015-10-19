package view;

import controller.Game;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

import java.io.IOException;

public class TownController {
    Timeline timer;
    @FXML private GridPane storeGrid;
    @FXML private GridPane stats;
    @FXML private ToggleGroup menu;
    @FXML private RadioButton buyFood;
    @FXML private RadioButton buyEnergy;
    @FXML private RadioButton buySmithore;
    @FXML private RadioButton buyCrystite;
    @FXML private RadioButton buyFoodMule;
    @FXML private RadioButton buyEnergyMule;
    @FXML private RadioButton buySmithoreMule;
    @FXML private RadioButton buyCrystiteMule;
    @FXML private Button exit;
    @FXML private Button pub;
    @FXML private Button store;
    @FXML private Button land;
    @FXML private Button assay;
    @FXML private Button gamble;
    @FXML private Button buy;
    @FXML private Button sell;
    @FXML private Label time;
    @FXML private Label food;
    @FXML private Label energy;
    @FXML private Label smithore;
    @FXML private Label crystite;
    @FXML private Label mules;
    @FXML private Label player;
    @FXML private Label pMoney;
    @FXML private Label pFood;
    @FXML private Label pEnergy;
    @FXML private Label pSmithore;
    @FXML private Label pCrystite;
    @FXML private Label pMules;
    @FXML private ImageView background;
    private int order;

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
        store.setOnAction(e1 -> {
            refreshData();
            pub.setVisible(false);
            store.setVisible(false);
            land.setVisible(false);
            assay.setVisible(false);
            storeGrid.setVisible(true);
            stats.setVisible(true);
            exit.setOnAction(e2 -> {
                pub.setVisible(true);
                store.setVisible(true);
                land.setVisible(true);
                assay.setVisible(true);
                storeGrid.setVisible(false);
                stats.setVisible(false);
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
            background.setImage(new Image(getClass().getResource("assets/store"
                    + "Screen.jpg").toExternalForm()));
        });
        buyFood.setOnAction(e -> order = 0);
        buyEnergy.setOnAction(e -> order = 1);
        buySmithore.setOnAction(e -> order = 2);
        buyCrystite.setOnAction(e -> order = 3);
        buyFoodMule.setOnAction(e -> order = 4);
        buyEnergyMule.setOnAction(e -> order = 5);
        buySmithoreMule.setOnAction(e -> order = 6);
        buyCrystiteMule.setOnAction(e -> order = 7);
        buy.setOnAction(e -> {
            if (menu.getSelectedToggle() != null) {
                ((RadioButton) menu.getSelectedToggle()).fire();
                Game.buy(order);
                refreshData();
            }
        });
        sell.setOnAction(e -> {
            if (menu.getSelectedToggle() != null) {
                ((RadioButton) menu.getSelectedToggle()).fire();
                Game.sell(order);
                refreshData();
            }
        });
    }

    private void refreshData() {
        food.setText("Remaining: " + Game.getStock(0));
        energy.setText("Remaining: " + Game.getStock(1));
        smithore.setText("Remaining: " + Game.getStock(2));
        crystite.setText("Remaining: " + Game.getStock(3));
        mules.setText(Integer.toString(Game.getStock(4)));
        int current = Game.getNextTurn();
        String color = Game.getData(1, current);
        player.setText(Game.getData(2, current));
        player.setTextFill(Paint.valueOf(color));
        pMoney.setText("Money: " + Game.getResources(0, current));
        pMoney.setTextFill(Paint.valueOf(color));
        pFood.setText("Food: " + Game.getResources(1, current));
        pFood.setTextFill(Paint.valueOf(color));
        pEnergy.setText("Energy: " + Game.getResources(2, current));
        pEnergy.setTextFill(Paint.valueOf(color));
        pSmithore.setText("Smithore: " + Game.getResources(3, current));
        pSmithore.setTextFill(Paint.valueOf(color));
        pCrystite.setText("Crystite: " + Game.getResources(4, current));
        pCrystite.setTextFill(Paint.valueOf(color));
        pMules.setText("Unplaced MULEs: "
                + (Game.getMule(Game.getNextTurn()) ? 1 : 0));
        pMules.setTextFill(Paint.valueOf(color));
    }
}
