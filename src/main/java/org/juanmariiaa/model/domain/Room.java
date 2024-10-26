package org.juanmariiaa.model.domain;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "room")
@XmlAccessorType(XmlAccessType.FIELD)
public class Room {

    private String roomName;
    private User administrator;  // Nuevo atributo: el creador/administrador de la sala

    @XmlElementWrapper(name = "messages")
    @XmlElement(name = "message")
    private List<Message> messages;

    // Constructor vacío, necesario para JAXB
    public Room() {
        this.messages = new ArrayList<>();
    }

    // Constructor con parámetros (incluyendo administrador)
    public Room(String roomName, User administrator) {
        this.roomName = roomName;
        this.administrator = administrator;
        this.messages = new ArrayList<>();
    }

    // Getters y Setters
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public User getAdministrator() {
        return administrator;
    }

    public void setAdministrator(User administrator) {
        this.administrator = administrator;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    // Método para agregar un mensaje a la sala
    public void addMessage(Message message) {
        this.messages.add(message);
    }

    // Método toString para visualizar la sala y los mensajes
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Room: ").append(roomName).append("\n")
                .append("Administrator: ").append(administrator.getUsername()).append("\n");
        for (Message message : messages) {
            sb.append(message.toString()).append("\n");
        }
        return sb.toString();
    }
}
