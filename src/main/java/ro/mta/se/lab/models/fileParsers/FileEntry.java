package ro.mta.se.lab.models.fileParsers;

public class FileEntry {

    private String cityId;
    private String cityName;
    private String lat;
    private String lon;
    private String countryCode;

    public FileEntry(String cityId, String cityName, String lat, String lon, String countryCode) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.lat = lat;
        this.lon = lon;
        this.countryCode = countryCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }
}
