package ro.mta.se.lab.models.weatherClient.responseParser;

import com.google.gson.annotations.SerializedName;

public class MainParameters {

    @SerializedName("temp")
    private double temperature;
    @SerializedName("pressure")
    private double pressure;
    @SerializedName("humidity")
    private double humidity;

    public double getTemperature() {
        return temperature;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void display() {
        System.out.println("temp: " + temperature + " pressure: " + pressure + " humidity: " + humidity);
    }

}
