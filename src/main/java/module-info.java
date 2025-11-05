module com.cgvsu.rasterizationfxapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.cgvsu.rasterizationfxapp to javafx.fxml;
    exports com.cgvsu.rasterizationfxapp;
    exports com.cgvsu.fortask;
    opens com.cgvsu.fortask to javafx.fxml;
}