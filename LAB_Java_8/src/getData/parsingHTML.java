package getData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class parsingHTML {
    
    public void parse(String urlName) {
        URL page = null;
        try {
            page = new URL(urlName);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if (page == null) {
            throw new RuntimeException();
        }
        try (BufferedReader d = new BufferedReader
                (new InputStreamReader(page.openStream())))
        {
            String line = "";
            while ((line = d.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace(); } }
}
