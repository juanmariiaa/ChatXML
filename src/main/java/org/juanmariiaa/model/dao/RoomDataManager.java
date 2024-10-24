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

public class RoomDataManager {

    private static final String ROOMS_XML_FILE = "rooms.xml";  // Ruta del archivo XML de salas
    private List<Room> rooms;  // Lista de salas en memoria

    // Constructor
    public RoomDataManager() {
        File file = new File(ROOMS_XML_FILE);
        if (!file.exists()) {
            // Si el archivo no existe, inicializamos la lista y creamos el archivo
            rooms = new ArrayList<>();
            saveRoomsToXML();
        } else {
            // Si el archivo existe, lo cargamos
            rooms = loadRoomsFromXML();
        }
    }

    // Método para crear una nueva sala
    public void createRoom(String roomName, User administrator) {
        Room newRoom = new Room(roomName, administrator);
        rooms.add(newRoom);
        saveRoomsToXML();  // Guardamos los cambios en el archivo XML
    }

    // Método para buscar una sala por su nombre
    public Room getRoomByName(String roomName) {
        for (Room room : rooms) {
            if (room.getRoomName().equals(roomName)) {
                return room;
            }
        }
        return null;
    }

    // Método para eliminar una sala
    public void deleteRoom(String roomName) {
        Room room = getRoomByName(roomName);
        if (room != null) {
            rooms.remove(room);
            saveRoomsToXML();  // Guardamos los cambios en el archivo XML
        } else {
            System.out.println("Sala no encontrada.");
        }
    }

    // Método para listar todas las salas (útil para la interfaz)
    public List<Room> getAllRooms() {
        return rooms;
    }

    // Método para cargar la lista de salas desde el archivo XML
    private List<Room> loadRoomsFromXML() {
        try {
            JAXBContext context = JAXBContext.newInstance(RoomWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            RoomWrapper wrapper = (RoomWrapper) unmarshaller.unmarshal(new File(ROOMS_XML_FILE));
            return wrapper.getRooms();
        } catch (JAXBException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Método para guardar la lista de salas en el archivo XML
    private void saveRoomsToXML() {
        try {
            JAXBContext context = JAXBContext.newInstance(RoomWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            RoomWrapper wrapper = new RoomWrapper();
            wrapper.setRooms(rooms);
            marshaller.marshal(wrapper, new File(ROOMS_XML_FILE));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
