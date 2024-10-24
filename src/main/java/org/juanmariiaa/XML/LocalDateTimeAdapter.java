package org.juanmariiaa.XML;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    // Definir el formato de fecha y hora, puedes cambiarlo según tus necesidades
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    // Método para deserializar (de String a LocalDateTime)
    @Override
    public LocalDateTime unmarshal(String v) throws Exception {
        return LocalDateTime.parse(v, formatter);
    }

    // Método para serializar (de LocalDateTime a String)
    @Override
    public String marshal(LocalDateTime v) throws Exception {
        return v.format(formatter);
    }
}
