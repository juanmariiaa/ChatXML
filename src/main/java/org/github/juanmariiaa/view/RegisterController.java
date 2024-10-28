package org.github.juanmariiaa.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.github.juanmariiaa.utils.Utils;
import org.github.juanmariiaa.model.dao.UserDataManager;
import org.github.juanmariiaa.model.domain.User;

import java.io.IOException;

/**
 * Controller for handling user registration in the application.
 * This class manages the registration process, including input validation,
 * user creation, and error handling.
 */
public class RegisterController {

    @FXML
    private TextField tfName; // Text field for entering the user's name
    @FXML
    private TextField tfLastName; // Text field for entering the user's surname
    @FXML
    private TextField tfUsername; // Text field for entering the desired username
    @FXML
    private PasswordField tfPassword; // Password field for entering the user's password

    private final UserDataManager userDataManager = new UserDataManager(); // Manager for user data operations

    /**
     * Method invoked when the user clicks the sign-up button.
     * It triggers the user registration process.
     */
    @FXML
    public void btSignUp() {
        addUser(); // Call method to add the new user
    }

    /**
     * Adds a new user to the system based on the information entered in the text fields.
     * This method performs validation checks on the input fields before proceeding to create the user.
     */
    @FXML
    private void addUser() {
        String name = tfName.getText().trim(); // Retrieve and trim the name input
        String surname = tfLastName.getText().trim(); // Retrieve and trim the surname input
        String username = tfUsername.getText().trim(); // Retrieve and trim the username input
        String password = tfPassword.getText().trim(); // Retrieve and trim the password input

        // Validate that all fields are filled
        if (name.isEmpty() || surname.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Utils.showPopUp("Error", "Empty fields", "Please complete all fields to continue.", Alert.AlertType.ERROR);
        } else if (username.length() < 4) { // Check username length
            Utils.showPopUp("Error", "Invalid username", "Username must be at least 4 characters long.", Alert.AlertType.ERROR);
        } else if (!isValidPassword(password)) { // Validate password complexity
            Utils.showPopUp("Error", "Invalid password", "Password must be more complex.", Alert.AlertType.ERROR);
        } else {
            try {
                // Check if the username already exists in the database
                if (userDataManager.exists(username)) {
                    Utils.showPopUp("Error", "Username already exists", "Please choose another username.", Alert.AlertType.ERROR);
                } else {
                    // Create a new user object and save it
                    User user = new User(username, name, password, surname);
                    userDataManager.addUser(user); // Use UserDataManager to save the user

                    Utils.showPopUp("Success", "User created", "User created successfully.", Alert.AlertType.INFORMATION);
                    switchToLogin(); // Switch to the login screen after successful registration
                }
            } catch (Exception e) {
                Utils.showPopUp("Error", "Database error", "Please try again later.", Alert.AlertType.ERROR);
                e.printStackTrace(); // Print the stack trace for debugging
            }
        }
    }

    /**
     * Switches the view to the login screen.
     *
     * @throws IOException If there is an error while switching screens.
     */
    @FXML
    private void switchToLogin() {
        try {
            App.setRoot("login"); // Navigate to the login view
        } catch (IOException e) {
            e.printStackTrace(); // Print the stack trace if there is an error
        }
    }

    /**
     * Validates the complexity of the entered password.
     * The password must meet the following criteria:
     * - At least 8 characters long
     * - Contains at least one uppercase letter
     * - Contains at least one lowercase letter
     * - Contains at least one digit
     * - Contains at least one special character
     *
     * @param password The entered password.
     * @return True if the password meets complexity requirements, false otherwise.
     */
    public static boolean isValidPassword(String password) {
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") && // At least one uppercase letter
                password.matches(".*[a-z].*") && // At least one lowercase letter
                password.matches(".*\\d.*") && // At least one digit
                password.matches(".*[^a-zA-Z0-9].*"); // At least one special character
    }
}
