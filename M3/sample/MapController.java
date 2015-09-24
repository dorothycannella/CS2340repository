package sample;

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
import java.util.TimerTask;

public class MapController {
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
    Timeline timer;

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
        ObservableList<Node> info = panel.getChildren();
        ObservableList<Node> tiles = grid.getChildren();
        phase.setText(Game.getPhase() ? "Selection Phase" : "Main Phase");
        turn.setText(Game.getName(Game.getNextTurn()) + "'s Turn");
        turn.setTextFill(Paint.valueOf(Game.getColor(Game.getNextTurn())));
        round.setText("Round: " + Game.getRound());
        pass.setOnAction(e -> {
            Game.pass();
            initialize();
        });
        for (Node n: info) {
            int i = GridPane.getRowIndex(n);
            int j = GridPane.getColumnIndex(n);
            ((Label) n).setTextFill(Paint.valueOf(Game.getColor((j / 3) + 1)));
            if (i == 1 && j % 3 == 0) {
                ((Label) n).setText(Game.getName((j / 3) + 1));
            } else if (i == 2 && j % 3 == 0) {
                ((Label) n).setText(Game.getRace((j / 3) + 1));
            } else if (i == 0 && j % 3 == 1) {
                ((Label) n).setText("Money: " + Game.getMoney((j / 3) + 1));
            } else if (i == 1 && j % 3 == 1) {
                ((Label) n).setText("Food: " + Game.getFood((j / 3) + 1));
            } else if (i == 2 && j % 3 == 1) {
                ((Label) n).setText("Energy: " + Game.getEnergy((j / 3) + 1));
            } else if (i == 0 && j % 3 == 2) {
                ((Label) n).setText("Smithore: "
                        + Game.getSmithore((j / 3) + 1));
            } else if (i == 1 && j % 3 == 2) {
                ((Label) n).setText("Crystite: "
                        + Game.getCrystite((j / 3) + 1));
            } else if (i == 2 && j % 3 == 2) {
                ((Label) n).setText("Score: " + Game.getScore((j / 3) + 1));
            }
        }
        for (Node n: tiles) {
            int i = GridPane.getRowIndex(n);
            int j = GridPane.getColumnIndex(n);
            Image cursor = new Image(getClass().getResource("assets/"
                    + Game.getColor(Game.getNextTurn()).toLowerCase()
                    + Game.getTileType(i, j) + ".jpg").toExternalForm());
            Image img = new Image(getClass().getResource("assets/"
                    + Game.getColor(Game.getOwner(i, j)).toLowerCase()
                    + Game.getTileType(i, j) + ".jpg").toExternalForm());
            ((ImageView) n).setImage(img);
            n.setOnMouseEntered(e -> {
                ((ImageView) n).setImage(cursor);
                int mnt = Game.getNumMountains(i, j);
                type.setText(Game.getTileType(i, j)
                        + (mnt > 0 ? " (" + mnt + ")" : ""));
                if (i == 2 && j == 4) {
                    owner.setText("Click to enter!");
                } else {
                    owner.setText("Owner: " + Game.getName(
                            Game.getOwner(i, j)));
                }
                owner.setTextFill(Paint.valueOf(Game.getColor(
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
            if (GridPane.getRowIndex(n) != 2
                    || GridPane.getColumnIndex(n) != 4) {
                n.setOnMouseClicked(e -> {
                    int buyer = Game.getNextTurn();
                    boolean feedback = Game.buyTile(GridPane.getRowIndex(n),
                            GridPane.getColumnIndex(n));
                    if (feedback) {
                        owner.setText("Owner: " + Game.getName(buyer));
                        owner.setTextFill(Paint.valueOf(Game.getColor(buyer)));
                        price.setText("Not for sale!");
                        initialize();
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
                            System.err.println(ex);
                        }
                    } else {
                        price.setText("Wait for the Main Phase!");
                    }
                });
            }
        }
    }
}
