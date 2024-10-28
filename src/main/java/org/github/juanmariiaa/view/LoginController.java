package org.github.juanmariiaa.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.github.juanmariiaa.others.SingletonUserSession;
import org.github.juanmariiaa.model.dao.UserDataManager;
import org.github.juanmariiaa.model.domain.User;

import java.io.IOException;

/**
 * Controller for the login functionality in the application.
 */
public class LoginController {

    @FXML
    private TextField tfUser;
    @FXML
    private PasswordField tfPass;

    private final UserDataManager dataUserManager = new UserDataManager();

    /**
     * Handles the login process when the user clicks the login button.
     * Validates the entered credentials and sets the user session if successful.
     *
     * @throws IOException If there's an issue switching to the home view.
     */
    @FXML
    private void login() {
        String username = tfUser.getText().trim();
        String password = tfPass.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "ERROR", "Please, complete all fields to continue.");
            return;
        }

        try {
            // Validate credentials and retrieve User object if valid
            User user = dataUserManager.validateUserCredentials(username, password);

            if (user != null) {
                // Successful login, store User object in session
                SingletonUserSession.login(user);
                showAlert(Alert.AlertType.INFORMATION, "SUCCESS", "Welcome, " + user.getName());
                switchToHome();
            }
        } catch (Exception e) {
            SingletonUserSession.logout();
            showAlert(Alert.AlertType.ERROR, "ERROR", e.getMessage());
        }
    }
    /**
     * Switches to the register view.
     *
     * @throws IOException If there's an issue switching to the register view.
     */
    @FXML
    private void switchToRegister() throws IOException {
        App.setRoot("register");
    }

    /**
     * Switches to the home view.
     *
     * @throws IOException If there's an issue switching to the home view.
     */
    @FXML
    private void switchToHome() throws IOException {
        App.setRoot("home");
    }

    /**
     * Utility method to show alerts.
     */
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
