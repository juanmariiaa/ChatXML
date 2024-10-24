package org.juanmariiaa.others;

import org.juanmariiaa.model.domain.User;

public class SingletonUserSession {
    private static SingletonUserSession _instance;
    private static User userLogged;

    private SingletonUserSession() {

    }

    public static SingletonUserSession getInstance() {
        if (_instance == null) {
            _instance = new SingletonUserSession();
        }
        return _instance;
    }


    public static void login(User user) {
        userLogged = user;
    }

    public static void logout() {
        userLogged = null;
    }

    public static User getCurrentUser() {
        return userLogged;
    }
}
