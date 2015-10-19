package view;

import controller.Game;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

import java.io.IOException;

public class MapController {
    Timeline timer;
    @FXML private GridPane grid;
    @FXML private GridPane panel;
    @FXML private Label phase;
    @FXML private Label turn;
    @FXML private Label round;
    @FXML private Label type;
    @FXML private Label owner;
    @FXML private Label price;
    @FXML private Label timeLabel;
    @FXML private Label time;
    @FXML private Button pass;

    @FXML protected void initialize() {
        if (!Game.getPhase()) {
            Game.resetTurnTime();
            pass.setDisable(true);
            pass.setVisible(false);
            timeLabel.setVisible(true);
            time.setText("0:" + String.format("%02d", Game.getTime()));
            time.setVisible(true);
            timer = new Timeline(new KeyFrame(
                    Duration.millis(1000),
                    e -> {
                        time.setText("0:" + String.format("%02d",
                                Game.getTime()));
                        if (Game.getTime() <= 0) {
                            initialize();
                        }
                    }));
            timer.setCycleCount(Animation.INDEFINITE);
            timer.play();
        }
        phase.setText(Game.getPhase() ? "Selection Phase" : "Main Phase");
        turn.setText(Game.getData(2, Game.getNextTurn()) + "'s Turn");
        turn.setTextFill(Paint.valueOf(Game.getData(1, Game.getNextTurn())));
        round.setText("Round: " + Game.getRound());
        pass.setOnAction(e -> {
            Game.pass();
            initialize();
        });
        refreshPanel();
        refreshMap();
    }

    private void refreshPanel() {
        ObservableList<Node> info = panel.getChildren();
        for (Node n: info) {
            int i = GridPane.getRowIndex(n);
            int j = GridPane.getColumnIndex(n);
            ((Label) n).setTextFill(Paint.valueOf(
                    Game.getData(1, (j / 3) + 1)));
            if (i == 1 && j % 3 == 0) {
                ((Label) n).setText(Game.getData(2, (j / 3) + 1));
            } else if (i == 2 && j % 3 == 0) {
                ((Label) n).setText(Game.getData(0, (j / 3) + 1));
            } else if (i == 0 && j % 3 == 1) {
                ((Label) n).setText("Money: "
                        + Game.getResources(0, (j / 3) + 1));
            } else if (i == 1 && j % 3 == 1) {
                ((Label) n).setText("Food: "
                        + Game.getResources(1, (j / 3) + 1));
            } else if (i == 2 && j % 3 == 1) {
                ((Label) n).setText("Energy: "
                        + Game.getResources(2, (j / 3) + 1));
            } else if (i == 0 && j % 3 == 2) {
                ((Label) n).setText("Smithore: "
                        + Game.getResources(3, (j / 3) + 1));
            } else if (i == 1 && j % 3 == 2) {
                ((Label) n).setText("Crystite: "
                        + Game.getResources(4, (j / 3) + 1));
            } else if (i == 2 && j % 3 == 2) {
                ((Label) n).setText("Score: " + Game.getScore((j / 3) + 1));
            }
        }
    }

    private void refreshMap() {
        ObservableList<Node> tiles = grid.getChildren();
        for (Node n: tiles) {
            int i = GridPane.getRowIndex(n);
            int j = GridPane.getColumnIndex(n);
            Image cursor = new Image(getClass().getResource("assets/"
                    + Game.getData(1, Game.getNextTurn()).toLowerCase()
                    + Game.getTileType(i, j)
                    + (Game.getMule(Game.getNextTurn())
                    && (i != 2 || j != 4) ? "Mule" : "")
                    + ".jpg").toExternalForm());
            Image img = new Image(getClass().getResource("assets/"
                    + Game.getData(1, Game.getOwner(i, j)).toLowerCase()
                    + Game.getTileType(i, j)
                    + (Game.hasMule(i, j) ? "Mule" : "")
                    + ".jpg").toExternalForm());
            ((ImageView) n).setImage(img);
            n.setOnMouseEntered(e -> {
                ((ImageView) n).setImage(cursor);
                int mnt = Game.getNumMountains(i, j);
                type.setText(Game.getTileType(i, j)
                        + (mnt > 0 ? " (" + mnt + ")" : ""));
                if (i == 2 && j == 4) {
                    owner.setText("Click to enter!");
                } else {
                    owner.setText("Owner: " + Game.getData(2,
                            Game.getOwner(i, j)));
                }
                owner.setTextFill(Paint.valueOf(Game.getData(1,
                        Game.getOwner(i, j))));
                if (i == 2 && j == 4 || Game.getOwner(i, j) > 0) {
                    price.setText("Not for sale!");
                } else if (!Game.getPhase()) {
                    price.setText("Purchase from Land Office");
                } else {
                    price.setText("Price: " + (!Game.getPhase()
                            || Game.getRound() > 2 ? "300" : "Free"));
                }
            });
            n.setOnMouseExited(e -> ((ImageView) n).setImage(img));
            if (Game.getPhase() && (i != 2 || j != 4)) {
                n.setOnMouseClicked(e -> {
                    int buyer = Game.getNextTurn();
                    Game.buyTile(i, j);
                    boolean feedback = Game.confirmPurchase(i, j, buyer);
                    if (feedback) {
                        owner.setText("Owner: " + Game.getData(2, buyer));
                        owner.setTextFill(Paint.valueOf(Game.getData(1, buyer)));
                        price.setText("Not for sale!");
                        initialize();
                    }
                });
            } else if (i != 2 || j != 4) {
                int buyer = Game.getNextTurn();
                n.setOnMouseClicked(e -> {
                    if (Game.getMule(buyer)
                            && Game.getOwner(i, j) == buyer
                            && !Game.hasMule(i, j)) {
                        Game.placeMule(i, j);
                        refreshMap();
                    } else if (Game.getMule(buyer)) {
                        price.setText("You have lost your MULE!");
                        Game.removeMule();
                        refreshMap();
                    } else {
                        price.setText("You can't do that right now!");
                    }
                });
            } else {
                n.setOnMouseClicked(e -> {
                    if (!Game.getPhase()) {
                        timer.stop();
                        try {
                            Main.swapPane(getClass().getResource("town.fxml"));
                        } catch (IOException ex) {
                            System.err.println("Missing Asset: town.fxml");
                        }
                    } else {
                        price.setText("Wait for the Main Phase!");
                    }
                });
            }
        }
    }
}
