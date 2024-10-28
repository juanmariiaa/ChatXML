package org.github.juanmariiaa.model.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The User class represents a user in the system, including their personal details
 * and a list of friends. It is annotated with JAXB annotations to facilitate XML
 * serialization and deserialization.
 */
@XmlRootElement
@XmlType(propOrder = {"username", "name", "password", "lastName", "profilePic", "friends"})
public class User {
    private String username;
    private String password;
    private String name;
    private String lastName;
    private String profilePic;
    private List<Friend> friends;

    /**
     * Default constructor initializing the friends list.
     */
    public User() {
        friends = new ArrayList<>();
    }

    /**
     * Constructor to create a User with mandatory details.
     *
     * @param username The username of the user.
     * @param name     The name of the user.
     * @param password The password of the user.
     * @param lastName The last name of the user.
     */
    public User(String username, String name, String password, String lastName) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.lastName = lastName;
        this.friends = new ArrayList<>();
    }

    /**
     * Constructor to create a User with mandatory details and profile picture.
     *
     * @param username  The username of the user.
     * @param name      The name of the user.
     * @param password  The password of the user.
     * @param lastName  The last name of the user.
     * @param profilePic The profile picture URL of the user.
     */
    public User(String username, String name, String password, String lastName, String profilePic) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.lastName = lastName;
        this.profilePic = profilePic;
        this.friends = new ArrayList<>();
    }

    @XmlElement
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlElement
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlElement
    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    @XmlElement
    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", lastName='" + lastName + '\'' +
                ", profilePic='" + profilePic + '\'' +
                '}';
    }
}
