package apis;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

public class PartOne {

    private static String getstuff(String pickup, String dropoff) {

        String result = "";
        try {
            String url_stringify = String.format("https://techtest.rideways.com/dave?pickup=%s&dropoff=%s", pickup, dropoff);
            URL url = new URL(url_stringify);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int status = con.getResponseCode();

            if(status == 200) { // success, read the output
                InputStreamReader stmread = new InputStreamReader(con.getInputStream());
                Map<String, Integer> results = OutputParser.listAllResults(stmread);
                System.out.println(results.toString());
                con.disconnect();

            } else {
                System.out.println("Sorry, could not access Dave's API");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException p) {
            p.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }

        return result;
    }

    private static String[] organiseOutput(String output) {
        String[] result = new String[10];

        return result;
    }

    public static void main(String args[]) {
        String pickup = args[0];
        String dropoff = args[1];
        getstuff(pickup, dropoff);
        System.out.println("Hello, world!");
    }
}
