package Example_01;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeClient {

    // https://tf.nist.gov/tf-cgi/servers.cgi
    // We are using TIME protocol here (port 37)

    public static void main(String[] args) {

        String hostname = "time.nist.gov";

        // The time protocol sets the epoch at 1900, the Java Date class works
        // with 1970 as the epoch start. So we need to convert manually.

        TimeZone gmt = TimeZone.getTimeZone("GMT");
        // 		Create a calendar
        Calendar epoch1900 = Calendar.getInstance(gmt);
        //		Set it to a point in time
        epoch1900.set(1900, Calendar.JANUARY, 1, 0, 0, 0);
        //		Get number of milliseconds since that point in time
        long epoch1900ms = epoch1900.getTimeInMillis();
        //		Do same for the other point
        Calendar epoch1970 = Calendar.getInstance(gmt);
        epoch1970.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        long epoch1970ms = epoch1970.getTimeInMillis();

        //		Calculate the difference
        long epochDifferenceInMilliseconds = epoch1970ms - epoch1900ms;
        long epochDifferenceInSeconds = epochDifferenceInMilliseconds / 1000;

        try (Socket sock = new Socket(hostname, 37)) {
            InputStream raw = new BufferedInputStream(sock.getInputStream());

            long secondsSince1900 = 0;

            for (int i = 0; i < 4; i++)
                secondsSince1900 = (secondsSince1900 << 8) | raw.read();

            // Now we can calculate current time
            long secondsSince1970 = secondsSince1900 - epochDifferenceInSeconds;
            long millisecondsSince1970 = secondsSince1970 * 1000;

            Date now = new Date(millisecondsSince1970);

            System.out.println("It is " + now + " at " + hostname);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
