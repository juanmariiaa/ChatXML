package org.juanmariiaa.model.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Config")
public class Config {
    private String fileRooms;
    private String fileUsers;
    private String pathFileRoom;

    public Config(){
        this.fileRooms = "rooms.xml";
        this.fileUsers = "users.xml";
        this.pathFileRoom = "room.xml";
    }

    public Config(String fileRooms, String fileUsers, String pathFileRoom){
        this.fileRooms = fileRooms;
        this.fileUsers = fileUsers;
        this.pathFileRoom = pathFileRoom;
    }

    public String getFileRooms() {
        return fileRooms;
    }

    public void setFileRooms(String fileRooms) {
        this.fileRooms = fileRooms;
    }

    public String getFileUsers() {
        return fileUsers;
    }

    public void setFileUsers(String fileUsers) {
        this.fileUsers = fileUsers;
    }

    public String getPathFileRoom() {
        return pathFileRoom;
    }

    public void setPathFileRoom(String pathFileRoom) {
        this.pathFileRoom = pathFileRoom;
    }

    @Override
    public String toString() {
        return "Config{" +
                "fileRooms='" + fileRooms + '\'' +
                ", fileUsers='" + fileUsers + '\'' +
                ", pathFileRoom='" + pathFileRoom + '\'' +
                '}';
    }
}