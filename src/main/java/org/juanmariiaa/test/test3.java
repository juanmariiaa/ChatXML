package org.juanmariiaa.test;

import org.juanmariiaa.model.dao.UserDataManager;
import org.juanmariiaa.model.domain.User;

import java.io.File;
import java.util.List;

public class test3 {
    private static final String TEST_XML_FILE = "users.xml"; // Nombre del archivo XML de prueba

    public static void main(String[] args) {
        // Limpiar el archivo XML de prueba antes de la ejecución
        cleanTestFile();

        // Crear una instancia de UserDataManager
        UserDataManager userDataManager = new UserDataManager(TEST_XML_FILE);

        // Crear y agregar nuevos usuarios
        User user1 = new User("john_doe", "securePassword123");
        User user2 = new User("jane_doe", "anotherSecurePassword456");

        userDataManager.addUser(user1);
        userDataManager.addUser(user2);

        // Leer todos los usuarios
        List<User> users = userDataManager.getAllUsers();

        // Mostrar los usuarios leídos
        System.out.println("Usuarios registrados:");
        for (User user : users) {
            System.out.println("Username: " + user.getUsername() + ", Password: " + user.getPassword());
        }
    }

    // Método para limpiar el archivo de prueba
    private static void cleanTestFile() {
        File testFile = new File(TEST_XML_FILE);
        if (testFile.exists()) {
            testFile.delete();  // Eliminar el archivo existente
        }
    }
}
