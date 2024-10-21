package org.juanmariiaa.model.dao;

import org.juanmariiaa.model.domain.RoomsList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class RoomsDAO {
    public static boolean writeXML(RoomsList c, String fileName) {
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

    public static RoomsList readXML(RoomsList c, String filename) {
        RoomsList result = c;
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(c.getClass());
            Unmarshaller um = context.createUnmarshaller();
            result = (RoomsList) um.unmarshal(new File(filename));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return result;
    }
}
