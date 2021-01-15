package ro.mta.se.lab.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ro.mta.se.lab.Main;
import ro.mta.se.lab.models.fileParsers.FileParser;
import ro.mta.se.lab.models.query.Query;
import ro.mta.se.lab.models.query.QueryBuilder;
import ro.mta.se.lab.models.weatherClient.WeatherClient;
import ro.mta.se.lab.models.weatherClient.responseParser.CurrentWeather;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class WeatherViewController {

    @FXML
    private StackPane mainPane;

    @FXML
    private Label city;

    @FXML
    private Label description;

    @FXML
    private Label temperature;

    @FXML
    private Label pressure;

    @FXML
    private Label humidity;

    @FXML
    private Label wind;

    @FXML
    private Button helpButton;

    @FXML
    private ImageView weatherIcon;

    @FXML
    private Label updateTime;

    @FXML
    private ComboBox<String> countrySource;

    @FXML
    private ComboBox<String> citySource;

    private CurrentWeather weatherData;
    private List<String> countryList;
    private List<String> cityList;
    private boolean isSet = false, isset2 = false;
    private DialogController dialogController;

    FileParser fileParser = new FileParser();

    private static final String apiKey = "7825d02704566ebfd21e11059f727ccb";

    private void prepareView() throws Exception {

        fileParser.readConfFile("files/inputRO.txt");
        countryList = fileParser.getCountryList();

        /* Adaugare coduri de tari la Combo Box */
        ObservableList<String> obs = FXCollections.observableArrayList();

        obs.addAll(countryList);
        countrySource.setItems(obs);
    }

    private void prepareControls() {
        Image image = new Image(getClass().getResourceAsStream("/icons/question.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(25);
        imageView.setFitWidth(25);
        helpButton.setGraphic(imageView);

        helpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (dialogController == null) {
                    dialogController = new DialogController();
                }

                FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/dialog-help.fxml"));
                loader.setController(dialogController);
                Scene scene = null;
                try {
                    scene = new Scene(loader.load(), 290, 200);
                    Stage stage = new Stage();
                    stage.setResizable(false);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(scene);
                    stage.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    private void initialize() throws Exception {

        prepareView();
        prepareControls();

        /* Configurare lista orase din tara selectata */
        countrySource.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                citySource.getSelectionModel().select(-1);

                System.out.println("Country is: " + newValue);
                cityList = fileParser.getCityList(newValue);

                ObservableList<String> obs = FXCollections.observableArrayList();
                obs.addAll(cityList);
                citySource.setItems(obs);
                isSet = false;
            }
        });

        /* Obtine orasul selectat */
        citySource.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (citySource.getSelectionModel().getSelectedIndex() == -1)
                    return;

                System.out.println("City is: " + newValue);

                /* Obtine date despre vreme in orasul selectat */
                Query currentWeatherQuery = QueryBuilder.getInstance(apiKey).currentWeather().byCityID(fileParser.getSpecificCityId(newValue)).unitFormat("metric").build();

                WeatherClient weatherClient = new WeatherClient();
                weatherData = weatherClient.getCurrentWeather(currentWeatherQuery);

                QueryBuilder.getInstance(apiKey).clean();

                /* Adauga datele despre vreme in interfata */
                city.setText(weatherData.getCityName());
                description.setText(weatherData.getDescription());
                temperature.setText(weatherData.getTemperature() + "°C");

                pressure.setText("Pressure: " + weatherData.getPressure() + " hPa");
                humidity.setText("Humidity: " + weatherData.getHumidity() + "%");
                wind.setText("Wind: " + weatherData.getWindSpeed() + " km/h");

                /* Adauga timpul */
                SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm a");
                updateTime.setText(sdf.format(Calendar.getInstance().getTime()));

                /* Adauga icon */
                weatherIcon.setImage(new Image(Main.class.getResourceAsStream("/icons/" + weatherData.getIcon() + ".png")));
                weatherIcon.setVisible(true);

            }
        });
    }
}
