package org.github.juanmariiaa.test;

import org.github.juanmariiaa.model.dao.UserDataManager;
import org.github.juanmariiaa.model.domain.User;

public class test {
    public static void main(String[] args) {
        UserDataManager userDataManager = new UserDataManager();
        try {
            // Test adding a user
            User user = new User("testUser", "Test", "password123", "Tester");
            userDataManager.addUser(user);
            System.out.println("User added successfully.");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
