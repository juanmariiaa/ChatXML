module org.juanmariiaa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires java.activation;

    opens org.juanmariiaa to javafx.fxml, java.xml.bind;
    exports org.juanmariiaa;
}
