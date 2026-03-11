package com.vehicle.management.app;

import com.vehicle.management.model.*;
import com.vehicle.management.exception.*;
import com.vehicle.management.util.*;
import com.vehicle.management.service.VehicleService;
import java.util.Scanner;

public class VehicleManagementApp {
    
    private static VehicleService vehicleService = new VehicleService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while(running) {
            displayMenu();

            int choice = getIntInput("Enter your choice: ");

            switch(choice) {
                case 1: 
                    registerVehicle();
                    break;
                case 2: 
                    searchVehicle();
                    break;
                case 3: 
                    displayAllVehicles();
                    break;
                case 4:
                    removeVehicle();
                    break;
                case 5:
                    running = false;
                    System.out.println("Thank you for using the Vehicle Management System!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n--- Vehicle Management System ---");
        System.out.println("1. Register a new vehicle");
        System.out.println("2. Search for a vehicle by registration number");
        System.out.println("3. Display all registered vehicles");
        System.out.println("4. Remove a vehicle by registration number");
        System.out.println("5. Exit");
        System.out.println("---------------------------------");
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while(!scanner.hasNextInt()) {
            System.out.println("Invalid input! Please enter a number.");
            System.out.print(prompt);
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while(!scanner.hasNextDouble()) {
            System.out.println("Invalid input! Please enter a number.");
            scanner.next();
            System.out.print(prompt);
        }
        return scanner.nextDouble();
    }

    private static void registerVehicle() {
        System.out.println("\n--- Register New Vehicle ---");
        System.out.println("Select Vehicle Type:");
        System.out.println("1. Car");
        System.out.println("2. Bike");
        System.out.println("3. Truck");
        
        int type = getIntInput("Enter type: ");
        scanner.nextLine(); // Clear buffer after nextInt()
        
        // Collect common details
        System.out.print("Enter Vehicle ID: ");
        String vehicleId = scanner.nextLine();
        
        System.out.print("Enter Brand: ");
        String brand = scanner.nextLine();
        
        System.out.print("Enter Model Name: ");
        String modelName = scanner.nextLine();
        
        int yearOfManufacture = getIntInput("Enter Year of Manufacture: ");
        scanner.nextLine();
        
        System.out.print("Enter Color: ");
        String color = scanner.nextLine();
        
        System.out.print("Enter Registration Number: ");
        String registrationNumber = scanner.nextLine();
        
        System.out.println("Select Fuel Type: 1.PETROL 2.DIESEL 3.ELECTRIC 4.CNG 5.HYBRID");
        int fuelChoice = getIntInput("Enter choice: ");
        FuelType fuelType = FuelType.values()[fuelChoice - 1];
        scanner.nextLine();
        
        double maxSpeed = getDoubleInput("Enter Max Speed (km/h): ");
        double mileage = getDoubleInput("Enter Mileage (km/l): ");
        scanner.nextLine();
        
        try {
            Vehicle vehicle = null;
            
            if(type == 1) {
                // Car-specific details
                int numberOfDoors = getIntInput("Enter Number of Doors: ");
                scanner.nextLine();
                double bootSpace = getDoubleInput("Enter Boot Space (liters): ");
                scanner.nextLine();
                
                System.out.print("Air Conditioner Available? (true/false): ");
                boolean acAvailable = scanner.nextBoolean();
                scanner.nextLine();
                
                System.out.println("Transmission Type: 1.MANUAL 2.AUTOMATIC");
                int transChoice = getIntInput("Enter choice: ");
                TransmissionType transmissionType = TransmissionType.values()[transChoice - 1];
                scanner.nextLine();
                
                int seatingCapacity = getIntInput("Enter Seating Capacity: ");
                scanner.nextLine();
                double fuelTankCapacity = getDoubleInput("Enter Fuel Tank Capacity (liters): ");
                scanner.nextLine();
                
                System.out.println("Car Type: 1.SEDAN 2.SUV 3.HATCHBACK 4.COUPE 5.CONVERTIBLE 6.WAGON 7.VAN");
                int carTypeChoice = getIntInput("Enter choice: ");
                CarType carType = CarType.values()[carTypeChoice - 1];
                
                vehicle = new Car(vehicleId, brand, modelName, yearOfManufacture, color,
                        registrationNumber, fuelType, maxSpeed, mileage, numberOfDoors,
                        bootSpace, acAvailable, transmissionType, seatingCapacity, 
                        fuelTankCapacity, carType);
                        
            } else if(type == 2) {
                // Bike-specific details
                int engineCC = getIntInput("Enter Engine CC: ");
                scanner.nextLine();
                
                System.out.print("Has Gear? (true/false): ");
                boolean hasGear = scanner.nextBoolean();
                scanner.nextLine();
                
                System.out.println("Bike Type: 1.SPORTS 2.CRUISER 3.TOURING 4.STANDARD 5.DUAL_SPORT");
                int bikeTypeChoice = getIntInput("Enter choice: ");
                BikeType bikeType = BikeType.values()[bikeTypeChoice - 1];
                scanner.nextLine();
                
                System.out.print("Has ABS? (true/false): ");
                boolean hasABS = scanner.nextBoolean();
                scanner.nextLine();
                
                System.out.print("Kick Start Available? (true/false): ");
                boolean kickStart = scanner.nextBoolean();
                scanner.nextLine();
                
                System.out.print("Has Helmet Storage? (true/false): ");
                boolean helmetStorage = scanner.nextBoolean();
                
                vehicle = new Bike(vehicleId, brand, modelName, yearOfManufacture, color,
                        registrationNumber, fuelType, maxSpeed, mileage, engineCC,
                        hasGear, bikeType, hasABS, kickStart, helmetStorage);
                        
            } else if(type == 3) {
                // Truck-specific details
                double loadCapacity = getDoubleInput("Enter Load Capacity (tons): ");
                scanner.nextLine();
                
                int numberOfAxles = getIntInput("Enter Number of Axles: ");
                scanner.nextLine();
                
                System.out.print("Has Trailer? (true/false): ");
                boolean hasTrailer = scanner.nextBoolean();
                scanner.nextLine();
                
                System.out.print("Enter Goods Type: ");
                String goodsType = scanner.nextLine();
                
                double cargoVolume = getDoubleInput("Enter Cargo Volume (cubic meters): ");
                
                vehicle = new Truck(vehicleId, brand, modelName, yearOfManufacture, color,
                        registrationNumber, fuelType, maxSpeed, mileage, loadCapacity,
                        numberOfAxles, hasTrailer, goodsType, cargoVolume);
            } else {
                System.out.println("Invalid vehicle type!");
                return;
            }
            
            vehicleService.registerVehicle(vehicle);
            
        } catch(DuplicateVehicleException e) {
            System.out.println("Error: " + e.getMessage());
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Invalid selection!");
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void searchVehicle() {
        System.out.println("\n--- Search Vehicle ---");
        scanner.nextLine(); // Clear buffer if needed
        
        System.out.print("Enter Registration Number: ");
        String regNumber = scanner.nextLine();
        
        try {
            Vehicle vehicle = vehicleService.findByRegistrationNumber(regNumber);
            System.out.println("\nVehicle Found!");
            vehicle.displayDetails();
        } catch(VehicleNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void displayAllVehicles() {
        System.out.println("\n--- All Registered Vehicles ---");
        vehicleService.displayAllVehicles();
    }

    private static void removeVehicle() {
        System.out.println("\n--- Remove Vehicle ---");
        scanner.nextLine(); // Clear buffer
        
        System.out.print("Enter Registration Number: ");
        String regNumber = scanner.nextLine();
        
        try {
            vehicleService.removeVehicle(regNumber);
        } catch(VehicleNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


}
