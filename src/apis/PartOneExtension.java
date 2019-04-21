package apis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.math.*;

public class PartOneExtension extends PartOne {
	
	String pickup;
	String dropoff; 
	

	public PartOneExtension(String pickup, String dropoff) {
		// TODO Auto-generated constructor stub
		super(pickup, dropoff);
	}
	
	public List<String> callApis() {
		Map<String, Integer> dave = getstuff("dave");
		Map<String, Integer> eric = getstuff("eric");
		Map<String, Integer> jeff = getstuff("jeff"); 
		
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
				String line = String.format("{%s} - {%s} - {%d}", type, source, minValue); 
				System.out.println(line); 
				results.add(line); 
			}
			
		}
		return results; 
	}
	
	public static void main(String[] args) {
	

    	if(args.length != 2){
    		System.out.println("Please enter exactly one pickup coordinates, one dropoff coordinates and the API name");
    	} else {
    		String coordinateChecker = "^(\\-?\\d+(\\.\\d+)?),\\s*(\\-?\\d+(\\.\\d+)?)$"; 
        	if(!(args[0].matches(coordinateChecker) && args[1].matches(coordinateChecker))) {
        		System.out.println("Please enter valid coordinates as input"); 
        	} else {
            	PartOneExtension exercise = new PartOneExtension(args[0], args[1]);
            	exercise.callApis();
        	}
    	}
    	
    
	}

}
