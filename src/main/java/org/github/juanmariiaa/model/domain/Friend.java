package org.github.juanmariiaa.model.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The Friend class represents a friend of a user in the system.
 * It contains the friend's username and a list of messages exchanged with that friend.
 * This class is annotated with JAXB annotations to facilitate XML serialization and deserialization.
 */
@XmlRootElement(name = "friend")
public class Friend {
    private String username;
    private List<Message> messages;

    /**
     * Default constructor.
     */
    public Friend() {
    }

    /**
     * Constructor to create a Friend with a specified username.
     * Initializes the messages list.
     *
     * @param username The username of the friend.
     */
    public Friend(String username) {
        this.username = username;
        this.messages = new ArrayList<>();
    }

    /**
     * Gets the username of the friend.
     *
     * @return The username of the friend.
     */
    @XmlElement
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the friend.
     *
     * @param username The new username of the friend.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the list of messages exchanged with this friend.
     * If the messages list is null, it initializes a new list.
     *
     * @return A list of messages exchanged with the friend.
     */
    @XmlElement
    public List<Message> getMessages() {
        if (messages == null) { // Checks if messages is null
            messages = new ArrayList<>();
        }
        return messages;
    }

    /**
     * Sets the list of messages exchanged with this friend.
     *
     * @param messages The new list of messages.
     */
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Friend)) return false;
        Friend friend = (Friend) o;
        return Objects.equals(username, friend.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return username;
    }
}
