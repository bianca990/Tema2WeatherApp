package ro.mta.se.lab.models.weatherClient.responseParser;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CurrentWeather {

    @SerializedName("weather")
    private List<WeatherDescription> weatherDescription;

    @SerializedName("name")
    private String cityName;

    @SerializedName("main")
    private MainParameters mainParameters;

    @SerializedName("wind")
    private Wind wind;

    public String getCityName() {
        return cityName;
    }

    /* Main Params */
    public double getTemperature() {
        return mainParameters.getTemperature();
    }

    public double getPressure() {
        return mainParameters.getPressure();
    }

    public double getHumidity() {
        return mainParameters.getHumidity();
    }

    public String getIcon() {
        return weatherDescription.get(0).getIcon();
    }

    public double getWindSpeed() {
        return wind.getSpeed();
    }

    public String getDescription() {

        String[] arr = weatherDescription.get(0).getDescription().split(" ");
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < arr.length; i++) {
            sb.append(Character.toUpperCase(arr[i].charAt(0)))
                    .append(arr[i].substring(1)).append(" ");
        }
        return sb.toString().trim();
    }

    public void display() {
        mainParameters.display();
    }
}
