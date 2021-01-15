module ro.mta.se.lab {
    requires javafx.controls;
    requires javafx.fxml;
    requires jfoenix;
    requires com.google.gson;

    opens ro.mta.se.lab to javafx.fxml;
    opens ro.mta.se.lab.controllers to javafx.fxml;
    opens ro.mta.se.lab.models.weatherClient.responseParser to com.google.gson;

    exports ro.mta.se.lab;
    exports ro.mta.se.lab.controllers;
    exports ro.mta.se.lab.models.weatherClient.responseParser;
}