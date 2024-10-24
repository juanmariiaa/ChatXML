package org.juanmariiaa.test;

import org.juanmariiaa.model.domain.Message;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class MessageTest {
    public static void main(String[] args) {
        try {
            // Create an instance of the Message class
            Message message = new Message("user1", "user2", "Hello, User 2!");

            // Print the message to verify the values
            System.out.println("Message Object:");
            System.out.println("Sender: " + message.getSender());
            System.out.println("Receiver: " + message.getReceiver());
            System.out.println("Content: " + message.getContent());
            System.out.println("Timestamp: " + message.getTimestamp());

            // Marshal the Message object to XML file
            System.out.println("\nMarshalling to XML file...");
            marshalToXML(message);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private static void marshalToXML(Message message) throws JAXBException {
        // Create a JAXB context for the Message class
        JAXBContext context = JAXBContext.newInstance(Message.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // Specify the file to write the XML output to
        File file = new File("message.xml");

        // Marshal the message to the specified file
        marshaller.marshal(message, file);
        System.out.println("XML file created: " + file.getAbsolutePath());
    }
}
