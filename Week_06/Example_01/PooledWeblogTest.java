package Example_01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

final class PooledWeblogTest {

    public static void main(String[] args) throws FileNotFoundException {

        // We wish to create a class which will have an Input-Output Stream API
        // Now we maybe have a file where the log is dumped but we might as well
        // be downloading it online via InputStream and give it to our class which
        // will process it without any modifications in the code. Similarly, the
        // output is not hardcoded as well - we can write the result where we want.
        PooledWeblog tw = new PooledWeblog(
                new FileInputStream(
                        "c06_URL_URLConnection/r01_pooled_weblog/apache.logfile"
                ),
                System.out,
                6
        );

        tw.processLogFile();
    }

}
