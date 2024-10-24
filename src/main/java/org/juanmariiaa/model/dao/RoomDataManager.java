package org.juanmariiaa.model.dao;

import org.juanmariiaa.model.domain.Message;
import org.juanmariiaa.model.domain.Room;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomDataManager {

    private final File xmlFile;

    // Constructor que recibe la ruta del archivo XML
    public RoomDataManager(String filePath) {
        this.xmlFile = new File(filePath);
    }

    // Método para leer todas las salas de chat desde el archivo XML
    public List<Room> getAllRooms() {
        try {
            if (!xmlFile.exists()) {
                return new ArrayList<>();  // Si no existe el archivo, retornamos una lista vacía
            }

            // Crear el contexto JAXB para RoomWrapper
            JAXBContext context = JAXBContext.newInstance(RoomWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Leer y deserializar el archivo XML
            RoomWrapper wrapper = (RoomWrapper) unmarshaller.unmarshal(xmlFile);

            return wrapper.getRooms();
        } catch (JAXBException e) {
            e.printStackTrace();
            return new ArrayList<>();  // Si ocurre un error, devolvemos una lista vacía
        }
    }

    // Método para añadir una nueva sala de chat
    public void addRoom(Room room) {
        try {
            List<Room> rooms = getAllRooms();  // Obtener la lista actual de salas

            // Añadir la nueva sala
            rooms.add(room);

            // Crear el contexto JAXB para RoomWrapper
            JAXBContext context = JAXBContext.newInstance(RoomWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Envolver la lista de salas en el wrapper y escribirla al archivo XML
            RoomWrapper wrapper = new RoomWrapper();
            wrapper.setRooms(rooms);

            marshaller.marshal(wrapper, xmlFile);  // Escribir el archivo XML

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    // Método para agregar un mensaje a una sala específica
    public void addMessageToRoom(String roomName, Message message) {
        try {
            List<Room> rooms = getAllRooms();  // Obtener todas las salas

            // Buscar la sala por su nombre
            Optional<Room> roomOpt = rooms.stream()
                    .filter(room -> room.getRoomName().equals(roomName))
                    .findFirst();

            if (roomOpt.isPresent()) {
                Room room = roomOpt.get();
                room.getMessages().add(message);  // Agregar el mensaje a la sala

                // Actualizar el archivo XML con la nueva lista de mensajes
                JAXBContext context = JAXBContext.newInstance(RoomWrapper.class);
                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

                RoomWrapper wrapper = new RoomWrapper();
                wrapper.setRooms(rooms);

                marshaller.marshal(wrapper, xmlFile);  // Escribir el archivo actualizado
            } else {
                System.out.println("Room not found: " + roomName);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener todos los mensajes de una sala de chat específica
    public List<Message> getMessagesFromRoom(String roomName) {
        List<Room> rooms = getAllRooms();

        return rooms.stream()
                .filter(room -> room.getRoomName().equals(roomName))
                .findFirst()
                .map(Room::getMessages)
                .orElse(new ArrayList<>());  // Si no encuentra la sala, retorna una lista vacía
    }
}
