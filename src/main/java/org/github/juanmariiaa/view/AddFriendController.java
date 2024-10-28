package org.github.juanmariiaa.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.github.juanmariiaa.model.dao.UserDataManager;
import org.github.juanmariiaa.model.domain.Friend;
import org.github.juanmariiaa.model.domain.User;
import org.github.juanmariiaa.others.SingletonUserSession; // Importing the SingletonUserSession class
import org.github.juanmariiaa.utils.Utils;

import java.io.IOException;

/**
 * Controller for managing the "Add Friend" functionality in the application.
 * This class handles the user interface interactions for adding a friend by username.
 */
public class AddFriendController {

    @FXML
    private TextField tfUsername; // TextField for entering the friend's username

    private final UserDataManager userDataManager = new UserDataManager(); // Data manager for user-related operations
    private Friend newFriend; // Variable to store the newly added friend

    /**
     * Event handler for the "Add Friend" button.
     * Invokes the method to add a friend when the button is clicked.
     */
    @FXML
    public void btAddFriend() {
        addFriend(); // Call the method to add a friend
    }

    /**
     * Adds a friend to the current user's contact list based on the entered username.
     * Validates the username, checks if the user exists, and adds the friend if valid.
     */
    private void addFriend() {
        String username = tfUsername.getText().trim(); // Get and trim the input from the TextField

        // Check if the input field is empty
        if (username.isEmpty()) {
            Utils.showPopUp("Error", "Empty field", "Please enter a valid username.", Alert.AlertType.ERROR);
        } else {
            try {
                // Check if the user with the entered username exists
                User friend = userDataManager.getUserByUsername(username);
                if (friend != null) {
                    User currentUser = SingletonUserSession.getInstance().getUser(); // Get the currently logged-in user
                    if (currentUser != null) {
                        // Check if the friend is already in the user's friend list
                        if (currentUser.getFriends().stream().anyMatch(f -> f.getUsername().equals(friend.getUsername()))) {
                            Utils.showPopUp("Amigo ya añadido", "Amigo existente", "Este amigo ya está en tu lista.", Alert.AlertType.INFORMATION);
                        } else {
                            newFriend = new Friend(friend.getUsername()); // Create a new Friend object
                            userDataManager.addContactToUser(currentUser.getUsername(), newFriend); // Add the friend to the user's contact list
                            currentUser.getFriends().add(newFriend); // Update the current user's friend list
                            Utils.showPopUp("Success", "Friend added", "You have successfully added " + friend.getName() + " as a friend.", Alert.AlertType.INFORMATION);
                            close(); // Close the window after successfully adding the friend
                        }
                    } else {
                        Utils.showPopUp("Error", "No user logged in", "Please log in to add friends.", Alert.AlertType.ERROR);
                    }
                } else {
                    Utils.showPopUp("Error", "User not found", "The username does not exist. Please try again.", Alert.AlertType.ERROR);
                }
            } catch (Exception e) {
                // Handle potential exceptions
                Utils.showPopUp("Error", "Database error", "Please try again later.", Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        }
    }

    /**
     * Gets the newly added friend.
     *
     * @return the newly added Friend object
     */
    public Friend getNewFriend() {
        return newFriend; // Return the newly added friend
    }

    /**
     * Closes the current window.
     */
    @FXML
    private void close() {
        Stage stage = (Stage) tfUsername.getScene().getWindow(); // Get the current stage from the TextField's scene
        stage.close(); // Close the stage
    }
}
