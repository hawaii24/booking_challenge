package apis;

public class CarCategory {
	 private String carType;
	    private int maxNumberOfPassengers;

	    CarCategory(String carType, int maxNumberOfPassengers) {
	        this.carType = carType;
	        this.maxNumberOfPassengers = maxNumberOfPassengers;
	    }

	    String getCarType() {
	        return carType;
	    }

	    void setCarType(String carType) {
	        this.carType = carType;
	    }

	    int getMaxNumberOfPassengers() {
	        return maxNumberOfPassengers;
	    }

	    void setMaxNumberOfPassengers(int maxNumberOfPassengers) {
	        this.maxNumberOfPassengers = maxNumberOfPassengers;
	    }
}
