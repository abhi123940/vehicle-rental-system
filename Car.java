/**
 * Class representing a Car that can be rented
 */
public class Car extends Vehicle {
    private int numDoors;
    private String fuelType;
    private int seatingCapacity;

    public Car(String id, String name, String model, double rentalPrice, 
               int numDoors, String fuelType, int seatingCapacity) {
        super(id, name, model, rentalPrice);
        this.numDoors = numDoors;
        this.fuelType = fuelType;
        this.seatingCapacity = seatingCapacity;
    }

    public int getNumDoors() {
        return numDoors;
    }

    public String getFuelType() {
        return fuelType;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    @Override
    public void displayDetails() {
        System.out.println("------ CAR DETAILS ------");
        display();
        System.out.println("Number of Doors: " + numDoors);
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("Seating Capacity: " + seatingCapacity);
        System.out.println("-------------------------");
    }
}