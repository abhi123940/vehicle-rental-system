import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to manage the rental operations
 */
public class RentalSystem {
    private AllVehicles allVehicles;
    private List<Rent> rentList;

    public RentalSystem() {
        this.allVehicles = new AllVehicles();
        this.rentList = new ArrayList<>();
    }

    /**
     * Rent a vehicle
     */
    public boolean rentVehicle(String vehicleId, String customerName, LocalDate startDate, LocalDate endDate) {
        Vehicle vehicle = allVehicles.findVehicleById(vehicleId);
        
        if (vehicle == null) {
            System.out.println("Vehicle with ID " + vehicleId + " not found.");
            return false;
        }
        
        if (!vehicle.isAvailable()) {
            System.out.println("Vehicle is already rented.");
            return false;
        }
        
        // Create a new rental
        Rent rent = new Rent(vehicleId, customerName, startDate, endDate, vehicle.getRentalPrice());
        rentList.add(rent);
        
        // Mark vehicle as not available
        vehicle.setAvailable(false);
        
        System.out.println("\nVehicle rented successfully!");
        rent.displayRentDetails();
        return true;
    }

    /**
     * Return a rented vehicle
     */
    public boolean returnVehicle(String vehicleId) {
        Vehicle vehicle = allVehicles.findVehicleById(vehicleId);
        
        if (vehicle == null) {
            System.out.println("Vehicle with ID " + vehicleId + " not found.");
            return false;
        }
        
        if (vehicle.isAvailable()) {
            System.out.println("This vehicle is not currently rented.");
            return false;
        }
        
        // Find the rental record
        Rent rentalRecord = null;
        for (Rent rent : rentList) {
            if (rent.getVehicleId().equals(vehicleId)) {
                rentalRecord = rent;
                break;
            }
        }
        
        if (rentalRecord != null) {
            System.out.println("\nVehicle returned successfully!");
            rentalRecord.displayRentDetails();
        }
        
        // Mark vehicle as available
        vehicle.setAvailable(true);
        return true;
    }

    /**
     * Display all rental records
     */
    public void displayAllRentals() {
        System.out.println("\n====== ALL RENTAL RECORDS ======");
        if (rentList.isEmpty()) {
            System.out.println("No rental records found.");
        } else {
            for (Rent rent : rentList) {
                System.out.println("Rental ID: " + rent.getRentId() + 
                                 " | Vehicle ID: " + rent.getVehicleId() +
                                 " | Customer: " + rent.getCustomerName() +
                                 " | Cost: $" + String.format("%.2f", rent.getTotalCost()));
            }
        }
        System.out.println("===============================");
    }

    /**
     * Get the AllVehicles object
     */
    public AllVehicles getAllVehicles() {
        return allVehicles;
    }

    /**
     * Find a specific rental by ID
     */
    public Rent findRentalById(int rentId) {
        for (Rent rent : rentList) {
            if (rent.getRentId() == rentId) {
                return rent;
            }
        }
        return null;
    }
}