package com.vehicle.management.model;
import com.vehicle.management.util.CarType;
import com.vehicle.management.util.FuelType;
import com.vehicle.management.util.TransmissionType;
import com.vehicle.management.exception.InvalidFuelCapacityException;

public class Car extends Vehicle {
    
    private int numberOfDoors;
    private double bootSpace;
    private boolean airConditionerAvailable;
    private TransmissionType transmissionType;
    private int seatingCapacity;
    private double fuelTankCapacity;
    private CarType carType;
    
    public Car(String vehicleId, String brand, String modelName, int yearOfManufacture, String color,
                  String registrationNumber, FuelType fuelType, double maxSpeed, double mileage, int numberOfDoors,
                  double bootSpace, boolean airConditionerAvailable, TransmissionType transmissionType,
                  int seatingCapacity, double fuelTankCapacity, CarType carType) {

        super(vehicleId, brand, modelName, yearOfManufacture, color, registrationNumber, fuelType, maxSpeed, mileage);
        this.numberOfDoors = numberOfDoors;
        this.bootSpace = bootSpace;
        this.airConditionerAvailable = airConditionerAvailable;
        this.transmissionType = transmissionType;
        this.seatingCapacity = seatingCapacity;
        this.fuelTankCapacity = fuelTankCapacity;
        this.carType = carType;
    }

    @Override
    public void start() {
        System.out.println("Car " + getBrand() + " " + getModelName() + " is started. Engine is running...");
    }

    @Override
    public void stop() {
        System.out.println("Car " + getBrand() + " " + getModelName() + " is stopped. Engine is off.");
    }

    @Override
    public void displayDetails() {
        System.out.println("Car Details:");
        System.out.println("Vehicle ID: " + getVehicleId());
        System.out.println("Brand: " + getBrand());
        System.out.println("Model Name: " + getModelName());
        System.out.println("Year of Manufacture: " + getYearOfManufacture());
        System.out.println("Color: " + getColor());
        System.out.println("Registration Number: " + getRegistrationNumber());
        System.out.println("Fuel Type: " + getFuelType());
        System.out.println("Max Speed: " + getMaxSpeed() + " km/h");
        System.out.println("Mileage: " + getMileage() + " km/l");
        System.out.println("Number of Doors: " + numberOfDoors);
        System.out.println("Boot Space: " + bootSpace + " liters");
        System.out.println("Air Conditioner Available: " + (airConditionerAvailable ? "Yes" : "No"));
        System.out.println("Transmission Type: " + transmissionType);
        System.out.println("Seating Capacity: " + seatingCapacity);
        System.out.println("Fuel Tank Capacity: " + fuelTankCapacity + " liters");
        System.out.println("Car Type: " + carType);
    }

    public void activateAC() {
        if (airConditionerAvailable) {
            System.out.println("AC activated!");
        } else {
            System.out.println("This car doesn't have AC.");
        }
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setBootSpace(double bootSpace) {
        this.bootSpace = bootSpace;
    }

    public double getBootSpace() {
        return bootSpace;
    }

    public void setAirConditionerAvailable(boolean airConditionerAvailable) {
        this.airConditionerAvailable = airConditionerAvailable;
    }

    public boolean isAirConditionerAvailable() {
        return airConditionerAvailable;
    }   

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setFuelTankCapacity(double fuelTankCapacity) throws InvalidFuelCapacityException {
        if(fuelTankCapacity < 0){
            throw new InvalidFuelCapacityException("Fuel tank capacity cannot be negative!");
        }
        this.fuelTankCapacity = fuelTankCapacity;
    }

    public double getFuelTankCapacity() {
        return fuelTankCapacity;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public CarType getCarType() {
        return carType;
    }

    @Override
    public String toString() {
        return "Car{" +
                "vehicleId='" + getVehicleId() + '\'' +
                ", brand='" + getBrand() + '\'' +
                ", modelName='" + getModelName() + '\'' +
                ", yearOfManufacture=" + getYearOfManufacture() +
                ", color='" + getColor() + '\'' +
                ", registrationNumber='" + getRegistrationNumber() + '\'' +
                ", fuelType=" + getFuelType() +
                ", maxSpeed=" + getMaxSpeed() +
                ", mileage=" + getMileage() +
                ", numberOfDoors=" + numberOfDoors +
                ", bootSpace=" + bootSpace +
                ", airConditionerAvailable=" + airConditionerAvailable +
                ", transmissionType=" + transmissionType +
                ", seatingCapacity=" + seatingCapacity +
                ", fuelTankCapacity=" + fuelTankCapacity +
                ", carType=" + carType +
                '}';
    }
}
