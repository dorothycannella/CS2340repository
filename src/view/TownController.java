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
        Game game = Main.getGame();
        time.setText("0:" + String.format("%02d", game.getTime()));
        time.setVisible(true);
        timer = new Timeline(new KeyFrame(
                Duration.millis(10),
                e -> {
                    time.setText("0:" + String.format("%02d",
                            game.getTime()));
                    if (game.getTime() <= 0) {
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
            game.pub();
            try {
                Main.swapPane(getClass().getResource("map.fxml"));
            } catch (IOException ex) {
                System.err.println("Missing Asset: map.fxml");
            }
        });
        store.setOnAction(e1 -> {
            refreshData(game);
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
                game.buy(order);
                refreshData(game);
            }
        });
        sell.setOnAction(e -> {
            if (menu.getSelectedToggle() != null) {
                ((RadioButton) menu.getSelectedToggle()).fire();
                game.sell(order);
                refreshData(game);
            }
        });
    }

    private void refreshData(Game game) {
        food.setText("Remaining: " + game.getStock(0));
        energy.setText("Remaining: " + game.getStock(1));
        smithore.setText("Remaining: " + game.getStock(2));
        crystite.setText("Remaining: " + game.getStock(3));
        mules.setText(Integer.toString(game.getStock(4)));
        int current = game.getNextTurn();
        String color = game.getData(1, current);
        player.setText(game.getData(2, current));
        player.setTextFill(Paint.valueOf(color));
        pMoney.setText("Money: " + game.getResources(0, current));
        pMoney.setTextFill(Paint.valueOf(color));
        pFood.setText("Food: " + game.getResources(1, current));
        pFood.setTextFill(Paint.valueOf(color));
        pEnergy.setText("Energy: " + game.getResources(2, current));
        pEnergy.setTextFill(Paint.valueOf(color));
        pSmithore.setText("Smithore: " + game.getResources(3, current));
        pSmithore.setTextFill(Paint.valueOf(color));
        pCrystite.setText("Crystite: " + game.getResources(4, current));
        pCrystite.setTextFill(Paint.valueOf(color));
        pMules.setText("Unplaced MULEs: "
                + (game.getMule(game.getNextTurn()) ? 1 : 0));
        pMules.setTextFill(Paint.valueOf(color));
    }
}
