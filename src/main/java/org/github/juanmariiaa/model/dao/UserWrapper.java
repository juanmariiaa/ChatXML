package org.github.juanmariiaa.model.dao;

import org.github.juanmariiaa.model.domain.User;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * The UserWrapper class acts as a container for a list of User objects.
 * It is annotated with JAXB annotations to facilitate XML serialization and deserialization.
 */
@XmlRootElement(name = "users")
public class UserWrapper {
    private List<User> users;

    /**
     * Gets the list of users.
     *
     * @return A list of User objects.
     */
    @XmlElement(name = "user")
    public List<User> getUsers() {
        return users;
    }

    /**
     * Sets the list of users.
     *
     * @param users A list of User objects to be set.
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }
}
