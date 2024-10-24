package org.juanmariiaa.model.dao;

import org.juanmariiaa.model.domain.Room;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "rooms")
public class RoomWrapper {

    private List<Room> rooms;

    @XmlElement(name = "room")
    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}