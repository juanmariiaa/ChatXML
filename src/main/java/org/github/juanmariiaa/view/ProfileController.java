package org.github.juanmariiaa.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.github.juanmariiaa.model.dao.UserDataManager;
import org.github.juanmariiaa.model.domain.Friend;
import org.github.juanmariiaa.model.domain.User;
import org.github.juanmariiaa.others.SingletonUserSession;
import org.github.juanmariiaa.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for managing user profile functionalities within the application.
 * This class handles user interactions for viewing and updating profile information,
 * including changing the profile picture and displaying the user's friends.
 */
public class ProfileController implements Initializable {

    @FXML
    private Label displayUsernameLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ImageView profileImageView; // ImageView for displaying profile picture

    @FXML
    private ListView<Friend> friendsListView; // ListView to display friends

    private User user; // The current user

    /**
     * Initializes the profile controller, loading the user data.
     *
     * @param location  The location used to resolve relative paths for the root object,
     *                  or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object
     *                  was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadUser(); // Load the current user's data
    }

    /**
     * Loads the current user's data from the session.
     */
    private void loadUser() {
        try {
            user = SingletonUserSession.getInstance().getUser(); // Get the current user from the session
            if (user != null) {
                updateProfileDisplay(); // Update the profile display with user data
            } else {
                Utils.showPopUp("Error", "User Not Found", "User not found in session.", Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace on error
            Utils.showPopUp("Error", "Loading User", "An error occurred while loading user data.", Alert.AlertType.ERROR);
        }
    }

    /**
     * Updates the UI components with the user's profile data.
     */
    private void updateProfileDisplay() {
        if (user != null) {
            usernameLabel.setText(user.getUsername());
            nameLabel.setText(user.getName());
            lastNameLabel.setText(user.getLastName());
            passwordField.setText(user.getPassword()); // Display the password (consider security implications)
            displayUsernameLabel.setText(user.getUsername());

            // Load profile image if it exists
            if (user.getProfilePic() != null && !user.getProfilePic().isEmpty()) {
                File imgFile = new File(user.getProfilePic());
                if (imgFile.exists()) {
                    profileImageView.setImage(new Image(imgFile.toURI().toString())); // Set image view with the profile picture
                }
            }

            // Populate friends ListView
            friendsListView.getItems().setAll(user.getFriends());
        }
    }

    /**
     * Handles changing the user's profile picture.
     */
    @FXML
    private void handleChangeProfilePic() {
        FileChooser fileChooser = new FileChooser(); // Create a file chooser for selecting images
        fileChooser.setTitle("Select Profile Picture");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        // Show open dialog and wait for the user's selection
        File selectedFile = fileChooser.showOpenDialog(profileImageView.getScene().getWindow());
        if (selectedFile != null) {
            String imagePath = selectedFile.getAbsolutePath();
            user.setProfilePic(imagePath); // Update the user's profile picture path
            profileImageView.setImage(new Image(selectedFile.toURI().toString())); // Update the profile image view
            try {
                UserDataManager.addUser(user); // Save changes to the database
                Utils.showPopUp("Success", "Profile Picture Changed", "Your profile picture has been updated.", Alert.AlertType.INFORMATION);
            } catch (Exception e) {
                e.printStackTrace(); // Print stack trace on error
                Utils.showPopUp("Error", "Changing Profile Picture", "An error occurred while changing the profile picture.", Alert.AlertType.ERROR);
            }
        } else {
            Utils.showPopUp("Warning", "No File Selected", "You did not select any file.", Alert.AlertType.WARNING);
        }
    }

    /**
     * Switches to the Chat view.
     *
     * @throws IOException If there is an error during the switch.
     */
    @FXML
    private void switchToChat() throws IOException {
        App.setRoot("chat"); // Navigate to the chat view
    }
}
