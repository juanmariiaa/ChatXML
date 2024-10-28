package org.github.juanmariiaa.model.dao;

import org.github.juanmariiaa.model.domain.Friend;
import org.github.juanmariiaa.model.domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The UserDataManager class manages user data, including adding users, retrieving user information,
 * managing user contacts, and validating user credentials. It uses JAXB for XML serialization and deserialization.
 */
public class UserDataManager {
    private static File file = new File("users.xml");

    /**
     * Creates a new XML file if it does not exist and initializes it with an empty UserWrapper.
     *
     * @throws Exception If an error occurs during file creation or writing.
     */
    private static void writeXMLUser() throws Exception {
        if (!file.exists()) {
            file.createNewFile();
        }
        UserWrapper wrapper = new UserWrapper();
        wrapper.setUsers(new ArrayList<>());
        JAXBContext context = JAXBContext.newInstance(UserWrapper.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(wrapper, file);
    }

    /**
     * Adds a new user to the XML file.
     * If the file does not exist, it initializes it first.
     *
     * @param user The user to be added.
     * @throws Exception If an error occurs during the process.
     */
    public static void addUser(User user) throws Exception {
        if (!file.exists()) {
            writeXMLUser();
        }
        List<User> users = getAllUsers();
        users.add(user);
        saveUsers(users);
    }

    /**
     * Retrieves all users from the XML file.
     * If the file is empty or does not exist, it initializes it.
     *
     * @return A list of User objects.
     * @throws Exception If an error occurs during the process.
     */
    public static List<User> getAllUsers() throws Exception {
        if (file.exists() && file.length() > 0) {
            JAXBContext context = JAXBContext.newInstance(UserWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            UserWrapper wrapper = (UserWrapper) unmarshaller.unmarshal(file);
            return wrapper.getUsers() != null ? wrapper.getUsers() : new ArrayList<>();
        } else {
            writeXMLUser();
            return new ArrayList<>();
        }
    }

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user to retrieve.
     * @return The User object if found, otherwise null.
     * @throws Exception If an error occurs during the process.
     */
    public User getUserByUsername(String username) throws Exception {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Adds a friend to a user's contact list.
     *
     * @param username The username of the user to whom the friend will be added.
     * @param friend   The Friend object to be added.
     * @throws Exception If an error occurs during the process.
     */
    public static void addContactToUser(String username, Friend friend) throws Exception {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                if (user.getFriends() == null) {
                    user.setFriends(FXCollections.observableArrayList());
                }
                user.getFriends().add(friend);
                saveUsers(users);
                return;
            }
        }
    }

    /**
     * Saves the list of users back to the XML file.
     *
     * @param users The list of users to be saved.
     * @throws Exception If an error occurs during the process.
     */
    public static void saveUsers(List<User> users) throws Exception {
        UserWrapper wrapper = new UserWrapper();
        wrapper.setUsers(users);

        JAXBContext context = JAXBContext.newInstance(UserWrapper.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(wrapper, file);
    }

    /**
     * Removes a friend from a user's contact list.
     *
     * @param username The username of the user from whom the friend will be removed.
     * @param friend   The Friend object to be removed.
     * @throws Exception If an error occurs during the process.
     */
    public static void removeFriend(String username, Friend friend) throws Exception {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                user.getFriends().remove(friend);
                saveUsers(users);
                return;
            }
        }
    }

    /**
     * Validates user credentials by checking the username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The User object if credentials are valid.
     * @throws Exception If the username is not found or the password is incorrect.
     */
    public static User validateUserCredentials(String username, String password) throws Exception {
        List<User> users = getAllUsers();

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                if (password.equals(user.getPassword())) {
                    return user;
                } else {
                    throw new Exception("Invalid password.");
                }
            }
        }
        throw new Exception("User not found.");
    }

    /**
     * Checks if a user exists based on their username.
     *
     * @param username The username to check.
     * @return True if the user exists, otherwise false.
     * @throws Exception If an error occurs during the process.
     */
    public static boolean exists(String username) throws Exception {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
