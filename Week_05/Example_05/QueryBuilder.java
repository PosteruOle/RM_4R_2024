package Example_05;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

// Constructs a GET request URL by concatenating base URL and query key-value pairs
// BASE_URL?key1=value1&key2=value2& ... &keyN=valueN
public class QueryBuilder {
    private StringBuffer query;
    private boolean hasQuery;

    public QueryBuilder(String baseUrl) {
        this.query = new StringBuffer(baseUrl);
    }

    public void append(String name, String value) {
        this.query.append(this.hasQuery ? '&' : '?');
        this.hasQuery = true;
        this.encode(name, value);
    }

    private void encode(String name, String value) {
        this.query.append(URLEncoder.encode(name, StandardCharsets.UTF_8));
        this.query.append('=');
        this.query.append(URLEncoder.encode(value, StandardCharsets.UTF_8));
    }

    @Override
    public String toString() {
        return this.query.toString();
    }

    public URL toUrl() throws MalformedURLException {
        return new URL(this.toString());
    }
}
