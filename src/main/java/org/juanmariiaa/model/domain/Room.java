package org.juanmariiaa.model.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "room")
public class Room implements Serializable {
    int idRoom;

    @XmlElement(name = "msgs", type= MsgsList.class)
    MsgsList msgList;

    public Room(int idRoom) {
        this.idRoom = idRoom;
        this.msgList = new MsgsList();
    }

    public Room() {
        this.idRoom = 0;
        this.msgList = new MsgsList();
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public MsgsList getMsgList() {
        return msgList;
    }

    public void setMsgList(MsgsList msgList) {
        this.msgList = msgList;
    }

    public void addMsg(Msg msg){
        this.msgList.addMsg(msg);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return idRoom == room.idRoom;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRoom);
    }

    @Override
    public String toString() {
        return "Room{" +
                "idRoom=" + idRoom +
                ", msgList=" + msgList +
                '}';
    }
}
