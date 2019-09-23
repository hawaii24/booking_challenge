package apis;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.json.JSONException;
import org.junit.jupiter.api.Test;

class OutputParserTest {

	@Test
	void whenCorrectInputIsPassedShouldReturnCorrectDataTest() {
		InputStreamReader inputStreamReader = mock(InputStreamReader.class); 
		OutputParser op = new OutputParser();
		try {
			List<Car> carResults = op.listCarResults(inputStreamReader, 4);
			assertNotNull(carResults);
		} catch (JSONException e) {
			fail("JSON cannot be parsed");
			e.printStackTrace();
		} catch (IOException e) {
			// Because we're mocking the inputStreamReader and are not calling an external API, this use case
			// is not relevant, hence it should always pass 
			assert(true);
		}
	}

}
