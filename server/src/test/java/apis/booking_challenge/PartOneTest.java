package apis;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
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

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
class PartOneTest {

	
	@Test
	void whenAPIInavailableShouldOutputStandardErrorMessageTest() {
		String heathrow = "51.470020,-0.454296";
		String buckinghamPalace = "51.501366,-0.141890";
		OutputStream os = new ByteArrayOutputStream();
		
		PartOne exercise = new PartOne(heathrow, buckinghamPalace); 
		List<Car> result = exercise.fetchListOfCars("dave", 4); 
		
		if(result.size() == 0) {
			assertEquals("Sorry, could not access Dave's API" + System.getProperty("line.separator"), os.toString());
		} 
		
		
	}
	
	@Test
	void whenInvalidCoordinatesAreProvidedShouldOutputStandardErrorMessageTest() {
		String firstInput = "51.470020,,-0.454296";
		String secondInput = "51.501366,--0.141890";
		
		PartOne exercise = new PartOne(firstInput, secondInput); 
		List<Car> result = exercise.fetchListOfCars("dave", 4); 
		
		assertNull(result);
	}
	
	@Test
	void whenInvalidNumberOfPassengersIsProvidedShouldOutputStandardErrorMessageTest() {
		String firstInput = "51.470020,-0.454296";
		String secondInput = "51.501366,-0.141890";
		
		PartOne exercise = new PartOne(firstInput, secondInput); 
		List<Car> result = exercise.fetchListOfCars("dave", 24); 
		
		assertNull(result);
		
	}
	
	// we'll generate some random data to see how the method OutputParser() reacts in various situations
	@Test
	void whenCorrectInputIsPassedShouldReturnCorrectDataTest() {
		BufferedReader bufferedReader = mock(BufferedReader.class); 
		assert(true);
	}
	

}
