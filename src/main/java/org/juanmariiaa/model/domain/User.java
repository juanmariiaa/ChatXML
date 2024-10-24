package org.juanmariiaa.model.domain;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD) // Use FIELD access to avoid conflicts with getters
@XmlRootElement(name = "user")
public class User implements Serializable {

    @XmlElement(name = "username")  // XML element for the username
    private String username;

    @XmlElement(name = "password")  // XML element for the password
    private String password;

    @XmlElement(name = "firstName")  // XML element for the first name
    private String firstName;

    @XmlElement(name = "lastName")  // XML element for the last name
    private String lastName;

    @XmlElementWrapper(name = "friends")  // Wrap the friends list in a <friends> element
    @XmlElement(name = "friend")  // Define each user friend as a <friend> element
    private List<User> friends;

    // Default constructor
    public User() {
        this.friends = new ArrayList<>();
    }

    // Constructor with parameters
    public User(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.friends = new ArrayList<>();
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    // Method to add a friend
    public void addFriend(User friend) {
        this.friends.add(friend);
    }

    // toString method
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", friends=" + friends.size() +
                '}';
    }

    // equals and hashCode methods
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}
