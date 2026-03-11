package com.vehicle.management.model;
import com.vehicle.management.exception.InvalidEngineCCException;
import com.vehicle.management.util.BikeType;
import com.vehicle.management.util.FuelType;

public class Bike extends Vehicle {
    private int engineCC;
    private boolean hasGear;
    private BikeType bikeType;
    private boolean hasABS;
    private boolean kickStartAvailable;
    private boolean hasHelmetStorage;


    public Bike(String vehicleId, String brand, String modelName, int yearOfManufacture, String color,
                  String registrationNumber, FuelType fuelType, double maxSpeed, double mileage, int engineCC,
                  boolean hasGear, BikeType bikeType, boolean hasABS, boolean kickStartAvailable, boolean hasHelmetStorage) {
        super(vehicleId, brand, modelName, yearOfManufacture, color, registrationNumber, fuelType, maxSpeed, mileage);
        this.engineCC = engineCC;
        this.hasGear = hasGear;
        this.bikeType = bikeType;
        this.hasABS = hasABS;
        this.kickStartAvailable = kickStartAvailable;
        this.hasHelmetStorage = hasHelmetStorage;
    }


    @Override
    public void start() {
        System.out.println("Bike " + getBrand() + " " + getModelName() + " is started. Engine is running...");
    }

    @Override
    public void stop() {
        System.out.println("Bike " + getBrand() + " " + getModelName() + " is stopped. Engine is off.");
    }

    @Override
    public void displayDetails() {
        System.out.println("Bike Details:");
        System.out.println("Vehicle ID: " + getVehicleId());
        System.out.println("Brand: " + getBrand());
        System.out.println("Model Name: " + getModelName());
        System.out.println("Year of Manufacture: " + getYearOfManufacture());
        System.out.println("Color: " + getColor());
        System.out.println("Registration Number: " + getRegistrationNumber());
        System.out.println("Fuel Type: " + getFuelType());
        System.out.println("Max Speed: " + getMaxSpeed() + " km/h");
        System.out.println("Mileage: " + getMileage() + " km/l");
        System.out.println("Engine CC: " + engineCC);
        System.out.println("Has Gear: " + (hasGear ? "Yes" : "No"));
        System.out.println("Bike Type: " + bikeType);
        System.out.println("Has ABS: " + (hasABS ? "Yes" : "No"));
        System.out.println("Kick Start Available: " + (kickStartAvailable ? "Yes" : "No"));
        System.out.println("Has Helmet Storage: " + (hasHelmetStorage ? "Yes" : "No"));
    }

    public void setEngineCC(int engineCC) throws InvalidEngineCCException{
        if(engineCC <= 0) {
            throw new InvalidEngineCCException("Invalid Engine CC!");
        }
        this.engineCC = engineCC;
    }

    public int getEngineCC() {
        return engineCC;
    }

    public void setHasGear(boolean hasGear) {
        this.hasGear = hasGear;
    }

    public boolean hasGear() {
        return hasGear;
    }

    public void setBikeType(BikeType bikeType) {
        this.bikeType = bikeType;
    }

    public BikeType getBikeType() {
        return bikeType;
    }

    public void setHasABS(boolean hasABS) {
        this.hasABS = hasABS;
    }

    public boolean hasABS() {
        return hasABS;
    }

    public void setKickStartAvailable(boolean kickStartAvailable) {
        this.kickStartAvailable = kickStartAvailable;
    }

    public boolean isKickStartAvailable() {
        return kickStartAvailable;
    }

    public void setHasHelmetStorage(boolean hasHelmetStorage) {
        this.hasHelmetStorage = hasHelmetStorage;
    }

    public boolean hasHelmetStorage() {
        return hasHelmetStorage;
    }

   public void useKickStart() {
        if (kickStartAvailable) {
            System.out.println("Bike started using kick start.");
        } else {
            System.out.println("Kick start is not available on this bike.");
        }
    }

    public void performWheelie() {
        if (bikeType != null &&
            (bikeType == BikeType.SPORTS || bikeType == BikeType.STANDARD)) {

            System.out.println("Performing a wheelie on the " +
                    getBrand() + " " + getModelName() + "! 🏍️🔥");

        } else {
            System.out.println("This bike type is not suitable for wheelies.");
        }
    }

}
