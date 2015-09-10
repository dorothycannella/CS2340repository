package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.Event;
import javafx.event.EventHandler;


public class Main extends Application {
    
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("MULE Configuration");
        primaryStage.setScene(new Scene(root, 515, 300));
        primaryStage.show();
        String css = this.getClass().getResource("controlStyle.css").toExternalForm();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}
