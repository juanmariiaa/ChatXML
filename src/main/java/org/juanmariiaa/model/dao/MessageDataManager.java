package org.juanmariiaa.model.dao;

import org.juanmariiaa.model.domain.Message;
import org.juanmariiaa.model.domain.Room;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageDataManager {

    private static final String MESSAGES_XML_FILE = "messages.xml";  // Ruta del archivo XML de mensajes
    private List<Message> messages;  // Lista de mensajes en memoria

    // Constructor
    public MessageDataManager() {
        File file = new File(MESSAGES_XML_FILE);
        if (!file.exists()) {
            // Si el archivo no existe, inicializamos la lista y creamos el archivo
            messages = new ArrayList<>();
            saveMessagesToXML();
        } else {
            // Si el archivo existe, lo cargamos
            messages = loadMessagesFromXML();
        }
    }

    // Método para agregar un mensaje a una sala
    public void addMessageToRoom(Room room, String content, String sender) {
        // Creando un nuevo mensaje con una lista de receptores vacía y la hora actual
        Message newMessage = new Message(sender, Collections.emptyList(), content, LocalDateTime.now());

        room.addMessage(newMessage);  // Añadiendo el mensaje a la sala
        messages.add(newMessage);  // También lo guardamos en la lista general de mensajes
        saveMessagesToXML();  // Guardamos los cambios en el archivo XML
    }

    public void addDirectMessage(String sender, String receiver, String content) {
        Message newMessage = new Message(sender, receiver, content, LocalDateTime.now());
        messages.add(newMessage);  // Guardamos el mensaje en la lista general de mensajes
        saveMessagesToXML();  // Guardamos los cambios en el archivo XML
    }

    // Método para listar todos los mensajes de una sala
    public List<Message> getMessagesByRoom(Room room) {
        return room.getMessages();
    }

    // Método para cargar los mensajes desde el archivo XML
    private List<Message> loadMessagesFromXML() {
        try {
            JAXBContext context = JAXBContext.newInstance(MessageWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            MessageWrapper wrapper = (MessageWrapper) unmarshaller.unmarshal(new File(MESSAGES_XML_FILE));
            return wrapper.getMessages();
        } catch (JAXBException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Método para guardar los mensajes en el archivo XML
    private void saveMessagesToXML() {
        try {
            JAXBContext context = JAXBContext.newInstance(MessageWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            MessageWrapper wrapper = new MessageWrapper();
            wrapper.setMessages(messages);
            marshaller.marshal(wrapper, new File(MESSAGES_XML_FILE));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todos los mensajes (útil para la interfaz)
    public List<Message> getAllMessages() {
        return messages;
    }
}
