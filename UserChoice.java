import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Class to handle user interactions
 */
public class UserChoice {
    private Scanner scanner;
    private RentalSystem rentalSystem;
    private DateTimeFormatter dateFormatter;

    public UserChoice() {
        this.scanner = new Scanner(System.in);
        this.rentalSystem = new RentalSystem();
        this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    /**
     * Display the main menu
     */
    public void displayMenu() {
        System.out.println("\n===== VEHICLE RENTAL SYSTEM =====");
        System.out.println("1. Display all vehicles");
        System.out.println("2. Display available vehicles");
        System.out.println("3. Rent a vehicle");
        System.out.println("4. Return a vehicle");
        System.out.println("5. View vehicle details");
        System.out.println("6. View all rentals");
        System.out.println("0. Exit");
        System.out.println("================================");
        System.out.print("Enter your choice: ");
    }

    /**
     * Process user choices
     */
    public void processChoice(int choice) {
        switch (choice) {
            case 1:
                rentalSystem.getAllVehicles().displayAllVehicles();
                break;
            case 2:
                rentalSystem.getAllVehicles().displayAvailableVehicles();
                break;
            case 3:
                rentVehicleProcess();
                break;
            case 4:
                returnVehicleProcess();
                break;
            case 5:
                viewVehicleDetails();
                break;
            case 6:
                rentalSystem.displayAllRentals();
                break;
            case 0:
                System.out.println("Thank you for using the Vehicle Rental System. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    /**
     * Process for renting a vehicle
     */
    private void rentVehicleProcess() {
        rentalSystem.getAllVehicles().displayAvailableVehicles();
        
        System.out.print("\nEnter the vehicle ID you want to rent: ");
        String vehicleId = scanner.nextLine();
        
        Vehicle vehicle = rentalSystem.getAllVehicles().findVehicleById(vehicleId);
        if (vehicle == null) {
            System.out.println("Vehicle with ID " + vehicleId + " not found.");
            return;
        }
        
        if (!vehicle.isAvailable()) {
            System.out.println("This vehicle is already rented.");
            return;
        }
        
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
        
        LocalDate startDate = null;
        while (startDate == null) {
            System.out.print("Enter start date (yyyy-MM-dd): ");
            String startDateStr = scanner.nextLine();
            try {
                startDate = LocalDate.parse(startDateStr, dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd format.");
            }
        }
        
        LocalDate endDate = null;
        while (endDate == null) {
            System.out.print("Enter end date (yyyy-MM-dd): ");
            String endDateStr = scanner.nextLine();
            try {
                endDate = LocalDate.parse(endDateStr, dateFormatter);
                if (endDate.isBefore(startDate)) {
                    System.out.println("End date cannot be before start date.");
                    endDate = null;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd format.");
            }
        }
        
        rentalSystem.rentVehicle(vehicleId, customerName, startDate, endDate);
    }

    /**
     * Process for returning a vehicle
     */
    private void returnVehicleProcess() {
        System.out.print("Enter the ID of the vehicle you want to return: ");
        String vehicleId = scanner.nextLine();
        rentalSystem.returnVehicle(vehicleId);
    }

    /**
     * View details of a specific vehicle
     */
    private void viewVehicleDetails() {
        System.out.print("Enter the vehicle ID to view details: ");
        String vehicleId = scanner.nextLine();
        
        Vehicle vehicle = rentalSystem.getAllVehicles().findVehicleById(vehicleId);
        if (vehicle == null) {
            System.out.println("Vehicle with ID " + vehicleId + " not found.");
            return;
        }
        
        vehicle.displayDetails();
    }

    /**
     * Start the application
     */
    public void start() {
        int choice;
        
        System.out.println("Welcome to the Vehicle Rental System!");
        
        while (true) {
            displayMenu();
            try {
                choice = Integer.parseInt(scanner.nextLine());
                processChoice(choice);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}