package ro.mta.se.lab.models.weatherClient.responseParser;

import com.google.gson.annotations.SerializedName;

/* Data used for interface */
public class WeatherDescription {

    @SerializedName("id")
    private String id;

    @SerializedName("main")
    private String main;

    @SerializedName("description")
    private String description;

    /* Weather icon id */
    @SerializedName("icon")
    private String icon;

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}
