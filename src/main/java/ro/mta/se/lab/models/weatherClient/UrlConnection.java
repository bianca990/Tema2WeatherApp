package ro.mta.se.lab.models.weatherClient;

import ro.mta.se.lab.models.query.Query;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UrlConnection {

    private static final String ACCEPT_CHARSET = "Accept-Charset";
    private static final String UTF_8 = "UTF-8";

    public String makeRequest(Query query) {

        try {
            URL url = getUrl(query.getQuery());
            URLConnection urlConnection = url.openConnection();
            urlConnection.setRequestProperty(ACCEPT_CHARSET, UTF_8);
            return readResponse(urlConnection);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String readResponse(URLConnection urlConnection) {

        StringBuilder response = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line = br.readLine();
            do {
                response.append(line);
                line = br.readLine();
            } while (line != null);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return response.toString();
    }

    public URL getUrl(String query) {
        try {
            return new URL(query);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Malformed query", e);
        }
    }
}
