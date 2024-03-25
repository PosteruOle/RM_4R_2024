package Example_06;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Query {
    private StringBuilder url;
    private boolean alreadyHasArguments;
    public Query(String base_url) {
        url = new StringBuilder(base_url);
    }

    public void add(String key, String value) {
        url.append(!alreadyHasArguments ? "?" : "&");
        this.alreadyHasArguments = true;
        url.append(URLEncoder.encode(key, StandardCharsets.UTF_8));
        url.append("=");
        url.append(URLEncoder.encode(value, StandardCharsets.UTF_8));
    }

    public URL getURL() throws MalformedURLException {
        return new URL(this.url.toString());
    }
}
