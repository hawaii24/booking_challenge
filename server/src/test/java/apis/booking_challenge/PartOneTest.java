package apis.booking_challenge;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.junit.Test;

class PartOneTest {

	
	@Test
	void testNoAccessToAPIBehaviour() {
		String heathrow = "51.470020,-0.454296";
		String buckinghamPalace = "51.501366,-0.141890";
		OutputStream os = new ByteArrayOutputStream();
		
		PartOne exercise = new PartOne(heathrow, buckinghamPalace); 
		Map<String, Integer> result = exercise.getstuff("dave", 4); 
		
		if(result.size() == 0) {
			assert(result.containsKey("Sorry, could not access Dave's API"));
		} 
		
		
	}
	
	@Test
	void testInvalidInput() {
		String firstInput = "51.470020,,-0.454296";
		String secondInput = "51.501366,--0.141890";
		OutputStream os = new ByteArrayOutputStream(); 
		
		PartOne exercise = new PartOne(firstInput, secondInput); 
		Map<String, Integer> result = exercise.getstuff("dave", 4); 
		
		if(result.size() != 0) {
			assert(result.containsKey("ERROR: Invalid coordinates."));
		}
	}
	
	@Test
	void testInvalidPassengers() {
		String firstInput = "51.470020,-0.454296";
		String secondInput = "51.501366,-0.141890";
		
		PartOne exercise = new PartOne(firstInput, secondInput); 
		Map<String, Integer> result = exercise.getstuff("dave", 24); 
		
		if(result.size() != 0) {
			assert(result.containsKey("ERROR: The number of passangers must be between 1 and 16")); 
		} 
		
	}

}
