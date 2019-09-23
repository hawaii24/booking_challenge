package apis;

public class Car {
	private String carType;
    private Integer price;

    public Car(String carType, Integer price) {
        this.carType = carType;
        this.price = price;
    }

    public String getCarType() {
        return this.carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
