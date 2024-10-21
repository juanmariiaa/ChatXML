package org.juanmariiaa.model.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "users")
public class UsersList {

    @XmlElement(name = "user", type=User.class)
    private List<User> users;

    public UsersList(){
        this.users = new ArrayList<>();
    }

    public UsersList(List<User> userList){
        this.users = userList;
    }

    public List<User> getUsers(){
        return this.users;
    }

    public void setUsersList(List<User> userList){
        this.users = userList;
    }

    public void setUser(User user){
        this.users.add(user);
    }

    @Override
    public String toString() {
        return "Users{" +
                "users=" + users +
                '}';
    }

    public List<User> usersInRoom(int room) {
        List<User> inRoom = new ArrayList<>();
        for (User user: users) {
            if (user.getCurrentRoom() == room){
                inRoom.add(user);
            }
        }
        return inRoom;
    }
}
