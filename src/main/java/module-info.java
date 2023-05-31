module com.example.project {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.persistence;
	requires java.sql;
	requires org.hibernate.orm.core;
	
	opens com.example.project.database.entities to org.hibernate.orm.core;
	
    opens com.example.project to javafx.fxml;
    exports com.example.project;
    exports com.example.project.popup;
    opens com.example.project.popup to javafx.fxml;
    exports com.example.project.gui11;
    opens com.example.project.gui11 to javafx.fxml;
    exports com.example.project.gui21;
    opens com.example.project.gui21 to javafx.fxml;
    exports com.example.project.gui32;
    opens com.example.project.gui32 to javafx.fxml;
    exports com.example.project.gui33;
    opens com.example.project.gui33 to javafx.fxml;
    exports com.example.project.gui34;
    opens com.example.project.gui34 to javafx.fxml;
    exports com.example.project.gui51;
    opens com.example.project.gui51 to javafx.fxml;
    exports com.example.project.gui61;
    opens com.example.project.gui61 to javafx.fxml;
    
}