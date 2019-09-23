package apis;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class PartOne {

	private String pickup;
	private String dropoff;

	public PartOne(String pickup, String dropoff) {
		this.pickup = pickup;
		this.dropoff = dropoff;
	}

	public List<Car> fetchListOfCars(String api, int num_pass) {
		if (num_pass < 1 || num_pass > 16) {
			Map<String, Integer> error_message = new HashMap<String, Integer>();
			error_message.put("The number of passangers must be between 1 and 16", 0);
			return null;
		}
		try {
			String url_stringify = String.format("https://techtest.rideways.com/%s?pickup=%s&dropoff=%s", api, pickup,
					dropoff);
			URL url = new URL(url_stringify);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setConnectTimeout(2000);
			int status = con.getResponseCode();
			if (status == 200) { // success, read the output
				InputStreamReader stmread = new InputStreamReader(con.getInputStream());
				OutputParser op = new OutputParser();
				List<Car> carResults = op.listCarResults(stmread, num_pass);
				con.disconnect();

				return carResults;

			} else {
				return null;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException p) {
			p.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		}

		return null;
	}

	public static void main(String args[]) {

		if (args.length != 3) {
			System.out.println(
					"ERROR: Please enter exactly one pickup coordinates, one dropoff coordinates and the number of passengers.");
		} else {
			// make sure arguments are genuine GPS lat-long coordinates
			String coordinateChecker = "^(\\-?\\d+(\\.\\d+)?),\\s*(\\-?\\d+(\\.\\d+)?)$";
			if (!(args[0].matches(coordinateChecker) && args[1].matches(coordinateChecker))) {
				System.out.println("ERROR: Please enter valid coordinates as input");
			} else {
				PartOne exercise = new PartOne(args[0], args[1]);
				try {
					int num_pass = Integer.parseInt(args[2]);
					List<Car> results = exercise.fetchListOfCars("dave", num_pass);
					if (results == null) {
						System.out.println("Sorry, could not access dave's API");
					} else if (results.size() == 0) {
						System.out.println("No suitable car was found.");
					} else {
						for (int i = 0; i < results.size(); i++) {
							System.out.println(
									String.format("%s - %d", results.get(i).getCarType(), results.get(i).getPrice()));
						}
					}

				} catch (NumberFormatException pe) {
					System.out.println("ERROR: The third argument must be an integer between 1 and 16.");
				}

			}
		}
	}
}
