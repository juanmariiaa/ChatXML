package org.juanmariiaa.test;

import org.juanmariiaa.model.dao.RoomDAO;
import org.juanmariiaa.model.domain.Room;

public class test1 {
    public static void main(String[] args) {
        Room r=new Room(1);

        RoomDAO.writeXML(r,"libro.xml");

        System.out.println(r);
    }

}
