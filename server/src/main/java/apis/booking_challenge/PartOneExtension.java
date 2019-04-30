package apis.booking_challenge;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PartOneExtension extends PartOne {
	
	String pickup;
	String dropoff; 
	

	public PartOneExtension(String pickup, String dropoff) {
		super(pickup, dropoff);
	}
	
	public List<String> callApis(int num_pass) {
		
		if(num_pass < 1 || num_pass > 16) {
			List<String> error_message = Arrays.asList("The number of passengers must be between 1 and 16");
			return error_message;
		}
		Map<String, Integer> dave = getstuff("dave", num_pass);
		Map<String, Integer> eric = getstuff("eric", num_pass);
		Map<String, Integer> jeff = getstuff("jeff", num_pass);		 
		
		List<String> results = new ArrayList<String>(); 
		
		String[] carTypes = {"STANDARD", "EXECUTIVE", "LUXURY", "PEOPLE_CARRIER", "LUXURY_PEOPLE_CARRIER", "MINIBUS"};
		
		for(String type: carTypes) {
			int minValue = 2147483647; // In pseudocode this would be +infinity 
			String source = "";
			
			if(dave.containsKey(type)) {
				minValue = Math.min(minValue, dave.get(type));
				source = "Dave";
			}
			
			if(eric.containsKey(type)) {
				minValue = Math.min(minValue, eric.get(type));
				source = "Eric"; 
			}
			
			if(jeff.containsKey(type)) {
				minValue = Math.min(minValue, jeff.get(type)); 
				source = "Jeff"; 
			}
			
			if(!dave.containsKey(type) && !eric.containsKey(type) && !jeff.containsKey(type)) {
				continue; 
			} else {
				String line = String.format("%s - %s - %d", type, source, minValue); 
				System.out.println(line); 
				results.add(line); 
			}
			
		}
		return results; 
	}
	
	public static void main(String[] args) {
	

    	if(args.length != 3){
    		System.out.println("ERROR: Please enter exactly one pickup coordinates pair and one dropoff coordinates pair");
    	} else {
    		String coordinateChecker = "^(\\-?\\d+(\\.\\d+)?),\\s*(\\-?\\d+(\\.\\d+)?)$"; 
        	if(!(args[0].matches(coordinateChecker) && args[1].matches(coordinateChecker))) {
        		System.out.println("ERROR: Please enter valid coordinates as input"); 
        	} else {
            	PartOneExtension exercise = new PartOneExtension(args[0], args[1]);
            	try {
            		int num_pass = Integer.parseInt(args[2]); 
                	exercise.callApis(num_pass);

            	} catch (NumberFormatException pe) {
            		System.out.println("ERROR: The third argument must be an integer between 1 and 16.");
            	}
        	}
    	}
    	
    
	}

}
