/**
 * Class representing a Bike that can be rented
 */
public class Bike extends Vehicle {
    private String bikeType;
    private int engineCapacity;

    public Bike(String id, String name, String model, double rentalPrice, 
                String bikeType, int engineCapacity) {
        super(id, name, model, rentalPrice);
        this.bikeType = bikeType;
        this.engineCapacity = engineCapacity;
    }

    public String getBikeType() {
        return bikeType;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    @Override
    public void displayDetails() {
        System.out.println("------ BIKE DETAILS ------");
        display();
        System.out.println("Bike Type: " + bikeType);
        System.out.println("Engine Capacity: " + engineCapacity + "cc");
        System.out.println("--------------------------");
    }
}