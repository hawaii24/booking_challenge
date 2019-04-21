package apis;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PartOne {
	
	protected String pickup; 
	protected String dropoff; 
	
	public PartOne(String pickup, String dropoff) {
		this.pickup = pickup; 
		this.dropoff = dropoff; 
	}

    public Map<String, Integer> getstuff(String api) {
    	//List<String> resultStrings = new ArrayList<String>(); 
        try {
            String url_stringify = String.format("https://techtest.rideways.com/%s?pickup=%s&dropoff=%s", api, pickup, dropoff);
            URL url = new URL(url_stringify);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(2000);
            int status = con.getResponseCode();

            if(status == 200) { // success, read the output
                InputStreamReader stmread = new InputStreamReader(con.getInputStream());
                Map<String, Integer> results = OutputParser.listAllResults(stmread);
                
                //System.out.println(results.toString());
                con.disconnect();
                
                for(Map.Entry<String, Integer> entry: results.entrySet()) {
                	String tobePrinted = String.format("{%s} - {%d}", entry.getKey(), entry.getValue());
                	//resultStrings.add(tobePrinted);
                	System.out.println(tobePrinted); 
                }
                return results; 

            } else {
                System.out.println(String.format("Sorry, could not access %s's API", api));
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException p) {
            p.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        } 
        
        return null;

    }

   
    
    public static void main(String args[]) {
		List<String> acceptableApis = new ArrayList<String>();
		acceptableApis.add("dave"); 
		acceptableApis.add("eric");
		acceptableApis.add("jeff");

    	if(args.length != 2){
    		System.out.println("Please enter exactly one pickup coordinates, one dropoff coordinates and the API name");
    	} else if(!acceptableApis.contains(args[2])) {
    		System.out.println("Please enter as API one of: dave, eric, jeff."); 
    	} else {
    		String coordinateChecker = "^(\\-?\\d+(\\.\\d+)?),\\s*(\\-?\\d+(\\.\\d+)?)$"; 
        	if(!(args[0].matches(coordinateChecker) && args[1].matches(coordinateChecker))) {
        		System.out.println("Please enter valid coordinates as input"); 
        	} else {
            	PartOne exercise = new PartOne(args[0], args[1]);
            	exercise.getstuff("dave");
        	}
    	}
    	
    }

}
