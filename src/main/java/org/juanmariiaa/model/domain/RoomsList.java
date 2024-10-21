package org.juanmariiaa.model.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "rooms")
public class RoomsList implements Serializable {

    @XmlElement(name = "room", type=String.class)
    private List<String> roomsId;

    public RoomsList() {
        this.roomsId = new ArrayList<>();
    }

    public RoomsList(List<String> roomList) {
        this.roomsId = roomList;
    }

    public List<String> getRooms() {
        return roomsId;
    }

    public void setRoom(List<String> roomsId) {
        this.roomsId = roomsId;
    }

    public void setRoom(RoomsList rooms){
        this.roomsId = rooms.getRooms();
    }

    public void addRoom(Room room){
        this.roomsId.add(String.valueOf(room.getIdRoom()));
    }

    public void addRoom(String roomId){
        this.roomsId.add(roomId);
    }


    @Override
    public String toString() {
        return "Rooms{" +
                "rooms=" + roomsId +
                '}';
    }
}
