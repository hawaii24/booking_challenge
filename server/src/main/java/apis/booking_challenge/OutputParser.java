package apis.booking_challenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class OutputParser {

    // this class takes the InputStream obtained from the API call and parses it to our requirements
    private static String jsonReader(InputStreamReader stmread) throws IOException {
        BufferedReader bf = new BufferedReader(stmread);
        String inputLine;
        StringBuffer content = new StringBuffer();
        while((inputLine = bf.readLine()) != null) {
            content.append(inputLine);
        }
        bf.close();

        return content.toString();
    }


    private static Map<String, Integer> sortMapByValue(Map<String, Integer> unsortedMap) {

        // convert map to a list of map to use Collections.sort
        List<Map.Entry<String, Integer>> listOfMaps = new LinkedList<Map.Entry<String, Integer>>(unsortedMap.entrySet());
        Collections.sort(listOfMaps, new Comparator<Map.Entry<String, Integer>>() {
        	 @Override // we wish to sort them in descending order
        	 public int compare(Map.Entry<String, Integer> firstEntry, Map.Entry<String, Integer> secondEntry) {
        		    return (secondEntry.getValue().compareTo(firstEntry.getValue()));
        	 }
        
        });
        
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for(Map.Entry<String, Integer> entry: listOfMaps) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        
        return sortedMap; 
    }
    protected static Map<String, Integer> listAllResults(InputStreamReader stmread, int num_pass) throws IOException, JSONException {
        JSONObject obj = new JSONObject(jsonReader(stmread));
        Map<String, Integer> queryResults = new HashMap<>();
        JSONArray options = obj.getJSONArray("options");
        List<String> acceptableCars = new ArrayList<String>(); // weeds out the cars irrelevant to the
        
        if(num_pass <= 4) { // the bounds are loosely checked because we already checked them carefully in getstuff() method 
        	acceptableCars = Arrays.asList("STANDARD", "EXECUTIVE", "LUXURY", "PEOPLE_CARRIER", "LUXURY_PEOPLE_CARRIER", "MINIBUS");
        } else if(num_pass <= 6) {
        	acceptableCars = Arrays.asList("PEOPLE_CARRIER", "LUXURY_PEOPLE_CARRIER"); 
        } else {
        	acceptableCars.add("MINIBUS"); 
        }
        
        for (int i = 0; i < options.length(); i++) {
            String carType = options.getJSONObject(i).getString("car_type");
            Integer price = options.getJSONObject(i).getInt("price");

            if(acceptableCars.contains(carType)) {
                queryResults.put(carType, price);
            }
        }
        
        if(queryResults.isEmpty()) { // this is to avoid to have an empty map passed as an argument to the subsequent method
        	queryResults.put("No car available for these conditions", 0); 
        }
        Map<String, Integer> results = sortMapByValue(queryResults);

        return results;
    }



}

