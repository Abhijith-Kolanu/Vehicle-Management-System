package com.vehicle.management.model;

/**
 * Enum representing different types of vehicles.
 * Enums are a special type in Java that represent a fixed set of constants.
 */
public enum VehicleType {
    CAR("Car", "Four-wheeled passenger vehicle"),
    MOTORCYCLE("Motorcycle", "Two-wheeled motor vehicle"),
    TRUCK("Truck", "Heavy goods vehicle"),
    BUS("Bus", "Large passenger vehicle");

    private final String displayName;
    private final String description;

    // Constructor for enum (always private)
    VehicleType(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
