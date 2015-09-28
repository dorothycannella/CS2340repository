package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controller.Game;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    private static Stage game;

    @Override
    public void start(Stage stage) throws IOException {
        Game.makeMap();
        game = stage;
        swapPane(getClass().getResource("config.fxml"));
    }

    public static void swapPane(URL location) throws IOException {
        Parent root = FXMLLoader.load(location);
        game.setTitle("MULE");
        game.setScene(new Scene(root, 960, 540));
        game.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
