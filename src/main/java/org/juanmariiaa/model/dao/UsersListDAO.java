package org.juanmariiaa.model.dao;

import org.juanmariiaa.model.domain.UsersList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class UsersListDAO {

    /**
     * Serializa el objeto UsersList a XML y lo guarda en el archivo especificado.
     *
     * @param usersList El objeto UsersList a serializar.
     * @param fileName El nombre del archivo donde se guardará el XML.
     * @throws JAXBException Si ocurre algún error durante la serialización.
     * @throws IllegalArgumentException Si el objeto UsersList o el nombre del archivo son nulos o vacíos.
     */
    public static void writeXML(UsersList usersList, String fileName) throws JAXBException {
        if (usersList == null || fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("El objeto UsersList y el nombre del archivo no pueden ser nulos o vacíos.");
        }

        JAXBContext context = JAXBContext.newInstance(UsersList.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

        marshaller.marshal(usersList, new File(fileName));
    }

    /**
     * Lee un archivo XML y deserializa su contenido en un objeto UsersList.
     *
     * @param filename El nombre del archivo XML.
     * @return El objeto UsersList deserializado.
     * @throws JAXBException Si ocurre algún error durante la deserialización.
     * @throws IllegalArgumentException Si el archivo no existe o el nombre del archivo es nulo o vacío.
     */
    public static UsersList readXML(String filename) throws JAXBException {
        if (filename == null || filename.isEmpty()) {
            throw new IllegalArgumentException("El nombre del archivo no puede ser nulo o vacío.");
        }

        File file = new File(filename);
        if (!file.exists()) {
            throw new IllegalArgumentException("El archivo " + filename + " no existe.");
        }

        JAXBContext context = JAXBContext.newInstance(UsersList.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (UsersList) unmarshaller.unmarshal(file);
    }
}
