package Example_01;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class URLIntro {
    public static void main(String[] args) {
        // The syntax of a URL is:
        // 		protocol://userInfo@host:port/path?query#fragment
        // Here the protocol is another word for what was called the scheme
        // of the URI (scheme is the word used in the URI RFC. Protocol is
        // the word used in the Java documentation).

        /*
		 scheme             authority               path                  query        fragment
		   |                   |                     |                      |               |
		 -----   ---------------------------- ---------------  --------------------------- ---
		 |   |   |                          | |             |  |                         | | |
		 https://john.doe@www.example.com:123/forum/questions/?tag=networking&order=newest#top
		         |      | |             | | |
		         -------- --------------- ---
		            |            |         |
		         username       host      port
		 */

        URL u;

        try {
            u = new URL("http://www.matf.bg.ac.rs:8080");
            System.out.println("The host part of " + u + " is " + u.getHost());
            System.out.println("The port part of " + u + " is " + u.getPort());
            System.out.println();

            u = new URL("http://www.matf.bg.ac.rs/~ivan_ristovic/index.html");
            System.out.println("The port part of " + u + " is " + u.getPort());
            System.out.println("The default port for " + u + " is " + u.getDefaultPort());
            System.out.println("Path part of " + u + " is " + u.getPath());
            System.out.println("Authority part of " + u + " is " + u.getAuthority());
            System.out.println();

            u = new URL("mailto:ivan_ristovic@matf.bg.ac.rs");
            System.out.println("Authority part of " + u + " is " + u.getAuthority());
            System.out.println("Path part of " + u + " is " + u.getPath());
            System.out.println();

            u = new URL("http://poincare.matf.bg.ac.rs/~ivan_ristovic/");
            URLConnection uc = u.openConnection();
            System.out.println("Content-type: " + uc.getContentType());
            System.out.println("Content-encoding: " + uc.getContentEncoding());
            System.out.println("Date: " + new Date(uc.getDate()));
            System.out.println("Last modified: " + new Date(uc.getLastModified()));
            System.out.println("Expiration date: " + new Date(uc.getExpiration()));
            System.out.println("Content-length: " + uc.getContentLength());
            System.out.println("URL: " + uc.getURL());

        } catch (MalformedURLException ex) {
            System.err.println("Invalid URL.");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
