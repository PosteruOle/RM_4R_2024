package Example_02;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public class Handler extends URLStreamHandler {
    @Override
    public int getDefaultPort() {
        return DaytimeURLConnection.DEFAULT_PORT;
    }

    @Override
    protected URLConnection openConnection(URL u) throws IOException {
        return new DaytimeURLConnection(u);
    }
}
