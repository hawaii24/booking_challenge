package apis.booking_challenge;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartOne {
	
	protected String pickup; 
	protected String dropoff; 
	
	public PartOne(String pickup, String dropoff) {
		this.pickup = pickup; 
		this.dropoff = dropoff; 
	}

    public Map<String, Integer> getstuff(String api, int num_pass) {
    	if(num_pass < 1 || num_pass > 16) {
    		Map<String, Integer> error_message = new HashMap<String, Integer>(); 
    		error_message.put("The number of passangers must be between 1 and 16", 0);
    		return error_message; 
    	}
        try {
            String url_stringify = String.format("https://techtest.rideways.com/%s?pickup=%s&dropoff=%s", api, pickup, dropoff);
            URL url = new URL(url_stringify);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(2000);
            int status = con.getResponseCode();
            if(status == 200) { // success, read the output
                InputStreamReader stmread = new InputStreamReader(con.getInputStream());
                Map<String, Integer> results = OutputParser.listAllResults(stmread, num_pass);
		con.disconnect();
                
                
                return results; 

            } else {
            	String errorMessage = String.format("Sorry, could not access %s's API", api);
                System.out.println(con.getErrorStream());
                Map<String, Integer> error = new HashMap<String, Integer>(); 
                error.put(errorMessage, 0);
                
                return error; 
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
		
		if(args.length != 3){
			System.out.println("ERROR: Please enter exactly one pickup coordinates, one dropoff coordinates and the number of passengers.");
		} else {
			// make sure arguments are genuine GPS lat-long coordinates 
			String coordinateChecker = "^(\\-?\\d+(\\.\\d+)?),\\s*(\\-?\\d+(\\.\\d+)?)$"; 
			if(!(args[0].matches(coordinateChecker) && args[1].matches(coordinateChecker))) {
				System.out.println("ERROR: Please enter valid coordinates as input"); 
			} else {
				PartOne exercise = new PartOne(args[0], args[1]);
				try {
					int num_pass = Integer.parseInt(args[2]); 
					Map<String, Integer> results = exercise.getstuff("dave", num_pass);
					for(Map.Entry<String, Integer> entry: results.entrySet()) {
						System.out.println(String.format("%s - %d", entry.getKey(), entry.getValue())); 
					}

				} catch (NumberFormatException pe) {
					System.out.println("ERROR: The third argument must be an integer between 1 and 16.");
				}

			}
		}
    }

}


