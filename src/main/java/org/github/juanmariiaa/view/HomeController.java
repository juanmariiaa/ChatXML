package org.github.juanmariiaa.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller that links to the sites you can go.
 */
public class HomeController {
    @FXML
    public void switchToProfile() throws IOException {
        App.setRoot("profile");
    }

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login");
    }
    @FXML
    private void switchToChats() throws IOException {
        App.setRoot("chat");
    }
}
