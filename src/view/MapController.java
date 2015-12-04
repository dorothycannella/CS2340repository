package view;

import controller.SystemManager;
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
    @FXML private GridPane grid;
    @FXML private GridPane panel;
    @FXML private GridPane popup;
    @FXML private Label phase;
    @FXML private Label turn;
    @FXML private Label round;
    @FXML private Label type;
    @FXML private Label owner;
    @FXML private Label price;
    @FXML private Label timeLabel;
    @FXML private Label time;
    @FXML private Label event;
    @FXML private Button pass;
    @FXML private Button ok;
    @FXML private Button save;
    private Timeline timer;

    /**
     * Manages the display of the map screen.
     */
    @FXML
    private void initialize() {
        SystemManager game = Main.getGame();
        String[] eventText = {
                "YOU JUST RECEIVED A PACKAGE FROM THE GT ALUMNI\nCONTAINING 3 "
                        + "FOOD AND 2 ENERGY UNITS.",
                "A WANDERING TECH STUDENT REPAID YOUR HOSPITALITY\nBY LEAVING "
                        + "TWO BARS OF ORE.",
                "THE MUSEUM BOUGHT YOUR ANTIQUE PERSONAL COMPUTER\nFOR $"
                        + 200 * (game.getRound() / 4 + 1) + ".",
                "YOU FOUND A DEAD MOOSE RAT AND SOLD THE HIDE FOR $"
                        + 50 * (game.getRound() / 4 + 1) + ".",
                "FLYING CAT-BUGS ATE THE ROOF OFF YOUR HOUSE.\nREPAIRS COST $"
                        + 100 * (game.getRound() / 4 + 1) + ".",
                "MISCHIEVOUS UGA STUDENTS BROKE INTO YOUR STORAGE SHED\nAND ST"
                        + "OLE HALF YOUR FOOD.",
                "YOUR SPACE GYPSY INLAWS MADE A MESS OF THE TOWN.\nIT COST YOU"
                        + " $" + 150 * (game.getRound() / 4 + 1)
                        + " TO CLEAN IT UP.",
                "A DISTANT RELATIVE DIED AND LEFT YOU A VAST FORTUNE."
                        + "\nBUT AFTER TAXES YOU ONLY GOT $"
                        + 100 * (game.getRound() / 4 + 1) + ".",
                "THE MUSEUM BOUGHT YOUR ANTIQUE PERSONAL COMPUTER FOR $"
                        + 200 * (game.getRound() / 4 + 1) + ".",
                "YOUR CHILD WAS BITTEN BY A BAT LIZARD AND THE HOSPITAL BILL COST YOU $"
                        + 100 * (game.getRound() / 4 + 1) + "."
        };
        if (!game.getPhase() && game.getEventType() > -1) {
            event.setText(eventText[game.getEventType()]);
            popup.setVisible(true);
            ok.setOnAction(e -> popup.setVisible(false));
            game.disarm();
        }
        if (!game.getPhase()) {
            game.resetTurnTime();
            pass.setDisable(true);
            pass.setVisible(false);
            timeLabel.setVisible(true);
            time.setText("0:" + String.format("%02d", game.getTime()));
            time.setVisible(true);
            timer = new Timeline(new KeyFrame(
                    Duration.millis(10),
                    e -> {
                        time.setText("0:" + String.format("%02d",
                                game.getTime()));
                        if (game.getTime() <= 0) {
                            initialize();
                        }
                    }));
            timer.setCycleCount(Animation.INDEFINITE);
            timer.play();
        }
        phase.setText(game.getPhase() ? "Selection Phase" : "Main Phase");
        turn.setText(game.getData(2, game.getNextTurn()) + "'s Turn");
        turn.setTextFill(Paint.valueOf(game.getData(1, game.getNextTurn())));
        round.setText("Round: " + game.getRound());
        pass.setOnAction(e -> {
            game.pass();
            initialize();
        });
        save.setOnAction(e -> {
            try {
                game.saveGame();
            } catch (IOException ex) {
                ex.printStackTrace();
                System.err.println( "save file not found" );
            }
        });
        refreshPanel(game);
        refreshMap(game);
    }

    /**
     * Reloads the data displayed for each player.
     * @param game the current game
     */
    private void refreshPanel(SystemManager game) {
        ObservableList<Node> info = panel.getChildren();
        for (Node n: info) {
            int i = GridPane.getRowIndex(n);
            int j = GridPane.getColumnIndex(n);
            ((Label) n).setTextFill(Paint.valueOf(
                    game.getData(1, (j / 3) + 1)));
            if (i == 1 && j % 3 == 0) {
                ((Label) n).setText(game.getData(2, (j / 3) + 1));
            } else if (i == 2 && j % 3 == 0) {
                ((Label) n).setText(game.getData(0, (j / 3) + 1));
            } else if (i == 0 && j % 3 == 1) {
                ((Label) n).setText("Money: "
                        + game.getResources(0, (j / 3) + 1));
            } else if (i == 1 && j % 3 == 1) {
                ((Label) n).setText("Food: "
                        + game.getResources(1, (j / 3) + 1));
            } else if (i == 2 && j % 3 == 1) {
                ((Label) n).setText("Energy: "
                        + game.getResources(2, (j / 3) + 1));
            } else if (i == 0 && j % 3 == 2) {
                ((Label) n).setText("Smithore: "
                        + game.getResources(3, (j / 3) + 1));
            } else if (i == 1 && j % 3 == 2) {
                ((Label) n).setText("Crystite: "
                        + game.getResources(4, (j / 3) + 1));
            } else if (i == 2 && j % 3 == 2) {
                ((Label) n).setText("Score: " + game.getScore((j / 3) + 1));
            }
        }
    }

    /**
     * Reloads the display of the map.
     * @param game the current game
     */
    private void refreshMap(SystemManager game) {
        ObservableList<Node> tiles = grid.getChildren();
        for (Node n: tiles) {
            int i = GridPane.getRowIndex(n);
            int j = GridPane.getColumnIndex(n);
            Image cursor = new Image(getClass().getResource("assets/"
                    + game.getData(1, game.getNextTurn()).toLowerCase()
                    + game.getTileType(i, j)
                    + (game.getMule(game.getNextTurn())
                    && (i != 2 || j != 4) ? "Mule" : "")
                    + ".jpg").toExternalForm());
            Image img = new Image(getClass().getResource("assets/"
                    + game.getData(1, game.getOwner(i, j)).toLowerCase()
                    + game.getTileType(i, j)
                    + (game.hasMule(i, j) ? "Mule" : "")
                    + ".jpg").toExternalForm());
            ((ImageView) n).setImage(img);
            n.setOnMouseEntered(e -> {
                ((ImageView) n).setImage(cursor);
                int mnt = game.getNumMountains(i, j);
                type.setText(game.getTileType(i, j)
                        + (mnt > 0 ? " (" + mnt + ")" : ""));
                if (i == 2 && j == 4) {
                    owner.setText("Click to enter!");
                } else {
                    owner.setText("Owner: " + game.getData(2,
                            game.getOwner(i, j)));
                }
                owner.setTextFill(Paint.valueOf(game.getData(1,
                        game.getOwner(i, j))));
                if (i == 2 && j == 4 || game.getOwner(i, j) > 0) {
                    price.setText("Not for sale!");
                } else if (!game.getPhase()) {
                    price.setText("Purchase from Land Office");
                } else {
                    price.setText("Price: " + (!game.getPhase()
                            || game.getRound() > 2 ? "300" : "Free"));
                }
            });
            n.setOnMouseExited(e -> ((ImageView) n).setImage(img));
            if (game.getPhase() && (i != 2 || j != 4)) {
                n.setOnMouseClicked(e -> {
                    int buyer = game.getNextTurn();
                    game.buyTile(i, j);
                    boolean feedback = game.confirmPurchase(i, j, buyer);
                    if (feedback) {
                        owner.setText("Owner: " + game.getData(2, buyer));
                        owner.setTextFill(Paint.valueOf(game.getData(1, buyer)));
                        price.setText("Not for sale!");
                        initialize();
                    }
                });
            } else if (i != 2 || j != 4) {
                int buyer = game.getNextTurn();
                n.setOnMouseClicked(e -> {
                    if (game.getMule(buyer)
                            && game.getOwner(i, j) == buyer
                            && !game.hasMule(i, j)) {
                        game.placeMule(i, j);
                        refreshMap(game);
                    } else if (game.getMule(buyer)) {
                        price.setText("You have lost your MULE!");
                        game.removeMule();
                        refreshMap(game);
                    } else {
                        price.setText("You can't do that right now!");
                    }
                });
            } else {
                n.setOnMouseClicked(e -> {
                    if (!game.getPhase()) {
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
