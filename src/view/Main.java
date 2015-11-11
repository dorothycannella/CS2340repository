package view;

import controller.SystemManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    private static Stage window;
    private static SystemManager game;

    public static SystemManager getGame() {
        return game;
    }

    public static void setGame(SystemManager g) {
        game = g;
    }

    public static void swapPane(URL location) throws IOException {
        Parent root = FXMLLoader.load(location);
        window.setTitle("MULE");
        window.setScene(new Scene(root, 960, 540));
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public final void start(Stage stage) throws IOException {
        window = stage;
        swapPane(getClass().getResource("config.fxml"));
    }
}
