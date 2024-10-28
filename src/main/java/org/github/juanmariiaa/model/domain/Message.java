package org.github.juanmariiaa.model.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Message class represents a message exchanged between users in the system.
 * It contains information about the sender, receiver, content of the message, and a timestamp.
 * This class is annotated with JAXB annotations to facilitate XML serialization and deserialization.
 */
@XmlRootElement(name = "message")
@XmlType(propOrder = {"sender", "receiver", "content", "timestamp"})
public class Message {
    private String sender;
    private String receiver;
    private String content;
    private String timestamp;

    /**
     * Default constructor that initializes the timestamp to the current time.
     */
    public Message() {
        this.timestamp = generateTimestamp();
    }

    /**
     * Constructor to create a Message with specified sender, receiver, and content.
     * The timestamp is automatically generated.
     *
     * @param sender   The username of the sender.
     * @param receiver The username of the receiver.
     * @param content  The content of the message.
     */
    public Message(String sender, String receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timestamp = generateTimestamp();
    }

    /**
     * Generates a timestamp in the format "dd-MM HH:mm:ss".
     *
     * @return A formatted timestamp string.
     */
    private String generateTimestamp() {
        return new SimpleDateFormat("dd-MM HH:mm:ss").format(new Date());
    }

    @XmlElement
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @XmlElement
    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @XmlElement
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @XmlElement
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
