package Example_03;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public class Handler extends URLStreamHandler {
    @Override
    protected int getDefaultPort() {
        return QuizURLConnection.DEFAULT_PORT;
    }

    @Override
    protected URLConnection openConnection(URL url) throws IOException {
        return new QuizURLConnection(url);
    }
}
