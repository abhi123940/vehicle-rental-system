import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Class representing a rental transaction
 */
public class Rent {
    private static int nextId = 1;
    
    private int rentId;
    private String vehicleId;
    private String customerName;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalCost;

    public Rent(String vehicleId, String customerName, LocalDate startDate, LocalDate endDate, double dailyRate) {
        this.rentId = nextId++;
        this.vehicleId = vehicleId;
        this.customerName = customerName;
        this.startDate = startDate;
        this.endDate = endDate;
        calculateTotalCost(dailyRate);
    }

    /**
     * Calculate the total cost of the rental
     */
    private void calculateTotalCost(double dailyRate) {
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        if (days <= 0) {
            days = 1; // Minimum 1 day rental
        }
        this.totalCost = days * dailyRate;
    }

    public int getRentId() {
        return rentId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public String getCustomerName() {
        return customerName;
    }

    /**
     * Display rental details
     */
    public void displayRentDetails() {
        System.out.println("======= RENTAL DETAILS =======");
        System.out.println("Rental ID: " + rentId);
        System.out.println("Vehicle ID: " + vehicleId);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
        System.out.println("Total Cost: $" + String.format("%.2f", totalCost));
        System.out.println("==============================");
    }
}