package com.vehicle.management.service;
import com.vehicle.management.model.Vehicle;
import com.vehicle.management.exception.*;
import java.util.ArrayList;
import java.util.List;


public class VehicleService {
    
    private List<Vehicle> vehicles;

    public VehicleService() {
        this.vehicles = new ArrayList<>();
    }

    public void registerVehicle(Vehicle vehicle) throws DuplicateVehicleException {
        for(Vehicle v : vehicles) {
            if(v.getVehicleId().equals(vehicle.getVehicleId())) {
                throw new DuplicateVehicleException("Vehicle with ID " + vehicle.getVehicleId() + " already exists!");
            }
            if(v.getRegistrationNumber().equals(vehicle.getRegistrationNumber())) {
                throw new DuplicateVehicleException("Vehicle with registration number " + vehicle.getRegistrationNumber() + " already exists!");
            }
        }
        vehicles.add(vehicle);
        System.out.println("Vehicle registered successfully: " + vehicle.getVehicleId());
    }

    public Vehicle findByRegistrationNumber(String regNumber) throws VehicleNotFoundException {
        for(Vehicle v: vehicles) {
            if(regNumber.equals(v.getRegistrationNumber())){
                return v;
            }
        }
        throw new VehicleNotFoundException("Vehicle with registration number " + regNumber + " not found!");    
    }

    public void removeVehicle(String regNumber) throws VehicleNotFoundException {
        for(Vehicle v: vehicles) {
            if(regNumber.equals(v.getRegistrationNumber())) {
                vehicles.remove(v);
                System.out.println("Vehicle removed successfully: " + regNumber);
                return;
            }
        }
        throw new VehicleNotFoundException("Vehicle with registration number " + regNumber + " not found!");
    }

    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(vehicles);
    }

    public void displayAllVehicles() {
        if(vehicles.isEmpty()) {
            System.out.println("No vehicles registered.");
            return;
        }
        for(Vehicle v : vehicles) {
            v.displayDetails();
            System.out.println("-----------------------------");
        }
    }

    public int getVehicleCount() {
        return vehicles.size();
    }
}
