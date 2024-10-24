package org.juanmariiaa.test;

import org.juanmariiaa.model.domain.User;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

public class UserTest {

    public static void main(String[] args) {
        try {
            // Create a User instance
            User user = new User("johndoe", "password123", "John", "Doe");
            User friend1 = new User("friend1", "friendpass1", "Friend", "One");
            User friend2 = new User("friend2", "friendpass2", "Friend", "Two");

            // Add friends to the user
            user.addFriend(friend1);
            user.addFriend(friend2);

            // Specify the file where the XML will be saved
            File file = new File("user.xml");

            // Create JAXB context and marshaller
            JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // Enable pretty printing

            // Serialize the User object to XML
            marshaller.marshal(user, file);
            System.out.println("User has been marshaled to XML: " + file.getAbsolutePath());

            // Deserialize the User object from XML
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            User deserializedUser = (User) unmarshaller.unmarshal(file);
            System.out.println("Deserialized User: " + deserializedUser);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
