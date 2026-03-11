package com.vehicle.management.service;

import com.vehicle.management.model.*;
import com.vehicle.management.exception.*;
import com.vehicle.management.util.VehicleValidator;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Main service class for managing vehicles.
 * 
 * CONCEPT: Service Layer
 * - Encapsulates business logic for vehicle management
 * - Acts as an intermediary between the UI and data layer
 * - Handles CRUD operations (Create, Read, Update, Delete)
 */
public class VehicleManager {
    
    // Using a Map for efficient vehicle lookup by ID
    private Map<String, Vehicle> vehicleRegistry;
    
    // Keep track of vehicle count by type
    private Map<VehicleType, Integer> vehicleCountByType;

    /**
     * Constructor - initializes the vehicle registry
     */
    public VehicleManager() {
        this.vehicleRegistry = new HashMap<>();
        this.vehicleCountByType = new EnumMap<>(VehicleType.class);
        
        // Initialize counts for all vehicle types
        for (VehicleType type : VehicleType.values()) {
            vehicleCountByType.put(type, 0);
        }
    }

    // ==================== REGISTER VEHICLE ====================
    
    /**
     * Registers a new vehicle in the system
     * @param vehicle the vehicle to register
     * @throws DuplicateVehicleException if vehicle ID already exists
     * @throws InvalidVehicleDataException if vehicle data is invalid
     */
    public void registerVehicle(Vehicle vehicle) throws DuplicateVehicleException, InvalidVehicleDataException {
        // Validate the vehicle data
        VehicleValidator.validateVehicleData(
            vehicle.getVehicleId(),
            vehicle.getBrand(),
            vehicle.getModel(),
            vehicle.getYear(),
            vehicle.getPrice()
        );
        
        // Check for duplicate
        if (vehicleRegistry.containsKey(vehicle.getVehicleId())) {
            throw new DuplicateVehicleException(vehicle.getVehicleId());
        }
        
        // Register the vehicle
        vehicleRegistry.put(vehicle.getVehicleId(), vehicle);
        vehicle.register();
        
        // Update count
        vehicleCountByType.merge(vehicle.getType(), 1, Integer::sum);
        
        System.out.println("✅ Vehicle registered successfully: " + vehicle);
    }

    // ==================== REMOVE VEHICLE ====================
    
    /**
     * Removes a vehicle from the system
     * @param vehicleId the ID of the vehicle to remove
     * @throws VehicleNotFoundException if vehicle doesn't exist
     */
    public void removeVehicle(String vehicleId) throws VehicleNotFoundException {
        Vehicle vehicle = findVehicleById(vehicleId);
        
        vehicleRegistry.remove(vehicleId);
        vehicleCountByType.merge(vehicle.getType(), -1, Integer::sum);
        
        System.out.println("🗑️ Vehicle removed: " + vehicle);
    }

    // ==================== FIND VEHICLES ====================
    
    /**
     * Finds a vehicle by its ID
     * @param vehicleId the vehicle ID
     * @return the found vehicle
     * @throws VehicleNotFoundException if not found
     */
    public Vehicle findVehicleById(String vehicleId) throws VehicleNotFoundException {
        Vehicle vehicle = vehicleRegistry.get(vehicleId);
        if (vehicle == null) {
            throw new VehicleNotFoundException(vehicleId);
        }
        return vehicle;
    }
    
    /**
     * Searches vehicles by brand
     * @param brand the brand to search for
     * @return list of matching vehicles
     */
    public List<Vehicle> findVehiclesByBrand(String brand) {
        return vehicleRegistry.values().stream()
                .filter(v -> v.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }
    
    /**
     * Gets vehicles by type
     * @param type the vehicle type
     * @return list of vehicles of that type
     */
    public List<Vehicle> getVehiclesByType(VehicleType type) {
        return vehicleRegistry.values().stream()
                .filter(v -> v.getType() == type)
                .collect(Collectors.toList());
    }
    
    /**
     * Gets all vehicles in the system
     * @return list of all vehicles
     */
    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(vehicleRegistry.values());
    }
    
    /**
     * Gets vehicles that need service
     * @return list of vehicles needing service
     */
    public List<Vehicle> getVehiclesNeedingService() {
        return vehicleRegistry.values().stream()
                .filter(Vehicle::needsService)
                .collect(Collectors.toList());
    }
    
