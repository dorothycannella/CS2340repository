package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

import java.io.IOException;

public class MapController {
    @FXML private GridPane grid;
    @FXML private GridPane panel;
    @FXML private Label phase;
    @FXML private Label turn;
    @FXML private Label round;
    @FXML private Label type;
    @FXML private Label owner;
    @FXML private Label price;
    @FXML private Button pass;

    @FXML protected void initialize() {
        ObservableList<Node> info = panel.getChildren();
        ObservableList<Node> tiles = grid.getChildren();
        phase.setText(Game.getPhase() ? "Selection Phase" : "Main Phase");
        phase.setTextFill(Paint.valueOf("White"));
        turn.setText(Game.getName(Game.getNextTurn()) + "'s Turn");
        turn.setTextFill(Paint.valueOf(Game.getColor(Game.getNextTurn())));
        round.setText("Round: " + Game.getRound());
        round.setTextFill(Paint.valueOf("White"));
        type.setTextFill(Paint.valueOf("White"));
        owner.setTextFill(Paint.valueOf("White"));
        price.setTextFill(Paint.valueOf("White"));
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
                ((Label) n).setText("Smithore: " + Game.getSmithore((j / 3) + 1));
            } else if (i == 1 && j % 3 == 2) {
                ((Label) n).setText("Crystite: " + Game.getCrystite((j / 3) + 1));
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
                type.setText(Game.getTileType(i, j) + (mnt > 0 ? " (" + mnt + ")" : ""));
                if (i == 2 && j == 4) {
                    owner.setText("Click to enter!");
                } else {
                    owner.setText("Owner: " + Game.getName(Game.getOwner(i, j)));
                }
                owner.setTextFill(Paint.valueOf(Game.getColor(Game.getOwner(i, j))));
                if (i == 2 && j == 4 || Game.getOwner(i, j) > 0) {
                    price.setText("Not for sale!");
                } else {
                    price.setText("Price: " + (!Game.getPhase() || Game.getRound() > 2 ? "300" : "Free"));
                }
            });
            n.setOnMouseExited(e -> ((ImageView) n).setImage(img));
            if (GridPane.getRowIndex(n) == 2 && GridPane.getColumnIndex(n) == 4) {
                n.setOnMouseClicked(e -> {
                    try {
                        Main.swapPane(getClass().getResource("town.fxml"));
                    } catch (IOException ex) {
                        System.err.println(ex);
                    }
                });
            } else {
                n.setOnMouseClicked(e -> {
                    boolean feedback = Game.buyTile(GridPane.getRowIndex(n), GridPane.getColumnIndex(n));
                    if (feedback) {
                        owner.setText("Owner: " + Game.getName(Game.getNextTurn()));
                        price.setText("Not for sale!");
                        initialize();
                    } else {
                        price.setText("You can't buy this tile!");
                    }
                });
            }
        }
    }
}
