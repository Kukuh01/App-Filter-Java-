package org.silvanus.develop.appfilterfoto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PhotoFilterApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PhotoFilterApp.class.getResource("app-filter.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("APP FILTER FOTO");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}