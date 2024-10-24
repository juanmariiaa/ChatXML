package org.juanmariiaa.model.domain;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)  // JAXB accede directamente a los campos
@XmlRootElement(name = "user")
public class User implements Serializable {

    private String username;
    private String password;
    private String firstName;
    private String lastName;

    @XmlElementWrapper(name = "friends")
    @XmlElement(name = "friend")
    private List<User> friends;

    @XmlElementWrapper(name = "createdRooms")  // Lista de salas creadas por el usuario
    @XmlElement(name = "room")
    private List<Room> roomsCreadas;

    // Constructor vacío, necesario para JAXB
    public User() {
        this.friends = new ArrayList<>();
        this.roomsCreadas = new ArrayList<>();
    }

    // Constructor con parámetros
    public User(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.friends = new ArrayList<>();
        this.roomsCreadas = new ArrayList<>();
    }

    // Getters y Setters
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

    public List<Room> getRoomsCreadas() {
        return roomsCreadas;
    }

    public void setRoomsCreadas(List<Room> roomsCreadas) {
        this.roomsCreadas = roomsCreadas;
    }

    // Método para agregar un amigo
    public void addFriend(User friend) {
        this.friends.add(friend);
    }

    // Método para agregar una sala creada
    public void addCreatedRoom(Room room) {
        this.roomsCreadas.add(room);
    }

    // Método toString para representar el usuario como cadena
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", friends=" + friends.size() +
                ", createdRooms=" + roomsCreadas.size() +
                '}';
    }

    // Método equals y hashCode para comparar usuarios (puede ser útil)
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
