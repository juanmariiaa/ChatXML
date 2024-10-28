module com.github.juanmariiaa {
    requires javafx.controls;       // Required for JavaFX controls
    requires javafx.fxml;          // Required for using FXML
    requires java.xml.bind;        // Required for JAXB

    // Allow JavaFX to access FXML files and controllers in these packages
    opens org.github.juanmariiaa to javafx.fxml; // Allows JavaFX to access root package for FXML
    opens org.github.juanmariiaa.view to javafx.fxml; // Necessary for FXML loading
    // opens org.github.juanmariiaa.model.domain to java.xml.bind; // Allows JAXB to access domain classes
    // opens org.github.juanmariiaa.model.dao to java.xml.bind; // Allows JAXB to access DAO classes

    // Allow JAXB to access classes in these packages
    opens org.github.juanmariiaa.utils to java.xml.bind; // Allows JAXB to access classes in Utils package
    opens org.github.juanmariiaa.model.domain to java.xml.bind; // Allows JAXB to access domain classes
    opens org.github.juanmariiaa.model.dao to java.xml.bind; // Allows JAXB to access DAO classes

    // Exports for public access
    exports org.github.juanmariiaa; // Exports root package for public access
    exports org.github.juanmariiaa.model.domain; // Exports domain model package
    exports org.github.juanmariiaa.test; // Exports test package, if needed
    exports org.github.juanmariiaa.view; // Exports view package for public access
}
