package org.juanmariiaa.model.domain;

import org.juanmariiaa.XML.LocalTimeAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "msg")
public class Msg implements Serializable {
    private String user;
    private String msgContent;
    @XmlJavaTypeAdapter(value = LocalTimeAdapter.class)
    private LocalTime time;

    public Msg(String user, String msgContent, LocalTime time) {
        this.user = user;
        this.msgContent = msgContent;
        this.time = time;
    }

    public Msg() {
        this.user = null;
        this.msgContent = "";
        this.time = null;
    }

    public String getUserName() {
        return user;
    }

    public void setUserName(String user) {
        this.user = user;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public LocalTime getDate() {
        return time;
    }

    public void setDate(LocalTime time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Msg msg = (Msg) o;
        return Objects.equals(user, msg.user) && Objects.equals(time, msg.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, time);
    }

    @Override
    public String toString() {
        return "Msg{" +
                "userName='" + user + '\'' +
                ", msgContent='" + msgContent + '\'' +
                ", date=" + time +
                '}';
    }
}
