package org.juanmariiaa.model.dao;

import org.juanmariiaa.model.domain.Message;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MessageDataManager {

    private final File xmlFile;

    // Constructor que recibe la ruta del archivo XML
    public MessageDataManager(String filePath) {
        this.xmlFile = new File(filePath);
    }

    // Método para leer todos los mensajes del archivo XML
    public List<Message> getAllMessages() {
        try {
            if (!xmlFile.exists()) {
                return new ArrayList<>();  // Si no existe el archivo, retornamos una lista vacía
            }

            // Crear el contexto JAXB para MessageWrapper
            JAXBContext context = JAXBContext.newInstance(MessageWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Leer y deserializar el archivo XML
            MessageWrapper wrapper = (MessageWrapper) unmarshaller.unmarshal(xmlFile);

            return wrapper.getMessages();
        } catch (JAXBException e) {
            e.printStackTrace();
            return new ArrayList<>();  // Si ocurre un error, devolvemos una lista vacía
        }
    }

    // Método para añadir un nuevo mensaje y guardarlo en el archivo XML
    public void addMessage(Message message) {
        try {
            List<Message> messages = getAllMessages();  // Obtener la lista actual de mensajes

            // Añadir el nuevo mensaje
            messages.add(message);

            // Crear el contexto JAXB para MessageWrapper
            JAXBContext context = JAXBContext.newInstance(MessageWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Envolver la lista de mensajes en el wrapper y escribirla al archivo XML
            MessageWrapper wrapper = new MessageWrapper();
            wrapper.setMessages(messages);

            marshaller.marshal(wrapper, xmlFile);  // Escribir el archivo XML

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener los mensajes entre dos usuarios específicos
    public List<Message> getMessagesBetween(String user1, String user2) {
        List<Message> messages = getAllMessages();

        return messages.stream()
                .filter(message -> (message.getSender().equals(user1) && message.getReceivers().contains(user2)) ||
                        (message.getSender().equals(user2) && message.getReceivers().contains(user1)))
                .collect(Collectors.toList());
    }
}