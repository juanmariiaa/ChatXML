module org.juanmariiaa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires java.activation;

    opens org.juanmariiaa to javafx.fxml, java.xml.bind;
    opens org.juanmariiaa.model.domain to java.xml.bind; // This should be sufficient
    opens org.juanmariiaa.model.dao to java.xml.bind;

    exports org.juanmariiaa;
}
