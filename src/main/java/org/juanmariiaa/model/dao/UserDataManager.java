package org.juanmariiaa.model.dao;

import org.juanmariiaa.model.domain.User;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserDataManager {

    private final File xmlFile;

    // Constructor que recibe la ruta del archivo XML
    public UserDataManager(String filePath) {
        this.xmlFile = new File(filePath);
    }

    // Método para leer todos los usuarios del archivo XML
    public List<User> getAllUsers() {
        try {
            if (!xmlFile.exists()) {
                return new ArrayList<>();  // Si no existe el archivo, retornamos una lista vacía
            }

            // Crear un contexto JAXB para la clase User
            JAXBContext context = JAXBContext.newInstance(UserWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Leer y deserializar el archivo XML en el wrapper
            UserWrapper wrapper = (UserWrapper) unmarshaller.unmarshal(xmlFile);

            return wrapper.getUsers();
        } catch (JAXBException e) {
            e.printStackTrace();
            return new ArrayList<>();  // Si ocurre un error, devolvemos una lista vacía
        }
    }

    // Método para añadir un nuevo usuario y guardar en el archivo XML
    public void addUser(User user) {
        try {
            List<User> users = getAllUsers();  // Obtener la lista actual de usuarios

            // Añadir el nuevo usuario
            users.add(user);

            // Crear el contexto JAXB para la clase User
            JAXBContext context = JAXBContext.newInstance(UserWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Envolver la lista de usuarios en el wrapper y escribirla al archivo XML
            UserWrapper wrapper = new UserWrapper();
            wrapper.setUsers(users);

            marshaller.marshal(wrapper, xmlFile);  // Escribir el archivo XML

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
