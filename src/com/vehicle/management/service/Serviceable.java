package com.vehicle.management.service;

/**
 * Interface defining service operations for vehicles.
 * 
 * CONCEPT: Interface
 * - An interface defines a contract that implementing classes must follow
 * - All methods in an interface are implicitly public and abstract
 * - A class can implement multiple interfaces (unlike single inheritance with classes)
 */
public interface Serviceable {
    
    /**
     * Performs service on the vehicle
     */
    void service();
    
    /**
     * Gets the estimated service cost
     * @return service cost in currency
     */
    double getServiceCost();
    
    /**
     * Checks if the vehicle needs service based on criteria
     * @return true if service is needed
     */
    boolean needsService();
    
    /**
     * Gets the last service date as a string
     * @return last service date
     */
    String getLastServiceDate();
}
