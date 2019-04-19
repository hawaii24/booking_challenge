package apis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    private static String[] listRanker(StringBuffer output) {
        // this method takes the output of the API call and ranks the results by distance
        String[] response = new String[10];



        return response;
    }

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

    private static String bufferParser(StringBuffer charSeq) {

        for(int i = 0;  i < charSeq.length(); i++) {
            char currentCh = charSeq.charAt(i);
        }

        return "";
    }


    private static Map<String, Integer> sortMapByValue(Map<String, Integer> unsortedMap) {

        // convert map to a list of map
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(unsortedMap.entrySet());
        
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
        	@Override // we wish to sort them in descending order
        	public int compare(Map.Entry<String, Integer> l1, Map.Entry<String, Integer> l2) {
        		    return (l2.getValue().compareTo(l1.getValue()));
        	}
        
        });
        
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>(); 
        for(Map.Entry<String, Integer> entry: list) {
        	sortedMap.put(entry.getKey(), entry.getValue());
        }
        
        
        return sortedMap; 
    }
    protected static Map<String, Integer> listAllResults(InputStreamReader stmread) throws IOException, JSONException {
        String json = jsonReader(stmread);
        JSONObject obj = new JSONObject(json);
        Map<String, Integer> queryResults = new HashMap<>();
        JSONArray options = obj.getJSONArray("options");
        
        for (int i = 0; i < options.length(); i++) {
            String carType = options.getJSONObject(i).getString("car_type");
            Integer price = options.getJSONObject(i).getInt("price");
            queryResults.put(carType, price);
        }
        
        Map<String, Integer> results = sortMapByValue(queryResults); 
        
        for(Map.Entry<String, Integer> entry: results.entrySet()) {
        	String tobePrinted = String.format("{%s} - {%d}", entry.getKey(), entry.getValue());
        	System.out.println(tobePrinted);
        }
        

        return queryResults;
    }



}

