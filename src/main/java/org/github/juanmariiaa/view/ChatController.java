package org.github.juanmariiaa.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.github.juanmariiaa.model.dao.MessageDataManager;
import org.github.juanmariiaa.model.dao.UserDataManager;
import org.github.juanmariiaa.model.domain.Friend;
import org.github.juanmariiaa.model.domain.Message;
import org.github.juanmariiaa.model.domain.User;
import org.github.juanmariiaa.others.ChatAnalyzer;
import org.github.juanmariiaa.others.ChatExporter;
import org.github.juanmariiaa.others.SingletonUserSession;
import org.github.juanmariiaa.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * ChatController class handles the chat interface interactions.
 * It allows users to send messages, download chat history, analyze chats,
 * and manage the user's friend list.
 */
public class ChatController {

    @FXML
    private Button btnAddFriend, btnHome, btnProfile, btnSignOut;
    @FXML
    private Button btnDownloadChat;
    @FXML
    private Button btnAnalysis;
    @FXML
    private ListView<Friend> lvFriends;
    @FXML
    private VBox vbMessages;
    @FXML
    private TextField tfMessage;
    @FXML
    private ImageView ivSendMessage;

    private final UserDataManager userDataManager = new UserDataManager();
    private final MessageDataManager messageDataManager = new MessageDataManager();
    private final ChatExporter chatExporter;

    /**
     * Constructor for ChatController.
     * Initializes ChatExporter with MessageDataManager.
     */
    public ChatController() {
        this.chatExporter = new ChatExporter(messageDataManager);
    }

    @FXML
    private void downloadChat() {
        chatExporter.downloadChat(currentUser, currentFriend, (Stage) btnDownloadChat.getScene().getWindow());
    }

    private User currentUser;
    private Friend currentFriend;

    /**
     * Initializes the controller.
     * Loads the user's friends and sets up a listener for friend selection.
     */
    @FXML
    private void initialize() {
        currentUser = SingletonUserSession.getInstance().getUser();
        if (currentUser != null) {
            loadFriends();
        }
        lvFriends.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                currentFriend = newSelection;
                loadMessages(currentFriend);
            }
        });
    }


    /**
     * Loads the list of friends for the current user.
     */
    private void loadFriends() {
        lvFriends.getItems().clear();
        lvFriends.getItems().addAll(currentUser.getFriends());
    }


    /**
     * Loads messages between the current user and the selected friend.
     *
     * @param friend The friend to load messages with.
     */
    private void loadMessages(Friend friend) {
        vbMessages.getChildren().clear();

        try {
            List<Message> messages = messageDataManager.getMessagesBetweenUsers(currentUser.getUsername(), friend.getUsername());
            for (Message msg : messages) {
                Label msgLabel = new Label();
                // Ajuste del texto del mensaje
                msgLabel.setText(msg.getSender().equals(currentUser.getUsername())
                        ? msg.getContent() + " : You (" + msg.getTimestamp() + ")"
                        : friend.getUsername() + " (" + msg.getTimestamp() + "): " + msg.getContent());

                // Estilo de la etiqueta del mensaje
                msgLabel.setStyle("-fx-padding: 5px; -fx-border-radius: 5px; -fx-wrap-text: true;");

                // Crear un HBox para contener el mensaje
                HBox messageContainer = new HBox();
                messageContainer.setMaxWidth(600);

                if (msg.getSender().equals(currentUser.getUsername())) {
                    msgLabel.setStyle("-fx-background-color: #1DA1F2; -fx-padding: 5px; -fx-border-radius: 5px; -fx-text-fill: white;");
                    messageContainer.getChildren().add(msgLabel);
                    messageContainer.setAlignment(Pos.CENTER_RIGHT);
                } else {
                    msgLabel.setStyle("-fx-background-color: #D3D3D3; -fx-padding: 5px; -fx-border-radius: 5px; -fx-text-fill: black;");
                    messageContainer.getChildren().add(msgLabel);
                    messageContainer.setAlignment(Pos.CENTER_LEFT);
                }

                vbMessages.getChildren().add(messageContainer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Sends a message to the currently selected friend.
     */
    @FXML
    private void sendMessage() {
        String content = tfMessage.getText().trim();
        if (content.isEmpty() || currentFriend == null) {
            return;
        }

        Message message = new Message(currentUser.getUsername(), currentFriend.getUsername(), content);
        currentFriend.getMessages().add(message);
        tfMessage.clear();

        try {
            messageDataManager.addMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        loadMessages(currentFriend); // Refresca la vista de mensajes
    }


    /**
     * Analyzes the chat with the currently selected friend.
     * Allows the user to save a summary of the chat.
     *
     * @throws Exception if an error occurs during chat analysis.
     */
    @FXML
    private void analyzeChat() throws Exception {
        // Verifica si hay un amigo seleccionado
        if (currentFriend == null) {
            Utils.showPopUp("Error", "No Friend Selected", "Please select a friend to analyze the chat.", Alert.AlertType.ERROR);
            return;
        }
        List<Message> messages = messageDataManager.getMessagesBetweenUsers(currentUser.getUsername(), currentFriend.getUsername());

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Chat Summary");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File file = fileChooser.showSaveDialog((Stage) btnDownloadChat.getScene().getWindow());

        if (file != null) {
            ChatAnalyzer chatAnalyzer = new ChatAnalyzer();
            chatAnalyzer.chatAnalyzer(messages, file.getAbsolutePath());
            Utils.showPopUp("Summary Generated", "The chat summary has been generated at: " + file.getAbsolutePath(), "", Alert.AlertType.INFORMATION);
        }
    }

    /**
     * Switches to the Add Friend view.
     * Loads the addFriend.fxml layout and shows it in a new window.
     */
    @FXML
    private void switchToAddFriend() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addFriend.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Add Friend");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            loadFriends();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void switchToHome() throws IOException {
        App.setRoot("home");
    }

    @FXML
    private void switchToProfile() throws IOException {
        App.setRoot("profile");
    }

    @FXML
    private void signOut() throws IOException {
        SingletonUserSession.logout();
        App.setRoot("login");
    }
}
