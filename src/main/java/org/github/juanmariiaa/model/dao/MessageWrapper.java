package org.github.juanmariiaa.model.dao;

import org.github.juanmariiaa.model.domain.Message;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * The MessageWrapper class serves as a container for a list of Message objects.
 * It is annotated with JAXB annotations to facilitate XML serialization and deserialization.
 */
@XmlRootElement(name = "messages")
public class MessageWrapper {
    private List<Message> messages = new ArrayList<>();

    /**
     * Gets the list of messages.
     *
     * @return A list of Message objects.
     */
    @XmlElement(name = "message")
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * Sets the list of messages.
     *
     * @param messages A list of Message objects to be set.
     */
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
