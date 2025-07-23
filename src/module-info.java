module POO.Editora.entrega {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.desktop;

    opens main.Entities to org.hibernate.orm.core;

    opens main.view to javafx.fxml;
    exports main.view;
    exports main.Controller;
    opens main.Controller to javafx.fxml;
}