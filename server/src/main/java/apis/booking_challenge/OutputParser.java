package apis;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class OutputParser {

    // this class takes the InputStream obtained from the API call and parses it to our requirements
    private static String jsonReader(InputStreamReader stmread) throws IOException {
        BufferedReader bf = new BufferedReader(stmread);
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = bf.readLine()) != null) {
            content.append(inputLine);
        }
        bf.close();

        return content.toString();
    }
    



    protected List<Car> listCarResults(InputStreamReader stmread, int numberPassengers) throws IOException, JSONException {
        JSONObject obj = new JSONObject(jsonReader(stmread));
        CarCategory standard = new CarCategory("STANDARD", 4);
        CarCategory executive = new CarCategory("EXECUTIVE", 4);
        CarCategory luxury = new CarCategory("LUXURY", 4);
        CarCategory peopleCarrier = new CarCategory("PEOPLE_CARRIER", 6);
        CarCategory luxuryPeopleCarrier = new CarCategory("LUXURY_PEOPLE_CARRIER", 6);
        CarCategory minibus = new CarCategory("MINIBUS", 16);
        List<Car> queryResults = new ArrayList<Car>();
        List<CarCategory> acceptableCars = Arrays.asList(standard, executive, luxury, peopleCarrier, luxuryPeopleCarrier, minibus)
                .stream()
                .filter(carCat -> carCat.getMaxNumberOfPassengers() >= numberPassengers)
                .collect(Collectors.toList());
        JSONArray options = obj.getJSONArray("options");

        for(int i = 0; i < options.length(); i++) {
            String carType = options.getJSONObject(i).getString("car_type");
            Integer price = options.getJSONObject(i).getInt("price");

            // use a lambda expression to iterate over list of CarCategory, get the car type for each and check if
            // one of them includes the carType returned by the API call
            if(acceptableCars.stream().map(carCat -> carCat.getCarType()).collect(Collectors.toList()).contains(carType)) {
                queryResults.add(new Car(carType, price));
            }
        }

        if(!queryResults.isEmpty()) {
            Collections.sort(queryResults, (car1, car2) -> car2.getPrice().compareTo(car1.getPrice()));
        }

        return queryResults;

    }




}
