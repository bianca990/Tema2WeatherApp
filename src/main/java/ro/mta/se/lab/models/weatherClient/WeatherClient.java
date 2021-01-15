package ro.mta.se.lab.models.weatherClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ro.mta.se.lab.models.query.Query;
import ro.mta.se.lab.models.weatherClient.responseParser.CurrentWeather;

public class WeatherClient {

    private UrlConnection urlConnection = new UrlConnection();
    private final Gson gson = new GsonBuilder().create();

    public CurrentWeather getCurrentWeather(Query query) {

        /* Get response data from the weather server */
        String data = urlConnection.makeRequest(query);

        CurrentWeather currentWeather = gson.fromJson(data, CurrentWeather.class);
        currentWeather.display();

        return currentWeather;
    }
}
