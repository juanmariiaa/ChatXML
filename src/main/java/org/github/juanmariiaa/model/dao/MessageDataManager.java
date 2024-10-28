package org.github.juanmariiaa.model.dao;

import org.github.juanmariiaa.model.domain.Message;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The MessageDataManager class provides methods to manage message data, including adding messages,
 * retrieving them, and saving them to an XML file. It uses JAXB (Java Architecture for XML Binding)
 * for marshaling and unmarshaling XML data.
 */
public class MessageDataManager {
    private static File file = new File("messages.xml");

    /**
     * Adds a new message to the XML file.
     * Retrieves existing messages, adds the new message, and saves the updated list.
     *
     * @param message The message to be added.
     * @throws Exception If an error occurs during the process.
     */
    public static void addMessage(Message message) throws Exception {
        List<Message> messages = recoverMessages();
        messages.add(message);
        saveMessages(messages);
    }

    /**
     * Recovers messages from the XML file.
     * If the file exists and is not empty, unmarshals the content into a MessageWrapper object and returns the list of messages.
     * If the file does not exist or is empty, returns an empty list.
     *
     * @return A list of messages.
     * @throws JAXBException If an error occurs during unmarshalling.
     */
    public static List<Message> recoverMessages() throws JAXBException {
        if (file.exists() && file.length() > 0) {
            JAXBContext context = JAXBContext.newInstance(MessageWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            MessageWrapper wrapper = (MessageWrapper) unmarshaller.unmarshal(file);
            return wrapper.getMessages();
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Initializes the XML file if it does not exist.
     * If the file is not found, creates a new file and saves an empty message list to it.
     *
     * @throws Exception If an error occurs during file creation.
     */
    private static void initializeMessageFile() throws Exception {
        if (!file.exists()) {
            file.createNewFile();
            saveMessages(new ArrayList<>());
        }
    }

    /**
     * Saves the provided list of messages to the XML file.
     * Wraps the list of messages in a MessageWrapper and marshals the wrapper object to the XML file.
     *
     * @param messages The list of messages to be saved.
     * @throws Exception If an error occurs during the process.
     */
    public static void saveMessages(List<Message> messages) throws Exception {
        MessageWrapper wrapper = new MessageWrapper();
        wrapper.setMessages(messages);

        JAXBContext context = JAXBContext.newInstance(MessageWrapper.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(wrapper, file);
    }

    /**
     * Filters and retrieves messages exchanged between two specified users.
     * Checks each message in the list of all messages to determine if the sender and receiver match the specified users.
     *
     * @param user1 The first user.
     * @param user2 The second user.
     * @return A list of messages exchanged between the two users.
     * @throws Exception If an error occurs during the process.
     */
    public List<Message> getMessagesBetweenUsers(String user1, String user2) throws Exception {
        List<Message> allMessages = getAllMessages();
        List<Message> filteredMessages = new ArrayList<>();
        for (Message msg : allMessages) {
            if ((msg.getSender().equals(user1) && msg.getReceiver().equals(user2)) ||
                    (msg.getSender().equals(user2) && msg.getReceiver().equals(user1))) {
                filteredMessages.add(msg);
            }
        }
        return filteredMessages;
    }

    /**
     * Retrieves all messages stored in the XML file.
     * Initializes the message file if it does not exist, then unmarshals the content into a MessageWrapper object.
     * If the wrapper contains messages, returns them; otherwise, returns an empty list.
     *
     * @return A list of all messages.
     * @throws Exception If an error occurs during the process.
     */
    public List<Message> getAllMessages() throws Exception {
        initializeMessageFile();
        if (file.length() > 0) {
            JAXBContext context = JAXBContext.newInstance(MessageWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            MessageWrapper wrapper = (MessageWrapper) unmarshaller.unmarshal(file);
            return wrapper.getMessages() != null ? wrapper.getMessages() : new ArrayList<>();
        } else {
            return new ArrayList<>();
        }
    }
}
