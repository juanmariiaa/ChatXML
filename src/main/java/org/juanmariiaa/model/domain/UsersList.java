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

    /**
     * Agrega un nuevo usuario, pero asegura que no haya duplicados basados en el nombre.
     *
     * @param user El usuario a agregar.
     * @throws IllegalArgumentException si ya existe un usuario con el mismo nombre.
     */
    public void setUser(User user) throws IllegalArgumentException {
        if (isUserPresent(user.getName())) {
            throw new IllegalArgumentException("El nombre de usuario ya existe: " + user.getName());
        }
        this.users.add(user);
    }

    /**
     * Verifica si ya existe un usuario con el mismo nombre.
     *
     * @param name El nombre del usuario a verificar.
     * @return true si el nombre ya existe en la lista, false en caso contrario.
     */
    private boolean isUserPresent(String name) {
        for (User u : users) {
            if (u.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Users{" +
                "users=" + users +
                '}';
    }

    /**
     * Retorna una lista de usuarios que están en una sala específica.
     *
     * @param room El número de la sala.
     * @return Lista de usuarios en esa sala.
     */
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
