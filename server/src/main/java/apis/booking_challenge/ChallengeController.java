package apis.booking_challenge;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import apis.booking_challenge.PartOne; 
import apis.booking_challenge.PartOneExtension;


@RestController
public class ChallengeController {
	
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong(); 
	
	@GetMapping("/partOne")	
	public Map<String, Integer> performPartOne(@RequestParam(name="pickup") String pickup, 
			@RequestParam(name="dropoff") String dropoff, @RequestParam(name="numPassengers", defaultValue="4") int numPassengers){
		
			PartOne partOne = new PartOne(pickup, dropoff); 
			return partOne.getstuff("dave", numPassengers); 
		
	}
	
	@GetMapping("/extensionPartOne")
	public List<String> performExtensionPartOne(@RequestParam(name="pickup") String pickup,
			@RequestParam(name="dropoff") String dropoff, @RequestParam(name="numPassengers", defaultValue="4") int numPassengers) {
		
		PartOneExtension extension = new PartOneExtension(pickup, dropoff); 
		return extension.callApis(numPassengers);
	}
}
