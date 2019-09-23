package apis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PartOneExtension extends PartOne {

	String pickup;
	String dropoff;

	// call the APIs of Eric and Jeff in addition to Dave's and get the cheapest
	// fare for each car type

	public PartOneExtension(String pickup, String dropoff) {
		super(pickup, dropoff);
		this.pickup = pickup;
		this.dropoff = dropoff;
	}

	public List<String> callApis(int num_pass) {

		if (num_pass < 1 || num_pass > 16) {
			return null;
		}

		PartOne exercise = new PartOne(pickup, dropoff);
		List<Car> dave = exercise.fetchListOfCars("dave", num_pass);
		List<Car> eric = exercise.fetchListOfCars("eric", num_pass);
		List<Car> jeff = exercise.fetchListOfCars("jeff", num_pass);
		List<String> results = new ArrayList<String>();

		String[] carTypes = { "STANDARD", "EXECUTIVE", "LUXURY", "PEOPLE_CARRIER", "LUXURY_PEOPLE_CARRIER", "MINIBUS" };

		for (String type : carTypes) {
			int minValue = 2147483647; // In pseudocode this would be +infinity
			String source = "";
			boolean doesDaveSupplyThisCarType = false;
			boolean doesEricSupplyThisCarType = false;
			boolean doesJeffSupplyThisCarType = false;
			if (dave != null) {
				doesDaveSupplyThisCarType = dave.stream().map(car -> car.getCarType()).collect(Collectors.toList())
						.contains(type);
			}
			if (eric != null) {
				doesEricSupplyThisCarType = eric.stream().map(car -> car.getCarType()).collect(Collectors.toList())
						.contains(type);
			}
			if (jeff != null) {
				doesJeffSupplyThisCarType = jeff.stream().map(car -> car.getCarType()).collect(Collectors.toList())
						.contains(type);
			}

			if (doesDaveSupplyThisCarType) {
				minValue = Math.min(minValue,
						Collections.min(dave.stream().map(car -> car.getPrice()).collect(Collectors.toList())));
				source = "Dave";
			}

			if (doesEricSupplyThisCarType) {
				minValue = Math.min(minValue,
						Collections.min(eric.stream().map(car -> car.getPrice()).collect(Collectors.toList())));
				source = "Eric";
			}

			if (doesJeffSupplyThisCarType) {
				minValue = Math.min(minValue,
						Collections.min(jeff.stream().map(car -> car.getPrice()).collect(Collectors.toList())));
				source = "Jeff";
			}

			if (!doesDaveSupplyThisCarType && !doesEricSupplyThisCarType && !doesJeffSupplyThisCarType) {
				// ignore the car type altogether if none of the providers has it
				continue;
			} else {
				String line = String.format("%s - %s - %d", type, source, minValue);
				System.out.println(line);
				results.add(line); // necessary for the tests
			}

		}
		return results;
	}

	public static void main(String[] args) {

		if (args.length != 3) {
			System.out.println(
					"ERROR: Please enter exactly one pickup coordinates pair, one dropoff coordinates pair and the number of passengers");
		} else {
			String coordinateChecker = "^(\\-?\\d+(\\.\\d+)?),\\s*(\\-?\\d+(\\.\\d+)?)$";
			if (!(args[0].matches(coordinateChecker) && args[1].matches(coordinateChecker))) {
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
