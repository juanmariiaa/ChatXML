package org.github.juanmariiaa.utils;

import javafx.scene.control.Alert;
import javafx.stage.Stage;


public class Utils {

    /**
     * Displays a pop-up alert dialog with the specified parameters.
     *
     * @param title   The title of the alert dialog.
     * @param header  The header text displayed in the alert dialog.
     * @param text    The content text displayed in the alert dialog.
     * @param type    The type of alert (e.g., INFORMATION, ERROR, CONFIRMATION).
     * @return The Alert object representing the displayed alert.
     */
    public static Alert showPopUp(String title, String header, String text, Alert.AlertType type) {
        Alert alertDialog = new Alert(type);
        alertDialog.setTitle(title);
        alertDialog.setHeaderText(header);
        alertDialog.setContentText(text);

        alertDialog.show();

        // Bring the alert dialog to the front of the application window
        Stage s = (Stage) alertDialog.getDialogPane().getScene().getWindow();
        s.toFront();

        return alertDialog;
    }
}
