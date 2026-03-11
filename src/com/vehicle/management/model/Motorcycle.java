package com.vehicle.management.model;

/**
 * Motorcycle class - extends Vehicle
 * 
 * CONCEPT: Inheritance
 * - Motorcycle inherits from Vehicle (parent/superclass)
 * - Has its own specific attributes like engineCC, bikeType
 * - Demonstrates how different vehicle types share common base but have unique features
 */
public class Motorcycle extends Vehicle {
    
    // Motorcycle-specific properties
    private int engineCC;           // Engine capacity in cubic centimeters
    private String bikeType;        // Sport, Cruiser, Touring, Off-road, Standard
    private boolean hasCarrier;     // For carrying goods
    private boolean hasSidecar;
    private int topSpeed;           // in km/h
    private String startType;       // Kick, Electric, Both

    /**
     * Basic constructor
     */
    public Motorcycle(String vehicleId, String brand, String model, int year, double price) {
        super(vehicleId, brand, model, year, price, VehicleType.MOTORCYCLE);
        this.engineCC = 150;
        this.bikeType = "Standard";
        this.hasCarrier = false;
        this.hasSidecar = false;
        this.startType = "Electric";
    }

    /**
     * Full constructor with motorcycle-specific details
     */
    public Motorcycle(String vehicleId, String brand, String model, int year, double price,
                      int engineCC, String bikeType, int topSpeed) {
        super(vehicleId, brand, model, year, price, VehicleType.MOTORCYCLE);
        this.engineCC = engineCC;
        this.bikeType = bikeType;
        this.topSpeed = topSpeed;
        this.hasCarrier = false;
        this.hasSidecar = false;
        this.startType = "Electric";
    }

    // ==================== ABSTRACT METHOD IMPLEMENTATIONS ====================
    
    @Override
    public void displayDetails() {
        System.out.println("\n========== MOTORCYCLE DETAILS ==========");
        System.out.println("Vehicle ID    : " + getVehicleId());
        System.out.println("Brand         : " + getBrand());
        System.out.println("Model         : " + getModel());
        System.out.println("Year          : " + getYear());
        System.out.println("Price         : $" + String.format("%.2f", getPrice()));
        System.out.println("Current Value : $" + String.format("%.2f", calculateDepreciation()));
        System.out.println("Color         : " + (getColor() != null ? getColor() : "Not specified"));
        System.out.println("Mileage       : " + getMileage() + " miles");
        System.out.println("----- Motorcycle Specifications -----");
        System.out.println("Engine CC     : " + engineCC + " cc");
        System.out.println("Bike Type     : " + bikeType);
        System.out.println("Top Speed     : " + topSpeed + " km/h");
        System.out.println("Start Type    : " + startType);
        System.out.println("Has Carrier   : " + (hasCarrier ? "Yes" : "No"));
        System.out.println("Has Sidecar   : " + (hasSidecar ? "Yes" : "No"));
        System.out.println("Registered    : " + (isRegistered() ? "Yes" : "No"));
        System.out.println("Last Service  : " + getLastServiceDate());
        System.out.println("=========================================\n");
    }

    @Override
    public void performOperation() {
        System.out.println("\n--- Performing Motorcycle-Specific Operations ---");
        System.out.println("1. Checking tire pressure...");
        System.out.println("2. Inspecting chain tension...");
        System.out.println("3. Starting " + engineCC + "cc engine (" + startType + " start)...");
        System.out.println("4. Warming up engine...");
        System.out.println("5. Checking brakes...");
        System.out.println("Motorcycle is ready to ride!");
    }

    @Override
    public String getTypeSpecificInfo() {
        return String.format("%dcc %s motorcycle with %d km/h top speed", 
                engineCC, bikeType, topSpeed);
    }

    // ==================== MOTORCYCLE-SPECIFIC METHODS ====================
    
    /**
     * Perform a wheelie (motorcycle-specific action)
     */
    public void doWheelie() {
        if (engineCC >= 250) {
            System.out.println("🏍️ " + getBrand() + " " + getModel() + " is doing a wheelie! VROOOM!");
        } else {
            System.out.println("Engine is too small for a safe wheelie.");
        }
    }
    
    /**
     * Revs the engine
     */
    public void revEngine() {
        System.out.println("🏍️ VROOM VROOM! " + engineCC + "cc engine roaring!");
    }
    
    /**
     * Calculate power-to-weight estimate
     */
    public String getPowerCategory() {
        if (engineCC < 150) return "Commuter";
        if (engineCC < 400) return "Standard";
        if (engineCC < 650) return "Mid-range";
        if (engineCC < 1000) return "High-performance";
        return "Superbike";
    }

    // ==================== OVERRIDE SERVICE COST ====================
    
    @Override
    public double getServiceCost() {
        double baseCost = super.getServiceCost();
        // Lower base cost for motorcycles
        baseCost *= 0.7;
        // Add based on engine size
        baseCost += (engineCC * 0.05);
        return baseCost;
    }

    // ==================== GETTERS AND SETTERS ====================
    
    public int getEngineCC() {
        return engineCC;
    }

    public void setEngineCC(int engineCC) {
        this.engineCC = engineCC;
    }

    public String getBikeType() {
        return bikeType;
    }

    public void setBikeType(String bikeType) {
        this.bikeType = bikeType;
    }

    public boolean isHasCarrier() {
        return hasCarrier;
    }

    public void setHasCarrier(boolean hasCarrier) {
        this.hasCarrier = hasCarrier;
    }

    public boolean isHasSidecar() {
        return hasSidecar;
    }

    public void setHasSidecar(boolean hasSidecar) {
        this.hasSidecar = hasSidecar;
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(int topSpeed) {
        this.topSpeed = topSpeed;
    }

    public String getStartType() {
        return startType;
    }

    public void setStartType(String startType) {
        this.startType = startType;
    }
}
