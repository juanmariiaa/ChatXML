package org.github.juanmariiaa.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The main application class for the tournament management system.
 * This class initializes and manages the main stage and scenes.
 */
public class App extends Application {
    private static Scene scene;
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;


        scene = new Scene(loadFXML("login"), 308, 411);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private static Scene createScene(String fxml, double width, double height) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, width, height);
        return scene;
    }

    public static void setRoot(String fxml) throws IOException {
        Scene newScene;
        if (fxml.equals("login")) {
            newScene = createScene(fxml, 308, 411);
        } else if(fxml.equals("chat")){
            newScene = createScene(fxml, 1166, 818);
        } else if(fxml.equals("profile")){
            newScene = createScene(fxml, 418, 730);
        } else if(fxml.equals("home")){
            newScene = createScene(fxml, 774, 489);
        } else if(fxml.equals("addFriend")){
            newScene = createScene(fxml, 329, 267);
        } else if(fxml.equals("register")){
            newScene = createScene(fxml, 329, 533);
        } else {
            newScene = createScene(fxml, 774, 489);
        }
        primaryStage.setResizable(true);
        primaryStage.setScene(newScene);
        primaryStage.setResizable(false);
    }


    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }

}