package com.vehicle.management.model;

/**
 * Bus class - extends Vehicle
 * 
 * CONCEPT: Extending features from one vehicle type to another
 * - Bus shares common Vehicle properties
 * - Has unique properties for passenger transport
 * - Demonstrates specialization in OOP
 */
public class Bus extends Vehicle {
    
    // Bus-specific properties
    private int passengerCapacity;
    private int currentPassengers;
    private String busType;          // City, School, Tourist, Intercity
    private boolean isAirConditioned;
    private boolean hasWifi;
    private int numDoors;
    private String routeNumber;
    private boolean isAccessible;    // Wheelchair accessible

    /**
     * Basic constructor
     */
    public Bus(String vehicleId, String brand, String model, int year, double price) {
        super(vehicleId, brand, model, year, price, VehicleType.BUS);
        this.passengerCapacity = 40;
        this.currentPassengers = 0;
        this.busType = "City";
        this.isAirConditioned = true;
        this.numDoors = 2;
        this.isAccessible = true;
    }

    /**
     * Full constructor with bus-specific details
     */
    public Bus(String vehicleId, String brand, String model, int year, double price,
               int passengerCapacity, String busType, boolean isAirConditioned) {
        super(vehicleId, brand, model, year, price, VehicleType.BUS);
        this.passengerCapacity = passengerCapacity;
        this.busType = busType;
        this.isAirConditioned = isAirConditioned;
        this.currentPassengers = 0;
        this.numDoors = 2;
        this.isAccessible = true;
    }

    // ==================== ABSTRACT METHOD IMPLEMENTATIONS ====================
    
    @Override
    public void displayDetails() {
        System.out.println("\n========== BUS DETAILS ==========");
        System.out.println("Vehicle ID      : " + getVehicleId());
        System.out.println("Brand           : " + getBrand());
        System.out.println("Model           : " + getModel());
        System.out.println("Year            : " + getYear());
        System.out.println("Price           : $" + String.format("%.2f", getPrice()));
        System.out.println("Current Value   : $" + String.format("%.2f", calculateDepreciation()));
        System.out.println("Color           : " + (getColor() != null ? getColor() : "Not specified"));
        System.out.println("Mileage         : " + getMileage() + " miles");
        System.out.println("----- Bus Specifications -----");
        System.out.println("Bus Type        : " + busType);
        System.out.println("Route Number    : " + (routeNumber != null ? routeNumber : "Not assigned"));
        System.out.println("Capacity        : " + passengerCapacity + " passengers");
        System.out.println("Current         : " + currentPassengers + " passengers");
        System.out.println("Available Seats : " + getAvailableSeats());
        System.out.println("Doors           : " + numDoors);
        System.out.println("Air Conditioned : " + (isAirConditioned ? "Yes" : "No"));
        System.out.println("WiFi            : " + (hasWifi ? "Yes" : "No"));
        System.out.println("Accessible      : " + (isAccessible ? "Yes" : "No"));
        System.out.println("Registered      : " + (isRegistered() ? "Yes" : "No"));
        System.out.println("Last Service    : " + getLastServiceDate());
        System.out.println("==================================\n");
    }

    @Override
    public void performOperation() {
        System.out.println("\n--- Performing Bus-Specific Operations ---");
        System.out.println("1. Checking passenger count: " + currentPassengers + "/" + passengerCapacity);
        System.out.println("2. Verifying " + numDoors + " doors are functional...");
        System.out.println("3. Checking emergency exits...");
        if (isAirConditioned) {
            System.out.println("4. Testing air conditioning system...");
        }
        if (hasWifi) {
            System.out.println("5. Checking WiFi connectivity...");
        }
        System.out.println("6. Announcing route " + (routeNumber != null ? routeNumber : "TBD") + "...");
        System.out.println("Bus is ready for service!");
    }

    @Override
    public String getTypeSpecificInfo() {
        return String.format("%s bus with %d passenger capacity, %s", 
                busType, passengerCapacity, 
                isAirConditioned ? "Air Conditioned" : "Non-AC");
    }

    // ==================== BUS-SPECIFIC METHODS ====================
    
    /**
     * Board passengers onto the bus
     * @param count number of passengers boarding
     * @return true if boarding successful
     */
    public boolean boardPassengers(int count) {
        if (count <= 0) {
            System.out.println("Invalid passenger count.");
            return false;
        }
        
        if (currentPassengers + count > passengerCapacity) {
            System.out.println("🚌 Cannot board! Only " + getAvailableSeats() + " seats available.");
            return false;
        }
        
        currentPassengers += count;
        System.out.println("🚌 " + count + " passengers boarded.");
        System.out.println("   Occupancy: " + currentPassengers + "/" + passengerCapacity);
        return true;
    }
    
    /**
     * Passengers alighting from the bus
     * @param count number of passengers alighting
     * @return true if alighting successful
     */
    public boolean alightPassengers(int count) {
        if (count <= 0 || count > currentPassengers) {
            System.out.println("Invalid passenger count.");
            return false;
        }
        
        currentPassengers -= count;
        System.out.println("🚌 " + count + " passengers alighted.");
        System.out.println("   Remaining: " + currentPassengers + " passengers");
        return true;
    }
    
    /**
     * All passengers alight
     */
    public void endRoute() {
        System.out.println("🚌 End of route " + (routeNumber != null ? routeNumber : "") + 
                ". All " + currentPassengers + " passengers alighting.");
        currentPassengers = 0;
    }
    
    /**
     * Get available seats
     */
    public int getAvailableSeats() {
        return passengerCapacity - currentPassengers;
    }
    
    /**
     * Check if bus is full
     */
    public boolean isFull() {
        return currentPassengers >= passengerCapacity;
    }
    
    /**
     * Announce next stop
     */
    public void announceStop(String stopName) {
        System.out.println("📢 ANNOUNCEMENT: Next stop - " + stopName);
    }
    
    /**
     * Ring the bell
     */
    public void ringBell() {
        System.out.println("🔔 DING DING! Stop requested.");
    }

    // ==================== OVERRIDE SERVICE COST ====================
    
    @Override
    public double getServiceCost() {
        double baseCost = super.getServiceCost();
        // Buses need extensive maintenance
        baseCost *= 2.0;
        // Add cost based on capacity
        baseCost += (passengerCapacity * 5);
        // A/C maintenance
        if (isAirConditioned) baseCost += 150;
        return baseCost;
    }

    // ==================== GETTERS AND SETTERS ====================
    
    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public int getCurrentPassengers() {
        return currentPassengers;
    }

    public void setCurrentPassengers(int currentPassengers) {
        this.currentPassengers = currentPassengers;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public boolean isAirConditioned() {
        return isAirConditioned;
    }

    public void setAirConditioned(boolean airConditioned) {
        isAirConditioned = airConditioned;
    }

    public boolean isHasWifi() {
        return hasWifi;
    }

    public void setHasWifi(boolean hasWifi) {
        this.hasWifi = hasWifi;
    }

    public int getNumDoors() {
        return numDoors;
    }

    public void setNumDoors(int numDoors) {
        this.numDoors = numDoors;
    }

    public String getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(String routeNumber) {
        this.routeNumber = routeNumber;
    }

    public boolean isAccessible() {
        return isAccessible;
    }

    public void setAccessible(boolean accessible) {
        isAccessible = accessible;
    }
}
