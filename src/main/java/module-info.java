module org.juanmariiaa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires java.activation;

    opens org.juanmariiaa to javafx.fxml, java.xml.bind;
    opens org.juanmariiaa.model.domain to java.xml.bind; // Open this package to JAXB

    exports org.juanmariiaa;
}
