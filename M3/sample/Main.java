package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("MULE Configuration");
        primaryStage.setScene(new Scene(root, 515, 300));
        primaryStage.show();
        String css = this.getClass().getResource("controlStyle.css").toExternalForm();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
