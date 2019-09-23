package apis.booking_challenge;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONException;
import org.junit.Test;
import org.mockito.Mockito;

public class OutputParserTest {

	/** I was intending to use Mockito to mock data resulting from the API calls to Dave, Eric and Jeff
	 and test the output of the OutParser's listAllResults() but it's bad practice to mock data from classes
	 one doesn't own (in this case, InputStreamReader) and I'm not sure how to circumvent this issue while
	 making sure the test case remains relevant. I'm obviously open to suggestions.
	 **/
	
	
	/**@Test(expected=IOException.class)
	public void test() {
		InputStreamReader mockReader = mock(InputStreamReader.class); 
		
		Mockito.when(mockReader.ready()).thenReturn("random", "text");
		
		HttpsURLConnection con = mock(HttpsURLConnection.class); 
		Mockito.when(con.getInputStream()).thenReturn(new InputStream()); 
		
	}**/

}
