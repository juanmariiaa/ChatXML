package org.juanmariiaa.model.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@XmlAccessorType(XmlAccessType.FIELD)  // JAXB accede directamente a los campos
@XmlRootElement(name = "user")
public class User implements Serializable {

    private String username;
    private String password;  // Podrías implementar un cifrado simple más adelante si es necesario

    // Constructor vacío, necesario para JAXB
    public User() {
    }

    // Constructor con parámetros
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters y Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Método toString opcional para representar el usuario como cadena
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }

    // Método equals y hashCode para comparar usuarios (puede ser útil)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}