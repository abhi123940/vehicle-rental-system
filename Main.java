/**
 * Main class to start the Vehicle Rental System
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Vehicle Rental System...");
        
        // Create and start the user interface
        UserChoice userChoice = new UserChoice();
        userChoice.start();
    }
}