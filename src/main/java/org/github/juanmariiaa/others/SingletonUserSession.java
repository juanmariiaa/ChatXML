package org.github.juanmariiaa.others;

import org.github.juanmariiaa.model.domain.User;

/**
 * Singleton class to manage the user session.
 */
public class SingletonUserSession {

    private static SingletonUserSession instance;
    private User user;

    // Private constructor to prevent instantiation
    private SingletonUserSession() {}

    /**
     * Gets the single instance of the SingletonUserSession.
     *
     * @return SingletonUserSession instance.
     */
    public static SingletonUserSession getInstance() {
        if (instance == null) {
            instance = new SingletonUserSession();
        }
        return instance;
    }

    /**
     * Sets the user session data after a successful login.
     *
     * @param user The User object of the logged-in user.
     */
    public static void login(User user) {
        SingletonUserSession session = getInstance();
        session.user = user;
    }

    /**
     * Clears the session data on logout.
     */
    public static void logout() {
        SingletonUserSession session = getInstance();
        session.user = null;
    }

    /**
     * Gets the logged-in User object.
     *
     * @return The User object.
     */
    public User getUser() {
        return user;
    }

    /**
     * Gets the logged-in user's username.
     *
     * @return The username.
     */
    public String getUsername() {
        return user != null ? user.getUsername() : null;
    }

    /**
     * Checks if a user is currently logged in.
     *
     * @return True if a user is logged in; false otherwise.
     */
    public boolean isLoggedIn() {
        return user != null;
    }
}