import java.util.ArrayList;
import java.util.List;

/**
 * Class to manage all vehicles in the system
 */
public class AllVehicles {
    private List<Vehicle> vehicleList;

    public AllVehicles() {
        this.vehicleList = new ArrayList<>();
        // Add some sample vehicles
        initializeVehicles();
    }

    /**
     * Initialize the system with some sample vehicles
     */
    private void initializeVehicles() {
        // Add sample cars
        addVehicle(new Car("C001", "Toyota", "Camry", 50.0, 4, "Gasoline", 5));
        addVehicle(new Car("C002", "Honda", "Accord", 55.0, 4, "Gasoline", 5));
        addVehicle(new Car("C003", "Tesla", "Model 3", 85.0, 4, "Electric", 5));
        addVehicle(new Car("C004", "BMW", "X5", 90.0, 4, "Gasoline", 7));
        
        // Add sample bikes
        addVehicle(new Bike("B001", "Honda", "CBR", 35.0, "Sport", 1000));
        addVehicle(new Bike("B002", "Harley", "Davidson", 65.0, "Cruiser", 1500));
        addVehicle(new Bike("B003", "Yamaha", "R1", 40.0, "Sport", 1000));
    }

    /**
     * Add a vehicle to the inventory
     */
    public void addVehicle(Vehicle vehicle) {
        vehicleList.add(vehicle);
    }

    /**
     * Remove a vehicle from the inventory
     */
    public boolean removeVehicle(String id) {
        Vehicle vehicle = findVehicleById(id);
        if (vehicle != null) {
            vehicleList.remove(vehicle);
            return true;
        }
        return false;
    }

    /**
     * Find a vehicle by its ID
     */
    public Vehicle findVehicleById(String id) {
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getId().equalsIgnoreCase(id)) {
                return vehicle;
            }
        }
        return null;
    }

    /**
     * Display all vehicles in the inventory
     */
    public void displayAllVehicles() {
        System.out.println("\n====== ALL VEHICLES ======");
        if (vehicleList.isEmpty()) {
            System.out.println("No vehicles available in the system.");
        } else {
            for (Vehicle vehicle : vehicleList) {
                if (vehicle instanceof Car) {
                    System.out.println("Car: " + vehicle.getName() + " " + vehicle.getModel() + " (ID: " + vehicle.getId() + ") - " + 
                                    (vehicle.isAvailable() ? "Available" : "Rented"));
                } else if (vehicle instanceof Bike) {
                    System.out.println("Bike: " + vehicle.getName() + " " + vehicle.getModel() + " (ID: " + vehicle.getId() + ") - " + 
                                    (vehicle.isAvailable() ? "Available" : "Rented"));
                }
            }
        }
        System.out.println("=========================");
    }

    /**
     * Display only available vehicles
     */
    public void displayAvailableVehicles() {
        System.out.println("\n====== AVAILABLE VEHICLES ======");
        boolean found = false;
        
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.isAvailable()) {
                found = true;
                if (vehicle instanceof Car) {
                    System.out.println("Car: " + vehicle.getName() + " " + vehicle.getModel() + " (ID: " + vehicle.getId() + 
                                    ") - $" + vehicle.getRentalPrice() + "/day");
                } else if (vehicle instanceof Bike) {
                    System.out.println("Bike: " + vehicle.getName() + " " + vehicle.getModel() + " (ID: " + vehicle.getId() + 
                                    ") - $" + vehicle.getRentalPrice() + "/day");
                }
            }
        }
        
        if (!found) {
            System.out.println("No vehicles available for rent.");
        }
        System.out.println("===============================");
    }

    /**
     * Get all vehicles
     */
    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }
}