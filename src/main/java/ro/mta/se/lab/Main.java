package ro.mta.se.lab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static final String ICON = "/icons/icon.png";
    private static final String MAIN_FXML = "views/weatherView";

    private static Scene scene;
    private Stage mainStage;


    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML(MAIN_FXML), 420, 420);
        scene.setFill(null);

        mainStage = stage;
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.getIcons().add(new Image(Main.class.getResourceAsStream(ICON)));

        mainStage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}