package org.juanmariiaa.model.dao;

import org.juanmariiaa.model.domain.Room;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class RoomDAO {
    public static boolean writeXML(Room c, String fileName) {
        boolean result = false;
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(c.getClass());
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            m.marshal(c, new File(fileName));
            result = true;
        } catch (JAXBException e) {
            e.printStackTrace(); //mode development
        }
        return result;
    }

    public static Room readXML(Room c, String filename) {
        Room result = c;
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(c.getClass());
            Unmarshaller um = context.createUnmarshaller();
            result = (Room) um.unmarshal(new File(filename));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return result;
    }
}