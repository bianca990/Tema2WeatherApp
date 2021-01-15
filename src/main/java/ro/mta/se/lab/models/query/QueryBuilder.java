package ro.mta.se.lab.models.query;

/* Singleton and builder */

public class QueryBuilder {

    /* Singleton */
    private static QueryBuilder instance = null;

    String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    String QUESTION_MARK = "?";
    String AND = "&";
    String apiKey;
    StringBuilder stringBuilder;

    private QueryBuilder(String apiKey) {

        /* Baza queriului */
        stringBuilder = new StringBuilder(BASE_URL);

        /* Adauga APIKEY*/
        this.apiKey = apiKey;
    }

    public static QueryBuilder getInstance(String apiKey) {

        if (instance == null)
            instance = new QueryBuilder(apiKey);

        return instance;
    }

    public QueryBuilder currentWeather() {

        /* Adauga 'weather' la query */
        stringBuilder.append("weather");

        /* Adauga ? la query */
        stringBuilder.append(QUESTION_MARK);

        return this;
    }

    public QueryBuilder byCityID(String id) {

        /* city cu id= */
        stringBuilder.append("id=");
        stringBuilder.append(id);

        return this;
    }

    public QueryBuilder byCityName(String cityName) {

        stringBuilder.append("q=");
        stringBuilder.append(cityName);

        return this;

    }

    public QueryBuilder byCityName(String cityName, String countryCode) {

        stringBuilder.append("q=");
        stringBuilder.append(cityName + "," + countryCode);

        return this;

    }

    public QueryBuilder byZipCode(String zipCode, String countryCode) {

        stringBuilder.append("zip=");
        stringBuilder.append(zipCode + "," + countryCode);

        return this;
    }

    public QueryBuilder byCoords(String lat, String lon) {

        stringBuilder.append("lat=" + lat);
        stringBuilder.append(AND);
        stringBuilder.append("lon=" + lon);

        return this;
    }

    public QueryBuilder unitFormat(String unitsFormat) {

        /* Celsius format*/
        stringBuilder.append(AND);
        stringBuilder.append("units=" + unitsFormat);

        return this;
    }

    public Query build() {

        stringBuilder.append(AND);
        stringBuilder.append("APPID=" + apiKey);

        /* constructia query */
        return new Query(stringBuilder.toString());
    }

    public QueryBuilder clean() {
        stringBuilder = new StringBuilder(BASE_URL);
        return this;

    }

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }

}
