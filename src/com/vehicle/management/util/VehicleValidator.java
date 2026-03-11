package com.vehicle.management.util;

import com.vehicle.management.exception.InvalidVehicleDataException;
import java.time.LocalDate;

/**
 * Utility class for validating vehicle data.
 * 
 * CONCEPT: Utility Classes
 * - Contains static methods that can be used without creating an instance
 * - Provides reusable validation logic
 * - Private constructor prevents instantiation
 */
public class VehicleValidator {
    
    // Private constructor to prevent instantiation
    private VehicleValidator() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
    
    /**
     * Validates vehicle ID
     * @param vehicleId the ID to validate
     * @throws InvalidVehicleDataException if validation fails
     */
    public static void validateVehicleId(String vehicleId) throws InvalidVehicleDataException {
        if (vehicleId == null || vehicleId.trim().isEmpty()) {
            throw new InvalidVehicleDataException("vehicleId", vehicleId, "Vehicle ID cannot be null or empty");
        }
        if (vehicleId.length() < 3) {
            throw new InvalidVehicleDataException("vehicleId", vehicleId, "Vehicle ID must be at least 3 characters");
        }
        if (vehicleId.length() > 20) {
            throw new InvalidVehicleDataException("vehicleId", vehicleId, "Vehicle ID cannot exceed 20 characters");
        }
    }
    
    /**
     * Validates brand name
     * @param brand the brand to validate
     * @throws InvalidVehicleDataException if validation fails
     */
    public static void validateBrand(String brand) throws InvalidVehicleDataException {
        if (brand == null || brand.trim().isEmpty()) {
            throw new InvalidVehicleDataException("brand", brand, "Brand cannot be null or empty");
        }
        if (brand.length() < 2) {
            throw new InvalidVehicleDataException("brand", brand, "Brand must be at least 2 characters");
        }
    }
    
    /**
     * Validates model name
     * @param model the model to validate
     * @throws InvalidVehicleDataException if validation fails
     */
    public static void validateModel(String model) throws InvalidVehicleDataException {
        if (model == null || model.trim().isEmpty()) {
            throw new InvalidVehicleDataException("model", model, "Model cannot be null or empty");
        }
    }
    
    /**
     * Validates manufacturing year
     * @param year the year to validate
     * @throws InvalidVehicleDataException if validation fails
     */
    public static void validateYear(int year) throws InvalidVehicleDataException {
        int currentYear = LocalDate.now().getYear();
        if (year < 1900) {
            throw new InvalidVehicleDataException("year", year, "Year cannot be before 1900");
        }
        if (year > currentYear + 1) {
            throw new InvalidVehicleDataException("year", year, "Year cannot be more than 1 year in the future");
        }
    }
    
    /**
     * Validates price
     * @param price the price to validate
     * @throws InvalidVehicleDataException if validation fails
     */
    public static void validatePrice(double price) throws InvalidVehicleDataException {
        if (price < 0) {
            throw new InvalidVehicleDataException("price", price, "Price cannot be negative");
        }
        if (price > 100_000_000) {
            throw new InvalidVehicleDataException("price", price, "Price seems unrealistically high");
        }
    }
    
    /**
     * Validates all basic vehicle data
     * @param vehicleId the vehicle ID
     * @param brand the brand name
     * @param model the model name
     * @param year the manufacturing year
     * @param price the price
     * @throws InvalidVehicleDataException if any validation fails
     */
    public static void validateVehicleData(String vehicleId, String brand, String model, 
                                           int year, double price) throws InvalidVehicleDataException {
        validateVehicleId(vehicleId);
        validateBrand(brand);
        validateModel(model);
        validateYear(year);
        validatePrice(price);
    }
    
    /**
     * Validates engine CC for motorcycles
     * @param engineCC the engine capacity
     * @throws InvalidVehicleDataException if validation fails
     */
    public static void validateEngineCC(int engineCC) throws InvalidVehicleDataException {
        if (engineCC < 50) {
            throw new InvalidVehicleDataException("engineCC", engineCC, "Engine CC must be at least 50");
        }
        if (engineCC > 2500) {
            throw new InvalidVehicleDataException("engineCC", engineCC, "Engine CC cannot exceed 2500");
        }
    }
    
    /**
     * Validates passenger capacity for buses
     * @param capacity the passenger capacity
     * @throws InvalidVehicleDataException if validation fails
     */
    public static void validatePassengerCapacity(int capacity) throws InvalidVehicleDataException {
        if (capacity < 10) {
            throw new InvalidVehicleDataException("passengerCapacity", capacity, "Bus must have at least 10 seats");
        }
        if (capacity > 100) {
            throw new InvalidVehicleDataException("passengerCapacity", capacity, "Capacity cannot exceed 100 passengers");
        }
    }
    
    /**
     * Validates load capacity for trucks
     * @param capacity the load capacity in tons
     * @throws InvalidVehicleDataException if validation fails
     */
    public static void validateLoadCapacity(double capacity) throws InvalidVehicleDataException {
        if (capacity <= 0) {
            throw new InvalidVehicleDataException("loadCapacity", capacity, "Load capacity must be positive");
        }
        if (capacity > 100) {
            throw new InvalidVehicleDataException("loadCapacity", capacity, "Load capacity cannot exceed 100 tons");
        }
    }
}
