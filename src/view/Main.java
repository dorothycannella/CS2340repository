package view;

import controller.SystemManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    private static Stage window;
    private static SystemManager game;

    /**
     * Get the current game.
     * @return the game
     */
    public static SystemManager getGame() {
        return game;
    }

    /**
     * Set the current game to the given game.
     * @param g the game
     */
    public static void setGame(SystemManager g) {
        game = g;
    }

    /**
     * Loads the specified fxml.
     * @param location URL of the target fxml
     * @throws IOException
     */
    public static void swapPane(URL location) throws IOException {
        Parent root = FXMLLoader.load(location);
        window.setTitle("MULE");
        window.setScene(new Scene(root, 960, 540));
        window.show();

        // create media player
        Media media = new Media(new File("src/view/assets/bgm.mp3").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        //mediaPlayer.setAutoPlay(true);
        mediaPlayer.play();
    }

    /**
     * Command line entry point
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * JavaFX entry point
     * @param stage the current window's stage
     * @throws IOException
     */
    @Override
    public final void start(Stage stage) throws IOException {
        window = stage;
        swapPane(getClass().getResource("config.fxml"));
    }
}
