package org.juanmariiaa.model.dao;

import org.juanmariiaa.model.domain.Room;
import org.juanmariiaa.model.domain.User;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserDataManager {

    private static final String USERS_XML_FILE = "users.xml";  // Ruta del archivo XML de usuarios
    private List<User> users;  // Lista de usuarios en memoria

    // Constructor
    public UserDataManager() {
        File file = new File(USERS_XML_FILE);
        if (!file.exists()) {
            // Si el archivo no existe, inicializamos la lista y creamos el archivo
            users = new ArrayList<>();
            saveUsersToXML();
        } else {
            // Si el archivo existe, lo cargamos
            users = loadUsersFromXML();
        }
    }

    // Método para crear un nuevo usuario
    public void createUser(String username, String password, String firstName, String lastName) {
        User newUser = new User(username, password, firstName, lastName);
        users.add(newUser);
        saveUsersToXML();  // Guardamos los cambios en el archivo XML
    }

    // Método para agregar un amigo a un usuario existente
    public void addFriend(String username, User friend) {
        User user = getUserByUsername(username);
        if (user != null) {
            user.addFriend(friend);
            saveUsersToXML();  // Guardamos los cambios en el archivo XML
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    /*// Método para crear una sala y asignar un administrador
    public void createRoom(String username, String roomName) {
        User user = getUserByUsername(username);
        if (user != null) {
            Room newRoom = new Room(roomName, user);  // Se asigna el usuario como administrador
            user.addCreatedRoom(newRoom);  // Añadir la sala a la lista de salas creadas por el usuario
            saveUsersToXML();  // Guardamos los cambios en el archivo XML
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }*/

    // Método para buscar un usuario por su nombre de usuario
    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    // Método para cargar la lista de usuarios desde el archivo XML
    private List<User> loadUsersFromXML() {
        try {
            JAXBContext context = JAXBContext.newInstance(UserWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            UserWrapper wrapper = (UserWrapper) unmarshaller.unmarshal(new File(USERS_XML_FILE));
            return wrapper.getUsers();
        } catch (JAXBException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Método para guardar la lista de usuarios en el archivo XML
    private void saveUsersToXML() {
        try {
            JAXBContext context = JAXBContext.newInstance(UserWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            UserWrapper wrapper = new UserWrapper();
            wrapper.setUsers(users);
            marshaller.marshal(wrapper, new File(USERS_XML_FILE));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todos los usuarios (útil para la interfaz)
    public List<User> getAllUsers() {
        return users;
    }

    // Método para eliminar un usuario (opcional)
    public void deleteUser(String username) {
        User user = getUserByUsername(username);
        if (user != null) {
            users.remove(user);
            saveUsersToXML();
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }
}