    /**
     * Gets vehicles by price range
     * @param minPrice minimum price
     * @param maxPrice maximum price
     * @return list of vehicles in price range
     */
    public List<Vehicle> getVehiclesByPriceRange(double minPrice, double maxPrice) {
        return vehicleRegistry.values().stream()
                .filter(v -> v.getPrice() >= minPrice && v.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }
    
    /**
     * Gets vehicles by year range
     * @param startYear start year (inclusive)
     * @param endYear end year (inclusive)
     * @return list of vehicles in year range
     */
    public List<Vehicle> getVehiclesByYearRange(int startYear, int endYear) {
        return vehicleRegistry.values().stream()
                .filter(v -> v.getYear() >= startYear && v.getYear() <= endYear)
                .collect(Collectors.toList());
    }

    // ==================== UPDATE VEHICLE ====================
    
    /**
     * Updates vehicle price
     * @param vehicleId the vehicle ID
     * @param newPrice the new price
     * @throws VehicleNotFoundException if vehicle not found
     * @throws InvalidVehicleDataException if price is invalid
     */
    public void updateVehiclePrice(String vehicleId, double newPrice) 
            throws VehicleNotFoundException, InvalidVehicleDataException {
        VehicleValidator.validatePrice(newPrice);
        Vehicle vehicle = findVehicleById(vehicleId);
        double oldPrice = vehicle.getPrice();
        vehicle.setPrice(newPrice);
        System.out.println("💰 Price updated for " + vehicleId + ": $" + oldPrice + " → $" + newPrice);
    }
    
    /**
     * Updates vehicle color
     * @param vehicleId the vehicle ID
     * @param color the new color
     * @throws VehicleNotFoundException if vehicle not found
     */
    public void updateVehicleColor(String vehicleId, String color) throws VehicleNotFoundException {
        Vehicle vehicle = findVehicleById(vehicleId);
        vehicle.setColor(color);
        System.out.println("🎨 Color updated for " + vehicleId + ": " + color);
    }
    
    /**
     * Add mileage to a vehicle
     * @param vehicleId the vehicle ID
     * @param miles miles to add
     * @throws VehicleNotFoundException if vehicle not found
     */
    public void addMileage(String vehicleId, double miles) throws VehicleNotFoundException {
        Vehicle vehicle = findVehicleById(vehicleId);
        vehicle.addMileage(miles);
        System.out.println("📏 Mileage updated for " + vehicleId + ": " + vehicle.getMileage() + " miles");
    }

    // ==================== SERVICE OPERATIONS ====================
    
    /**
     * Services a vehicle
     * @param vehicleId the vehicle ID
     * @throws VehicleNotFoundException if vehicle not found
     */
    public void serviceVehicle(String vehicleId) throws VehicleNotFoundException {
        Vehicle vehicle = findVehicleById(vehicleId);
        System.out.println("Estimated service cost: $" + String.format("%.2f", vehicle.getServiceCost()));
        vehicle.service();
    }

    // ==================== STATISTICS ====================
    
    /**
     * Gets total count of vehicles
     * @return total vehicle count
     */
    public int getTotalVehicleCount() {
        return vehicleRegistry.size();
    }
    
    /**
     * Gets vehicle count by type
     * @param type the vehicle type
     * @return count of that type
     */
    public int getVehicleCount(VehicleType type) {
        return vehicleCountByType.getOrDefault(type, 0);
    }
    
    /**
     * Gets total value of all vehicles
     * @return total value
     */
    public double getTotalVehicleValue() {
        return vehicleRegistry.values().stream()
                .mapToDouble(Vehicle::getPrice)
                .sum();
    }
    
    /**
     * Gets total depreciated value of all vehicles
     * @return total depreciated value
     */
    public double getTotalDepreciatedValue() {
        return vehicleRegistry.values().stream()
                .mapToDouble(Vehicle::calculateDepreciation)
                .sum();
    }
    
    /**
     * Displays summary statistics
     */
    public void displayStatistics() {
        System.out.println("\n========== VEHICLE STATISTICS ==========");
        System.out.println("Total Vehicles    : " + getTotalVehicleCount());
        System.out.println("-----------------------------------------");
        for (VehicleType type : VehicleType.values()) {
            System.out.printf("  %-12s   : %d%n", type.getDisplayName() + "s", getVehicleCount(type));
        }
        System.out.println("-----------------------------------------");
        System.out.printf("Total Value       : $%,.2f%n", getTotalVehicleValue());
        System.out.printf("Depreciated Value : $%,.2f%n", getTotalDepreciatedValue());
        System.out.println("Need Service      : " + getVehiclesNeedingService().size());
        System.out.println("=========================================\n");
    }
    
    /**
     * Lists all vehicles with brief info
     */
    public void listAllVehicles() {
        if (vehicleRegistry.isEmpty()) {
            System.out.println("No vehicles registered in the system.");
            return;
        }
        
        System.out.println("\n========== ALL VEHICLES ==========");
        System.out.printf("%-12s %-10s %-12s %-15s %-6s %12s%n", 
                "ID", "Type", "Brand", "Model", "Year", "Price");
        System.out.println("-".repeat(70));
        
        for (Vehicle v : vehicleRegistry.values()) {
            System.out.printf("%-12s %-10s %-12s %-15s %-6d $%,10.2f%n",
                    v.getVehicleId(),
                    v.getType().getDisplayName(),
                    v.getBrand(),
                    v.getModel(),
                    v.getYear(),
                    v.getPrice());
        }
        System.out.println("===================================\n");
    }
}
