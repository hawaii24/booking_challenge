package apis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
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
        List<Map.Entry<String, Integer>>
    }
    protected static Map<String, Integer> listAllResults(InputStreamReader stmread) throws IOException, JSONException {
        String json = jsonReader(stmread);
        JSONObject obj = new JSONObject(json);
        Map<String, Integer> queryResults = new HashMap<>();
        JSONArray options = obj.getJSONArray("options");
        
        System.out.println(options.toString());
        for (int i = 0; i < options.length(); i++) {
            String carType = options.getJSONObject(i).getString("car_type");
            Integer price = options.getJSONObject(i).getInt("price");
            queryResults.put(carType, price);
        }
        
        System.out.println(queryResults.toString());

        return queryResults;
    }



}

