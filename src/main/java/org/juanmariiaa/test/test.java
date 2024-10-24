package org.juanmariiaa.test;

import org.juanmariiaa.model.dao.UserDataManager;
import org.juanmariiaa.model.domain.User;

public class test {
    public static void main(String[] args) {
        UserDataManager userDataManager = new UserDataManager();

        // Crear un nuevo usuario
        System.out.println("Creando nuevo usuario...");
        userDataManager.createUser("juan", "password123", "Juan", "Pérez");

        // Listar todos los usuarios
        System.out.println("Usuarios:");
        for (User user : userDataManager.getAllUsers()) {
            System.out.println(user);
        }


    }
}
