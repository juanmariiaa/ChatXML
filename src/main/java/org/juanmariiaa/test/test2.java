package org.juanmariiaa.test;

import org.juanmariiaa.model.dao.UsersListDAO;
import org.juanmariiaa.model.domain.User;
import org.juanmariiaa.model.domain.UsersList;

import javax.xml.bind.JAXBException;



public class test2 {
    public static void main(String[] args) {
        // Create a UsersList instance and add some users
        UsersList usersList = new UsersList();
        try {
            // Add users to the UsersList
            User user1 = new User("Juan");
            User user2 = new User("Pedro");
            User user3 = new User("Ana");

            usersList.setUser(user1);
            usersList.setUser(user2);
            usersList.setUser(user3);

            // Test UsersListDAO: Write to XML
            UsersListDAO.writeXML(usersList, "usersList.xml");
            System.out.println("UsersList saved to usersList.xml");

            // Test UsersListDAO: Read from XML
            UsersList loadedUsers = UsersListDAO.readXML("usersList.xml");
            System.out.println("Loaded UsersList: " + loadedUsers);

        } catch (IllegalArgumentException | JAXBException e) {
            System.out.println("Error while processing UsersListDAO: " + e.getMessage());
        }
    }
}



