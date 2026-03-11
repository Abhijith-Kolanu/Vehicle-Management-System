package com.vehicle.management.exception;

/**
 * Exception thrown when vehicle data is invalid.
 * Examples: negative price, empty brand name, invalid year, etc.
 */
public class InvalidVehicleDataException extends VehicleException {
    
    private String fieldName;
    private Object invalidValue;
    
    public InvalidVehicleDataException(String message) {
        super(message, "ERR_INVALID_DATA");
    }
    
    public InvalidVehicleDataException(String fieldName, Object invalidValue, String reason) {
        super("Invalid value for " + fieldName + ": " + invalidValue + ". Reason: " + reason, "ERR_INVALID_DATA");
        this.fieldName = fieldName;
        this.invalidValue = invalidValue;
    }
    
    public String getFieldName() {
        return fieldName;
    }
    
    public Object getInvalidValue() {
        return invalidValue;
    }
}
