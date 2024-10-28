package org.github.juanmariiaa.others;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.github.juanmariiaa.model.dao.MessageDataManager;
import org.github.juanmariiaa.model.domain.Friend;
import org.github.juanmariiaa.model.domain.Message;
import org.github.juanmariiaa.model.domain.User;
import org.github.juanmariiaa.utils.Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * The ChatExporter class provides functionality to export chat messages
 * between a user and a selected friend to a text file.
 */
public class ChatExporter {

    private final MessageDataManager messageDataManager;

    /**
     * Constructs a ChatExporter with the given MessageDataManager.
     *
     * @param messageDataManager The MessageDataManager instance to manage message retrieval.
     */
    public ChatExporter(MessageDataManager messageDataManager) {
        this.messageDataManager = messageDataManager;
    }

    /**
     * Initiates the download process for the chat between the current user and their selected friend.
     * Displays a file chooser to allow the user to select where to save the chat.
     *
     * @param currentUser   The current user requesting the chat download.
     * @param currentFriend The friend whose chat is being exported.
     * @param stage         The current JavaFX stage for displaying dialogs.
     */
    public void downloadChat(User currentUser, Friend currentFriend, Stage stage) {
        if (currentFriend == null) {
            Utils.showPopUp("Error", "No Friend Selected", "Please select a friend to download the chat.", Alert.AlertType.ERROR);
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Chat");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            exportMessagesToFile(currentUser, currentFriend, file);
        }
    }

    /**
     * Exports the messages between the current user and their friend to a specified file.
     *
     * @param currentUser   The current user requesting the chat export.
     * @param currentFriend The friend whose messages are being exported.
     * @param file          The file to which the chat will be exported.
     */
    private void exportMessagesToFile(User currentUser, Friend currentFriend, File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            List<Message> messages = messageDataManager.getMessagesBetweenUsers(currentUser.getUsername(), currentFriend.getUsername());

            for (Message msg : messages) {
                String messageLine = msg.getSender().equals(currentUser.getUsername())
                        ? "You (" + msg.getTimestamp() + "): " + msg.getContent()
                        : currentFriend.getUsername() + " (" + msg.getTimestamp() + "): " + msg.getContent();
                writer.write(messageLine);
                writer.newLine();
            }

            Utils.showPopUp("Success", "Chat Exported", "The chat has been successfully exported to " + file.getAbsolutePath(), Alert.AlertType.INFORMATION);
        } catch (IOException e) {
            e.printStackTrace();
            Utils.showPopUp("Error", "Export Failed", "An error occurred while exporting the chat. Please try again.", Alert.AlertType.ERROR);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
