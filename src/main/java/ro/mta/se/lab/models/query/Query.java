package ro.mta.se.lab.models.query;

public class Query {

    static String query;

    public Query(String query) {
        this.query = query;
    }

    public static String getQuery() {
        return query;
    }
}
