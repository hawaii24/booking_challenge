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

	/**@Test(expected=IOException.class)
	public void test() {
		InputStreamReader mockReader = mock(InputStreamReader.class); 
		
		Mockito.when(mockReader.ready()).thenReturn("random", "text");
		
		HttpsURLConnection con = mock(HttpsURLConnection.class); 
		Mockito.when(con.getInputStream()).thenReturn(new InputStream()); 
		
	}**/

}
