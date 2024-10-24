package org.juanmariiaa.model.domain;

import org.juanmariiaa.XML.LocalDateTimeAdapter;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;


@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class Message {

    private String sender;  // El remitente del mensaje

    // Lista de destinatarios
    @XmlElementWrapper(name = "receivers")
    @XmlElement(name = "receiver")
    private List<String> receivers;
    private String content;
    // Adaptador para la fecha y hora
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime timestamp;

    // Constructor vacío para JAXB
    public Message() {
    }

    // Constructor para múltiples destinatarios (mensajes en grupo)
    public Message(String sender, List<String> receivers, String content, LocalDateTime timestamp) {
        this.sender = sender;
        this.receivers = receivers;
        this.content = content;
        this.timestamp = timestamp;
    }

    // Constructor para un solo destinatario (mensaje directo)
    public Message(String sender, String receiver, String content, LocalDateTime timestamp) {
        this.sender = sender;
        this.receivers = Collections.singletonList(receiver);  // Solo un receptor
        this.content = content;
        this.timestamp = timestamp;
    }



    // Getters y Setters
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<String> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<String> receivers) {
        this.receivers = receivers;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    // Método toString
    @Override
    public String toString() {
        return "Message{" +
                "sender='" + sender + '\'' +
                ", receivers=" + receivers +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
