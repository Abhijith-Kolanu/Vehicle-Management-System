package com.vehicle.management.model;
import com.vehicle.management.util.FuelType;
import com.vehicle.management.exception.InvalidMileageException;
import com.vehicle.management.exception.InvalidSpeedException;
import com.vehicle.management.exception.InvalidManufactureYearException;

public abstract class Vehicle {
   private String vehicleId;
   
   private String brand;
   private String modelName;
   private int yearOfManufacture;
   private String color;

   private String registrationNumber;
   private FuelType fuelType;
   private double maxSpeed;
   private double currentSpeed;
   private double mileage;

   public Vehicle(String vehicleId, String brand, String modelName, int yearOfManufacture, String color,
                  String registrationNumber, FuelType fuelType, double maxSpeed, double mileage) {
       this.vehicleId = vehicleId;
       this.brand = brand;
       this.modelName = modelName;
       this.yearOfManufacture = yearOfManufacture;
       this.color = color;
       this.registrationNumber = registrationNumber;
       this.fuelType = fuelType;
       this.maxSpeed = maxSpeed;
       this.mileage = mileage;
       this.currentSpeed = 0.0; 
   }

   public String getVehicleId() {
       return vehicleId;
   }

   public void setBrand(String brand){
       this.brand = brand;
   }

   public String getBrand(){
       return brand;
   }

    public void setModelName(String modelName){
         this.modelName = modelName;
    }

    public String getModelName(){
        return modelName;
    }

    public void setYearOfManufacture(int yearOfManufacture) throws InvalidManufactureYearException {
        if(yearOfManufacture < 1886 || yearOfManufacture > java.time.Year.now().getValue()){
            throw new InvalidManufactureYearException("Invalid year of manufacture!");
        }
        this.yearOfManufacture = yearOfManufacture;
    }

    public int getYearOfManufacture(){
        return yearOfManufacture;
    }

    public void setColor(String color){
        this.color = color;
    }

    public String getColor(){
        return color;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setFuelType(FuelType fuelType){
        this.fuelType = fuelType;
    }

    public FuelType getFuelType(){
        return fuelType;
    }

    public void setMaxSpeed(double maxSpeed) throws InvalidSpeedException {
        if(maxSpeed < 0){
            throw new InvalidSpeedException("Maximum speed cannot be negative!");
        }
        this.maxSpeed = maxSpeed;
    }
    public double getMaxSpeed(){
        return maxSpeed;
    }

    public void setCurrentSpeed(double currentSpeed) throws InvalidSpeedException {
        if(currentSpeed < 0){
            throw new InvalidSpeedException("Speed cannot be negative!");
        }
        if(currentSpeed >  maxSpeed){
            throw new InvalidSpeedException("Speed cannot exceed max speed!");
        }
        this.currentSpeed = currentSpeed;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public void setMileage(double mileage) throws InvalidMileageException {
        if(mileage < 0){
            throw new InvalidMileageException("Mileage cannot be negative!");
        }
        this.mileage = mileage;
    }

    public double getMileage(){
        return mileage;
    }

    public abstract void start();

    public abstract void stop();

    public abstract void displayDetails();

    public void accelerate(double increment) throws InvalidSpeedException {
        if(increment < 0){
            throw new InvalidSpeedException("Increment cannot be negative!");
        }
        if(currentSpeed + increment > maxSpeed){;
            throw new InvalidSpeedException("Cannot accelerate beyond max speed!");
        }
        setCurrentSpeed(this.currentSpeed + increment);
    }

    public void decelerate(double decrement) throws InvalidSpeedException {
        if(decrement < 0){
            throw new InvalidSpeedException("Decrement cannot be negative!");
        }
        if(currentSpeed - decrement < 0){
            throw new InvalidSpeedException("Cannot decelerate below 0!");
        }
        setCurrentSpeed(this.currentSpeed - decrement);
    }


    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId='" + vehicleId + '\'' +
                ", brand='" + brand + '\'' +
                ", modelName='" + modelName + '\'' +
                ", yearOfManufacture=" + yearOfManufacture +
                ", color='" + color + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", fuelType=" + fuelType +
                ", maxSpeed=" + maxSpeed +
                ", currentSpeed=" + currentSpeed +
                ", mileage=" + mileage +
                '}';
    }


}
