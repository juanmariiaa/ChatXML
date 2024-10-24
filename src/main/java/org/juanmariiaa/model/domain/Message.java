package org.juanmariiaa.model.domain;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD) // Use FIELD access to avoid conflicts with getters
@XmlRootElement(name = "message")
public class Message implements Serializable {

    @XmlElement(name = "sender") // XML element for the sender
    private String sender;

    @XmlElement(name = "receiver") // XML element for the receiver
    private String receiver;

    @XmlElement(name = "content") // XML element for the content of the message
    private String content;

    @XmlElement(name = "timestamp") // XML element for the timestamp
    private String timestamp;

    // Default constructor
    public Message() {
        this.timestamp = generateTimestamp(); // Generate timestamp on creation
    }

    // Constructor with parameters
    public Message(String sender, String receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timestamp = generateTimestamp(); // Generate timestamp on creation
    }

    // Method to generate a timestamp in the desired format
    private String generateTimestamp() {
        return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
    }

    // Getters and Setters
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    // toString method
    @Override
    public String toString() {
        return "Message{" +
                "sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", content='" + content + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

    // equals and hashCode methods
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Message message = (Message) obj;
        return sender.equals(message.sender) && receiver.equals(message.receiver);
    }

    @Override
    public int hashCode() {
        int result = sender.hashCode();
        result = 31 * result + receiver.hashCode();
        return result;
    }
}
