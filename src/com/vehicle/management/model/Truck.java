package com.vehicle.management.model;
import com.vehicle.management.exception.InvalidNumberOfAxlesException;
import com.vehicle.management.exception.LoadCapacityException;
import com.vehicle.management.util.FuelType;
import com.vehicle.management.exception.InvalidCargoVolumeException;

public class Truck extends Vehicle {
    private double loadCapacity; 
    private int numberOfAxles;
    private boolean hasTrailer;
    private String goodsType;
    private double cargoVolume; 
    private double currentLoad;

    public Truck(String vehicleId, String brand, String modelName, int yearOfManufacture, String color,
                 String registrationNumber, FuelType fuelType, double maxSpeed, double mileage,
                 double loadCapacity, int numberOfAxles, boolean hasTrailer, String goodsType, double cargoVolume) {
        super(vehicleId, brand, modelName, yearOfManufacture, color, registrationNumber, fuelType, maxSpeed, mileage);
        this.loadCapacity = loadCapacity;
        this.numberOfAxles = numberOfAxles;
        this.hasTrailer = hasTrailer;
        this.goodsType = goodsType;
        this.cargoVolume = cargoVolume;
        this.currentLoad = 0.0;
    }

    @Override
    public void start() {
        System.out.println("Truck " + getBrand() + " " + getModelName() + " is started. Engine is running...");
    }

    @Override
    public void stop() {
        System.out.println("Truck " + getBrand() + " " + getModelName() + " is stopped. Engine is off.");
    }

    @Override
    public void displayDetails() {
        System.out.println("Truck Details:");
        System.out.println("Vehicle ID: " + getVehicleId());
        System.out.println("Brand: " + getBrand());
        System.out.println("Model Name: " + getModelName());
        System.out.println("Year of Manufacture: " + getYearOfManufacture());
        System.out.println("Color: " + getColor());
        System.out.println("Registration Number: " + getRegistrationNumber());
        System.out.println("Fuel Type: " + getFuelType());
        System.out.println("Max Speed: " + getMaxSpeed() + " km/h");
        System.out.println("Mileage: " + getMileage() + " km/l");
        System.out.println("Load Capacity: " + loadCapacity + " tons");
        System.out.println("Number of Axles: " + numberOfAxles);
        System.out.println("Has Trailer: " + (hasTrailer ? "Yes" : "No"));
        System.out.println("Goods Type: " + goodsType);
        System.out.println("Cargo Volume: " + cargoVolume + " cubic meters");
        System.out.println("Current Load: " + currentLoad + " tons");
    }

    public void setLoadCapacity(double loadCapacity) throws LoadCapacityException {
        if(loadCapacity <= 0) {
            throw new LoadCapacityException("Invalid load capacity!");
        }
        this.loadCapacity = loadCapacity;
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void setNumberOfAxles(int numberOfAxles) throws InvalidNumberOfAxlesException {
        if(numberOfAxles <= 0) {
            throw new InvalidNumberOfAxlesException("Invalid number of axles!");
        }
        this.numberOfAxles = numberOfAxles;
    }

    public int getNumberOfAxles() {
        return numberOfAxles;
    }

    public void setHasTrailer(boolean hasTrailer) {
        this.hasTrailer = hasTrailer;
    }

    public boolean hasTrailer() {
        return hasTrailer;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setCargoVolume(double cargoVolume) throws InvalidCargoVolumeException {
        if(cargoVolume <= 0) {
            throw new InvalidCargoVolumeException("Invalid cargo volume!");
        }
        this.cargoVolume = cargoVolume;
    }

    public double getCargoVolume() {
        return cargoVolume;
    }

    public double getCurrentLoad() {
        return currentLoad;
    }

    public void loadCargo(double weight) throws InvalidCargoVolumeException {
        if(weight <= 0) {
            throw new InvalidCargoVolumeException("Invalid Cargo Weight");
        }
        if(weight + currentLoad > loadCapacity) {
            throw new InvalidCargoVolumeException("Cargo weight exceeds load capacity!");
        }
        currentLoad += weight;
        System.out.println("Cargo loaded: " + weight + " tons");
    }

    public void unloadCargo(double weight) throws InvalidCargoVolumeException {
        if(weight <= 0) {
            throw new InvalidCargoVolumeException("Invalid Cargo weight!");
        }
        if(weight > currentLoad) {
            throw new InvalidCargoVolumeException("Cannot unload more cargo than is currently loaded!");
        }
        currentLoad -= weight;
        System.out.println("Cargo unloaded: " + weight + " tons");
    }

    public void attachTrailer() {
        if(hasTrailer) {
            System.out.println("Trailer is already attached.");
        } else {
            hasTrailer = true;
            System.out.println("Trailer attached successfully.");
        }
    }   

    public void detachTrailer() {
        if(!hasTrailer) {
            System.out.println("No trailer to detach.");
        } else {
            hasTrailer = false;
            System.out.println("Trailer detached successfully.");
        }
    }
}
